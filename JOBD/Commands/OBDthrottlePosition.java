package Commands;

public class OBDthrottlePosition extends OBDcommand {
    float percentage=0;
    public OBDthrottlePosition() {
        super("01 11");
    }

    @Override
    protected void calculateResult() {
        if(available) {
            percentage = buffer.get(4) * 100 / 255;
        }
    }

    @Override
    public String getResult(){
        if(available) {
            return Float.toString(percentage);
        }
        else{
            return "NODATA";
        }
    }
}
