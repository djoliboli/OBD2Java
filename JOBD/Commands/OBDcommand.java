package Commands;

import Exeption.CarUnableToConnectExeption;
import Exeption.DataInvalidExeption;
import Exeption.UnableToConnectExeption;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.regex.Pattern;

public abstract class OBDcommand {
    protected ArrayList<Integer> buffer = null;
    protected String cmd = null;
    private long start;
    private long end;
    protected Long waitForResponse = null;
    protected String rawData = null;
    boolean available = true;




    public OBDcommand(String command) {
        this.cmd = command;
        this.buffer = new ArrayList<>();
    }

    public void run(InputStream in, OutputStream out) throws IOException, InterruptedException, DataInvalidExeption, CarUnableToConnectExeption {
        synchronized (OBDcommand.class) {
            if (available) {
                start = System.currentTimeMillis();
                sendCommand(out);
                readResult(in);
                end = System.currentTimeMillis();
            }
            else return;
        }
    }

    protected void sendCommand(OutputStream out) throws IOException, InterruptedException {
        out.write((cmd + "\r").getBytes()); // Cariage return hinzugefuegt
        out.flush();
        if (waitForResponse != null && waitForResponse > 0) {
            Thread.sleep(waitForResponse);
        }
    }

    protected void readResult(InputStream in) throws DataInvalidExeption, IOException, CarUnableToConnectExeption{
        readRawData(in);
        checkData();
        fillBuffer();
        calculateResult();
    }

    protected abstract void calculateResult(); // Was muss mit den Daten noch passieren bevor diese zurück gegeben werden

    private void checkData() throws DataInvalidExeption {
        if(rawData.isEmpty()){
            throw new DataInvalidExeption();
        }
    }

    protected void readRawData(InputStream in) throws IOException{
        byte b = 0;
        StringBuilder res = new StringBuilder();


        char c;
        // -1 ende des streams
        while (((b = (byte) in.read()) > -1)) {
            c = (char) b;
            if (c == '>') // > ende Zeichen
            {
                break;
            }
            res.append(c);
        }

        rawData = removeAll(SEARCHING_PATTERN, res.toString());

        rawData = removeAll(WHITESPACE_PATTERN, rawData);//removes all [ \t\n\x0B\f\r]
    }

    private static Pattern WHITESPACE_PATTERN = Pattern.compile("\\s"); // Whitespace
    private static Pattern BUSINIT_PATTERN = Pattern.compile("(BUS INIT)|(BUSINIT)|(\\.)"); // BUS INIT Nachrichten
    private static Pattern SEARCHING_PATTERN = Pattern.compile("SEARCHING"); // Searching
    private static Pattern DIGITS_LETTERS_PATTERN = Pattern.compile("([0-9A-F])+"); // alle zugelassenen Zeichen
    private static Pattern UNABLE_TO_CONNECT = Pattern.compile(".*UNABLETOCONNECT"); //falls nicht verbunden
    private static Pattern NODATA = Pattern.compile(".*NODATA"); //falls nicht verbunden



    protected String removeAll(Pattern pattern, String input) {
        return pattern.matcher(input).replaceAll("");
    }

    protected void fillBuffer() throws CarUnableToConnectExeption, DataInvalidExeption {
        rawData = removeAll(WHITESPACE_PATTERN, rawData); //removes all [ \t\n\x0B\f\r]
        rawData = removeAll(BUSINIT_PATTERN, rawData);

        if(UNABLE_TO_CONNECT.matcher(rawData).matches()){
            throw new CarUnableToConnectExeption();
        }
        else if(NODATA.matcher(rawData).matches()){
            available = false;
            return;

        }

        else if (!DIGITS_LETTERS_PATTERN.matcher(rawData).matches()) {
            System.out.println("ERROR !!!: "+rawData);
            throw new DataInvalidExeption();
        }

        // read string each two chars
        buffer.clear();
        int begin = 0;
        int end = 2;
        while (end <= rawData.length()) {
            buffer.add(Integer.decode("0x" + rawData.substring(begin, end)));
            begin = end;
            end += 2;
        }
    }

    public String getResult() {
        if (available){
            return rawData;
        }
        else{
            return "NODATA";
        }
    }




}
