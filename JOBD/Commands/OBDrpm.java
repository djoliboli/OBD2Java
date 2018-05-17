package Commands;

public class OBDrpm extends OBDcommand {
    int rpm = 0;
    public OBDrpm() {
        super("01 0C");
    }

    @Override
    protected void calculateResult() {
        if(available) {
            rpm = (buffer.get(4) * 256 + buffer.get(5)) / 4;
        }

    }

    public String getFormattedResult() {
        return String.format("%d%s", rpm, "rpm");
    }

    @Override
    public String getResult(){
        if(available) {
            return String.valueOf(rpm);
        }
        else {
            return "NODATA";
        }
    }
}
