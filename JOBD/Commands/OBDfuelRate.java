package Commands;

public class OBDfuelRate extends OBDcommand {
    float fuelRate=0;
    public OBDfuelRate() {
        super("01 5E");
    }

    @Override
    protected void calculateResult() {
        if (available) {
            fuelRate = (256 * buffer.get(4) + buffer.get(5)) / 20;
        }
    }
    @Override
    public String getResult(){
        if(available){
            return Float.toString(fuelRate);
        }
        else {
            return "NODATA";
        }
    }
}
