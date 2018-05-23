package MQTT;

import Config.config;
import Exeption.Checker;
import com.opel.Main;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MQTThandler {
    MemoryPersistence persistence = new MemoryPersistence();
    MqttClient client;
  public MQTThandler() {



        try {
            client = new MqttClient("tcp://" + config.MQTTIP + ":" + config.MQTTport, Double.toString(Math.random()), persistence);
            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable throwable) {
                    Checker.setMQTTConnected(false);
                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");


                }

                @Override
                public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                    Main.newMessage(mqttMessage.toString());

                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

                }
            });

        } catch (MqttException e) {
            e.printStackTrace();
            Checker.setMQTTConnected(false);
        }
                    while (!Checker.isMQTTConnected()) {
                        try {

                            MqttConnectOptions connOpts = new MqttConnectOptions();
                            connOpts.setCleanSession(true);
                            connOpts.setConnectionTimeout(3);



                            System.out.println("Connecting to broker: " + config.MQTTIP);
                            client.connect(connOpts);
                            Checker.setMQTTConnected(true);
                            System.out.println("hello i am connected");


                        } catch (Exception e) {
                            Checker.setMQTTConnected(false);
                            System.out.println(e.getMessage());

                        }

                    }
                }


    public boolean sendMessage(String topic, String messageString) {
        System.out.println("message: " + messageString);
        System.out.println("Topic: " + topic);
        MqttMessage message = new MqttMessage(messageString.getBytes());
        message.setQos(0);
        try {
            this.client.publish(topic, message);
            System.out.println("Message published");
            return true;
        } catch (MqttException e) {
            System.out.println(e.getMessage());
            try {
                client.reconnect();
            } catch (MqttException e1) {
                e1.printStackTrace();
                System.exit(1);
            }
            return false;
        }

    }
    public boolean subscribe(String topic){
        try {
            client.subscribe(topic);
            return true;
        } catch (MqttException e) {
            e.printStackTrace();
            return false;
        }

    }


}
