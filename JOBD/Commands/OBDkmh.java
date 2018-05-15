package Commands;

public class OBDkmh extends OBDcommand
{
    int kmh = -1;

    public OBDkmh() {
        super("01 0D");
    }

    @Override
    protected void calculateResult() {
        kmh = buffer.get(4);
    }
    @Override
    public String getResult(){
        return Integer.toString(kmh);
    }
}
