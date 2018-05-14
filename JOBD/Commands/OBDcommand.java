package Commands;

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



    public OBDcommand(String command) {
        this.cmd = command;
        this.buffer = new ArrayList<>();
    }

    public void run(InputStream in, OutputStream out) throws IOException, InterruptedException {
        synchronized (OBDcommand.class) {
            start = System.currentTimeMillis();
            sendCommand(out);
            readResult(in);
            end = System.currentTimeMillis();
        }
    }

    protected void sendCommand(OutputStream out) throws IOException, InterruptedException {
        out.write((cmd + "\r").getBytes()); // Cariage return hinzugefuegt
        out.flush();
        if (waitForResponse != null && waitForResponse > 0) {
            Thread.sleep(waitForResponse);
        }
    }

    protected void readResult(InputStream in) throws IOException {
        readRawData(in);
        checkData();
        fillBuffer();
        calculateResult();
    }

    protected abstract void calculateResult(); // Was muss mit den Daten noch passieren bevor diese zurück gegeben werden

    private void checkData() throws IOException {
        if(rawData.isEmpty()){
            throw new IOException("Fehlerhafte antwort");
        }
    }

    protected void readRawData(InputStream in) throws IOException {
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

    protected String replaceAll(Pattern pattern, String input, String replacement) {
        return pattern.matcher(input).replaceAll(replacement);
    }

    protected String removeAll(Pattern pattern, String input) {
        return pattern.matcher(input).replaceAll("");
    }

    protected void fillBuffer() throws IOException {
        rawData = removeAll(WHITESPACE_PATTERN, rawData); //removes all [ \t\n\x0B\f\r]
        rawData = removeAll(BUSINIT_PATTERN, rawData);

        if(UNABLE_TO_CONNECT.matcher(rawData).matches()){
            throw new IOException("Fahrzeug  ist nicht verbunden");
        }

        if (!DIGITS_LETTERS_PATTERN.matcher(rawData).matches()) {
            System.out.println(rawData);
            throw new IOException("Ungueltige Zeichen");
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
        return rawData;
    }


}
