package nu.educom.MI6.models.validations;

public class IntegerValidator implements IValidator {

    private int         test;
    private String      value;
    private String      error = null;
    protected boolean   valid = true;

    public IntegerValidator(String string) {
        setValue(string);
        validate();
    }

    protected void validate() {
        try {
            setTest(Integer.parseInt(getValue()));
        } catch (Exception e) {
            setError("Please enter a number");
            valid = false;
        }
    }

    @Override
    public boolean isValid() {
        return valid;
    }


    public int getTest() {
        return test;
    }

    public void setTest(int test) {
        this.test = test;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
