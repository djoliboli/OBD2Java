package Config;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class config {
    static Properties props = new Properties();

    // Serial Settings
    public static String SerialPort = props.getProperty("SerialPortName");
    public static Integer Baudrate = Integer.parseInt(props.getProperty("baudrate"));
    public static Integer Databits = Integer.parseInt(props.getProperty("databits"));
    public static Integer Stopbit = Integer.parseInt(props.getProperty("stopbit"));

    //MQTT Settings


    File configFile = new File(System.getProperty("user.dir") + "//Config//config.cfg");
    static config conf = new config();
    public config(){
        try {
            readConfig();
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }

    }


    public void readConfig() throws IOException {
        try {
            FileReader reader = new FileReader(configFile);
            props.load(reader);
            reader.close();
            if(props.isEmpty()){
                createDefaultConfig();
            }
        }
        catch (IOException e){
            try {
                configFile.createNewFile();
                createDefaultConfig();
            }
            catch (Exception e1){
                File folder = new File(System.getProperty("user.dir") + "//Config");
                folder.mkdir();
                createDefaultConfig();
            }
        }
    }
        protected void createDefaultConfig() throws IOException {
        props.setProperty("baudrate","115200");
        props.setProperty("COMport","1");
        props.setProperty("IP","10.10.10.10");
        props.setProperty("MQTTport","1234");
        props.setProperty("SerialPortName","USB-to-Serial Port");
        props.setProperty("databits","8");
        props.setProperty("stopbit","1");
        FileWriter writer = new FileWriter(configFile);
        props.store(writer,null);
        writer.flush();
        writer.close();
        }


}





