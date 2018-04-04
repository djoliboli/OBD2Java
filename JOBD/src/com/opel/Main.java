package com.opel;
import com.fazecast.jSerialComm.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static com.fazecast.jSerialComm.SerialPort.FLOW_CONTROL_XONXOFF_IN_ENABLED;
import static com.fazecast.jSerialComm.SerialPort.FLOW_CONTROL_XONXOFF_OUT_ENABLED;

import static java.lang.Thread.sleep;

public class Main {



    public static void main(String[] args) {
        OutputStream out;
        InputStream in;
        StringBuilder response = new StringBuilder();
        String reset = "E0\r\n";
        String test ="01 0C\r\n";
        int i;
        SerialPort[] liste = SerialPort.getCommPorts();
        for (SerialPort port:liste
                ) {
            System.out.println(port.getDescriptivePortName());
        }
        SerialPort obd = liste[1];
        obd.closePort();
        obd.setBaudRate(115200);
        obd.setNumStopBits(1);
        obd.setNumDataBits(8);
        obd.setFlowControl(FLOW_CONTROL_XONXOFF_IN_ENABLED);
        obd.setFlowControl(FLOW_CONTROL_XONXOFF_OUT_ENABLED);
        obd.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 100, 0);
        if (obd.openPort()){
            System.out.println("Verbindung Steht");

        }
        in = obd.getInputStream();
        out = obd.getOutputStream();


        try {

            //System.out.println(obd.writeBytes(reset.getBytes(),(long)reset.getBytes().length));
            out.write(reset.getBytes());
            sleep(4000);

            /*byte[] buffer = new byte[obd.bytesAvailable()];

            System.out.println(obd.readBytes(buffer,obd.bytesAvailable()));
            for (byte b:buffer) {
                System.out.println((char)b);}*/

            while (( i = in.read())!= 62){
                //System.out.println((char)i);
            }
            out.write(test.getBytes());
            sleep(4000);

            /*byte[] buffer = new byte[obd.bytesAvailable()];

            System.out.println(obd.readBytes(buffer,obd.bytesAvailable()));
            for (byte b:buffer) {
                System.out.println((char)b);}*/

            while (( i = in.read())!= 62){

                response.append((char)i);
            }
            String response2 = response.toString().replaceAll("\r","\0");
            System.out.println(response2);



        } catch (Exception e) {
            e.printStackTrace();
        }

        try {

            obd.closePort();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
