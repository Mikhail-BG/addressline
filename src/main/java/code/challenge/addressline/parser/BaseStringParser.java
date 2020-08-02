package code.challenge.addressline.parser;

public class BaseStringParser
{
    private final String input;
    private final boolean isInputValid;

    protected BaseStringParser(String input, boolean isInputValid)
    {
        this.input = input;
        this.isInputValid = isInputValid;
    }

    public boolean isInputValid()
    {
        return isInputValid;
    }

    protected String getInput()
    {
        return input;
    }
}
