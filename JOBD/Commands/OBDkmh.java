package Commands;

public class OBDkmh extends OBDcommand
{
    int kmh = -1;

    public OBDkmh() {
        super("01 0D");
    }

    @Override
    protected void calculateResult() {
        if(available) {
            kmh = buffer.get(4);
        }
    }
    @Override
    public String getResult(){
        if(available) {
            return Integer.toString(kmh);
        }
        else {
            return "NODATA";
        }
    }
}
