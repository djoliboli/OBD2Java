package Commands;

public class OBDtemperature extends OBDcommand {
    int temperature=0;
    public OBDtemperature(String command) {
        super(command);
    }

    @Override
    protected void calculateResult() {
      temperature = buffer.get(4)-40;
    }

    @Override
    public String getResult(){
        return Integer.toString(temperature);
    }
}
