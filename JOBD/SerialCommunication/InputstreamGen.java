package SerialCommunication;

import com.fazecast.jSerialComm.SerialPort;

import java.io.InputStream;

public class InputstreamGen {
    public static InputStream in(){
        return SerialPortSelector.findPort().getInputStream();

    }
}
