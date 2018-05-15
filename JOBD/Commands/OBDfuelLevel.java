package Commands;

public class OBDfuelLevel extends OBDcommand {
    float percentage=0;
    public OBDfuelLevel() {
        super("01 2F");
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
