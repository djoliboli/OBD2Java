package com.opel;


import Commands.OBDdelete;
import Exeption.Checker;
import MQTT.MQTThandler;
import SerialCommunication.SerialPortSelector;
import Threads.DeleteError;
import Threads.ErrorReader;
import Threads.Reader;

public class Main {
    static Thread activeThread = null;


    public static void main(String[] args) throws InterruptedException {
        GUI.createGui();
       MQTThandler mainmenu = new MQTThandler();
       mainmenu.subscribe("Control");
    }


    public static void newMessage(String Message) throws InterruptedException {


        System.out.println("new Message");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        Checker.setMQTTConnected(false);
        System.out.println(activeThread == null);
        if (SerialPortSelector.AdapterConnected()) {
            SerialPortSelector.rightPort.closePort();
        }
        Checker.setAdapterConnected(false);
        if (activeThread != null) {
            if(Reader.t1!=null){
            Reader.t1.interrupt();
            Reader.t1.stop();}
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
                activeThread = new Thread(new ErrorReader());
                activeThread.start();
                System.out.println("Error started");
                break;
            case "Delete":
                System.out.println("Delete");
                activeThread = new Thread(new DeleteError());
                System.out.println("Deleted");
                break;
            default:
                System.out.println("Keine Gueltige Anweisung");

        }

        System.out.println(Message);

        //activeThread.start();

    }


}

