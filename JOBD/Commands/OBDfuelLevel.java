package Commands;

public class OBDfuelLevel extends OBDcommand {
    float percentage=0;
    public OBDfuelLevel() {
        super("01 2F");
    }

    @Override
    protected void calculateResult() {
        if(available) {
            percentage = buffer.get(4) * 100 / 255;
        }
    }

    @Override
    public String getResult(){
        if (available) {
            return Float.toString(percentage);
        }
        else{
            return "NODATA";
        }
    }
}
