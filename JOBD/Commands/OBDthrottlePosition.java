package Commands;

public class OBDthrottlePosition extends OBDcommand {
    float percentage=0;
    public OBDthrottlePosition() {
        super("01 11");
    }

    @Override
    protected void calculateResult() {
        percentage = buffer.get(4)*100/255;
    }

    @Override
    public String getResult(){
        return Float.toString(percentage);
    }
}
