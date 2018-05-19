package com.opel;


import Exeption.Checker;
import SerialCommunication.SerialPortSelector;
import Threads.OBDreader;

public class Main {
    static Thread t1;
    static int  x =0;

    public static void main(String[] args) throws InterruptedException {

        runReader();


    }

    public static void runReader() throws InterruptedException {

        t1 = new Thread(new OBDreader());
        t1.start();
        while ((!Checker.isAdapterConnected())||(!Checker.isMQTTConnected())) {
            System.out.println("nicht verbunden main");
            Thread.sleep(2000);
            if(x>10){
                x=0;
                t1.stop();
                Checker.setMQTTConnected(false);
                Checker.setAdapterConnected(false);
                System.out.println("10 Erfolglose Versuche :(");
                runReader();
            }
            x++;
        }
        while (SerialPortSelector.AdapterConnected()&&Checker.isMQTTConnected()) {
            System.out.println("verbunden2 "+ Checker.isMQTTConnected());

            Thread.sleep(1000);


        }
        t1.stop();

        Checker.setAdapterConnected(false);
        Checker.setMQTTConnected(false);

        runReader();

    }

}

