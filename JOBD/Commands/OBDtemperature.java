package Commands;

public class OBDtemperature extends OBDcommand {
    int temperature=0;
    public OBDtemperature(String command) {
        super(command);
    }

    @Override
    protected void calculateResult() {
        if (available) {
            temperature = buffer.get(4) - 40;
        }
    }

    @Override
    public String getResult(){
        if(available) {
            return Integer.toString(temperature);
        }
        else {
         return "NODATA";
        }
    }

}
