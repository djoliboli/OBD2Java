package MQTT;

import Config.config;
import Exeption.Checker;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MQTThandler {
    MemoryPersistence persistence = new MemoryPersistence();

    MqttClient client;

    {
        try {
            client = new MqttClient("tcp://" + config.MQTTIP + ":" + config.MQTTport, "OBDSERVER", persistence);
            Checker.setMQTTConnected(true);
        } catch (MqttException e) {
            e.printStackTrace();
            Checker.setMQTTConnected(false);
        }
    }

    public MQTThandler() {
        try {

            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            System.out.println("Connecting to broker: " + config.MQTTIP);
            client.connect(connOpts);
            System.out.println("Connected");

            client.close();
            System.exit(0);
        } catch (Exception e) {
            Checker.setMQTTConnected(false);
            System.out.println(e.getMessage());
        }

    }

    public boolean sendMessage(String topic, String messageString) {
        System.out.println("message: " + messageString);
        System.out.println("Topic: " + topic);
        MqttMessage message = new MqttMessage(messageString.getBytes());
        message.setQos(0);
        try {
            client.publish(topic, message);
            System.out.println("Message published");
            return true;
        } catch (MqttException e) {
            return false;
        }

    }
}
