package com.opel;

public interface SerialCommand {

    double result(Byte[] rawData);
    Byte getCommand();
    String getUnit();


}

