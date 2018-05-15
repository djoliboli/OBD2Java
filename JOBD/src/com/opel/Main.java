package com.opel;
import Commands.*;
import com.fazecast.jSerialComm.*;


import java.io.InputStream;
import java.io.OutputStream;


import static com.fazecast.jSerialComm.SerialPort.FLOW_CONTROL_XONXOFF_IN_ENABLED;
import static com.fazecast.jSerialComm.SerialPort.FLOW_CONTROL_XONXOFF_OUT_ENABLED;

import static java.lang.Thread.sleep;

public class Main {



    public static void main(String[] args) throws Exception {
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


        OBDcollantTemperature collant = new OBDcollantTemperature();

        OBDDTCcount count = new OBDDTCcount();

        OBDfuelLevel fuel = new OBDfuelLevel();

        OBDfuelRate fuel2 = new OBDfuelRate();

        OBDkmh kmh = new OBDkmh();

        OBDoilTemperature oil = new OBDoilTemperature();

        OBDrpm rpm = new OBDrpm();

        OBDthrottlePosition trottle = new OBDthrottlePosition();

        OBDreset reset = new OBDreset();

        collant.run(in,out);
        count.run(in,out);
        fuel.run(in,out);
        fuel2.run(in,out);
        kmh.run(in,out);
        oil.run(in,out);
        rpm.run(in,out);
        trottle.run(in,out);
        System.out.println("Coollanttemp: " + collant.getResult());
        System.out.println("DTC Count: " + count.getResult());
        System.out.println("FuelLevel: " + fuel.getResult());
        System.out.println("FuelRate: " + fuel2.getResult());
        System.out.println("KMH: " + kmh.getResult());
        System.out.println("OIL: " + oil.getResult());
        System.out.println("RPM: " + rpm.getResult());
        System.out.println("Trootle: " + trottle.getResult());
        System.out.println("All Done");

        reset.run(in,out);
        System.out.println(reset.getResult());


        }

    }

