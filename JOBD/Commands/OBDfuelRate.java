package Commands;

public class OBDfuelRate extends OBDcommand {
    float fuelRate=0;
    public OBDfuelRate() {
        super("01 5E");
    }

    @Override
    protected void calculateResult() {
        fuelRate = (256*buffer.get(4)+buffer.get(5))/20;
    }
}
