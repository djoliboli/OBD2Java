package com.opel;

import com.fazecast.jSerialComm.SerialPort;
import static com.fazecast.jSerialComm.SerialPort.FLOW_CONTROL_XONXOFF_IN_ENABLED;
import static com.fazecast.jSerialComm.SerialPort.FLOW_CONTROL_XONXOFF_OUT_ENABLED;
import static com.fazecast.jSerialComm.SerialPort.TIMEOUT_READ_SEMI_BLOCKING;

public class OBD2Connector {
    private SerialPort[] allPorts= SerialPort.getCommPorts();
    private SerialPort OBD2adapter;
    private static int portNumber = 1;
    private int bautrate = 115200;
    private int stopBits = 1;
    private int dataBits= 8;
    private int readTimeOut= 100;
    private int writeTimeOut = 0;

    public  OBD2Connector(int port){
        OBD2adapter = allPorts[port];
        OBD2adapter.setBaudRate(bautrate);
        OBD2adapter.setNumStopBits(stopBits);
        OBD2adapter.setNumDataBits(dataBits);
        OBD2adapter.setFlowControl(FLOW_CONTROL_XONXOFF_IN_ENABLED);
        OBD2adapter.setFlowControl(FLOW_CONTROL_XONXOFF_OUT_ENABLED);
        OBD2adapter.setComPortTimeouts(TIMEOUT_READ_SEMI_BLOCKING,readTimeOut,writeTimeOut);
    }

    public OBD2Connector(){
        this(portNumber);
    }

    public boolean connect(){
        return OBD2adapter.openPort();
    }


}