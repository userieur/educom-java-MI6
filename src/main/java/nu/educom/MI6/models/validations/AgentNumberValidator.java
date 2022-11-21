package nu.educom.MI6.models.validations;

public class AgentNumberValidator extends IntegerValidator{
    public AgentNumberValidator(String string) {
        super(string);
    }

    @Override
    protected void validate() {
        super.validate();
        if (valid && !(0< getTest() && getTest() < 957)) {
            setError("Please enter a number between 1-956");
            valid = false;
        } else {
            setValue(String.format("%03d", getTest()));
        }
    }
}
