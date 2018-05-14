package com.opel;
import Commands.OBDreset;
import Commands.OBDrpm;
import com.fazecast.jSerialComm.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static com.fazecast.jSerialComm.SerialPort.FLOW_CONTROL_XONXOFF_IN_ENABLED;
import static com.fazecast.jSerialComm.SerialPort.FLOW_CONTROL_XONXOFF_OUT_ENABLED;

import static java.lang.Thread.sleep;

public class Main {



    public static void main(String[] args) throws IOException, InterruptedException {
        OutputStream out;
        InputStream in;

        int i;
        SerialPort[] liste = SerialPort.getCommPorts();
        for (SerialPort port : liste
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
        if (obd.openPort()) {
            System.out.println("Verbindung Steht");

        }
        in = obd.getInputStream();
        out = obd.getOutputStream();

        OBDrpm rpmmeter = new OBDrpm();
        for(int c = 0; c<5000; c++) {
            rpmmeter.run(in, out);
            System.out.println(rpmmeter.getResult());
            Thread.sleep(100);
        }

    }
}
