package Commands;

public class OBDDTCcount extends OBDcommand {
    protected int count = 0;
    boolean engineLampOn = false;
    public OBDDTCcount() {
        super("01 01");
    }

    @Override
    protected void calculateResult() {
        if (available) {
            count = buffer.get(4);
            if (count > 128) {
                engineLampOn = true;
                count = count - 128;
            }
        }
    }

    @Override
    public String getResult(){
        if(available) {
            return Integer.toString(count);
        }
        else{
            return "NODATA";
        }
    }

}
