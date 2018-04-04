package com.opel;

public class RPM_SerialCommand implements SerialCommand {

    @Override
    public double result(Byte[] rawData) {
        return 0;
    }

    @Override
    public Byte getCommand() {
        return null;
    }

    @Override
    public String getUnit() {
        return null;
    }
}
