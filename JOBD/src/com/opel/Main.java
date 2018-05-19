package com.opel;


import Exeption.Checker;
import MQTT.MQTThandler;

import SerialCommunication.SerialPortSelector;
import Threads.OBDreader;
import Threads.Reader;
import org.eclipse.paho.client.mqttv3.MqttClient;

public class Main {
    static Thread t2;
    static String[] test;
    public static void main(String[] args) throws InterruptedException {
        t2= new Thread(new Reader());
        int n =1;
        MQTThandler mainmenu = new MQTThandler();
        mainmenu.subscribe("obd2controller");




    }


    public static void newMessage(String Message) {

        System.out.println("new Message");

        t2.stop();
        Checker.setMQTTConnected(false);
        t2 = new Thread(new Reader());
        System.out.println(Message);

        t2.start();

    }


}

