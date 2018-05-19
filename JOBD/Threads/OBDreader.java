package Threads;

import Commands.*;
import Exeption.Checker;
import Exeption.OBDUnableToConnectExeption;
import GSON.LiveData;
import MQTT.MQTThandler;

import SerialCommunication.StreamGen;
import com.google.gson.Gson;

import java.io.InputStream;
import java.io.OutputStream;



public class OBDreader implements Runnable {


    private static InputStream in;
    private static OutputStream out;





    private static OBDcollantTemperature collant = new OBDcollantTemperature();
    private static OBDDTCcount count = new OBDDTCcount();
    private static OBDfuelLevel fuel = new OBDfuelLevel();
    private static OBDfuelRate fuel2 = new OBDfuelRate();
    private static OBDkmh kmh = new OBDkmh();
    private static OBDoilTemperature oil = new OBDoilTemperature();
    private static OBDrpm rpm = new OBDrpm();
    private static OBDthrottlePosition trottle = new OBDthrottlePosition();
    private OBDreset reset = new OBDreset();
    private static MQTThandler obdData;





    private static Gson gson = new Gson();

    LiveData data = new LiveData();

    public OBDreader() {
    this.obdData=new MQTThandler();
    }

    @Override
    public void run() {

        while(!Checker.isAdapterConnected()){
        try {
            StreamGen gen = new StreamGen();
            out =gen.out();
            in = gen.in();
            Checker.setAdapterConnected(true);

        }catch (OBDUnableToConnectExeption e){
            System.out.println("adapter nicht verbunden");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }}


        try {
            while (true) {
                System.out.println("run");
                collant.run(in, out);
                System.out.println(collant.getResult());
                sendMessage("coolwatertemp",collant.getResult());
                count.run(in, out);
                sendMessage("dtccount",count.getResult());
                fuel.run(in, out);
                sendMessage("fuellevel",fuel.getResult());
                fuel2.run(in, out);
                sendMessage("fuelrate",fuel2.getResult());
                kmh.run(in, out);
                sendMessage("kmh",kmh.getResult());
                oil.run(in, out);
                sendMessage("oiltemp",oil.getResult());
                rpm.run(in, out);
                sendMessage("rpm",rpm.getResult());
                trottle.run(in, out);
                sendMessage("throttlepos",trottle.getResult());
                reset.run(in, out);
                System.out.println("obd");
                sendMessage("reset",reset.getResult());
                System.out.println("send");

            }
        }

        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
    private void sendMessage(String type, String message){
        data.type=type;
        data.datavalue =message;
        System.out.println(gson.toJson(data,LiveData.class));
        obdData.sendMessage("Live",gson.toJson(data,LiveData.class));
        System.out.println("send");
    }

}
