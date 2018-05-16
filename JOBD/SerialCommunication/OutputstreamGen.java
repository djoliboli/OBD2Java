package SerialCommunication;

import com.fazecast.jSerialComm.SerialPort;

import java.io.OutputStream;

public class OutputstreamGen {
    public static OutputStream out(){
        return SerialPortSelector.findPort().getOutputStream();
    }
}
