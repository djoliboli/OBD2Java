package Exeption;

import com.fazecast.jSerialComm.SerialPort;

public class Checker {

    public static boolean isAdapterConnected() {
        return AdapterConnected;
    }

    public static void setAdapterConnected(boolean adapterConnected) {
        AdapterConnected = adapterConnected;
    }

    public static boolean isCarConnected() {
        return CarConnected;
    }

    public static void setCarConnected(boolean carConnected) {
        CarConnected = carConnected;
    }

    public static boolean isMQTTConnected() {
        return MQTTConnected;
    }

    public static void setMQTTConnected(boolean MQTTConnected) {
        Checker.MQTTConnected = MQTTConnected;
    }

    public static boolean isDBConnected() {
        return DBConnected;
    }

    public static void setDBConnected(boolean DBConnected) {
        Checker.DBConnected = DBConnected;
    }

    private static boolean AdapterConnected = false;
    private static boolean CarConnected = false;
    private static boolean MQTTConnected = false;
    private static boolean DBConnected = false;


}
