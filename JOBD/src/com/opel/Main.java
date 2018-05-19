package com.opel;



public class Main {

    public static void main(String[] args) {

        GUI test = new GUI();
        GUI.updateMQTTChecker(false);
        GUI.updateAdapterChecker(false);
        GUI.updateCarChecker(false);
        GUI.updateDBChecker(false);
        GUI.updateDTCChecker(0);
//test

        // MQTThandler test = new MQTThandler();
        //System.out.println("mqtt done");

      /*
  SerialPort[] liste = SerialPort.getCommPorts();
        for (SerialPort port : liste) {
            System.out.println(port.getDescriptivePortName());
        }
        OutputStream out = OutputstreamGen.out();
        InputStream in = InputstreamGen.in();






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
*/

    }

}

