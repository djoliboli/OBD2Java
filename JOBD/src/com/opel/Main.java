package com.opel;


import Exeption.Checker;
import MQTT.MQTThandler;
import SerialCommunication.SerialPortSelector;
import Threads.Reader;

public class Main {
    static Thread activeThread = null;

    public static void main(String[] args) throws InterruptedException {


        //MQTThandler mainmenu = new MQTThandler();
        //mainmenu.subscribe("Control");
    newMessage("Live");

    }


    public static void newMessage(String Message) throws InterruptedException {

        System.out.println("new Message");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(activeThread == null);
        if (SerialPortSelector.AdapterConnected()) {
            SerialPortSelector.rightPort.closePort();
        }
        Checker.setAdapterConnected(false);
        if (activeThread != null) {
            Reader.t1.interrupt();
            Reader.t1.stop();
            activeThread.stop();
            Thread.sleep(1000);

        }
        switch (Message) {
            case "Live":
                System.out.println("LIVE");

                activeThread = new Thread(new Reader());
                activeThread.start();
                System.out.println("Live started");
                break;
            case "Error":
                System.out.println("ERROR");
                activeThread = new Thread(new Reader());
                activeThread.start();
                System.out.println("Live started");
                break;
            case "Delete":
                System.out.println("");
                break;
            default:
                System.out.println("Keine Gueltige Anweisung");

        }
        Checker.setMQTTConnected(false);
        System.out.println(Message);

        //activeThread.start();

    }


}

