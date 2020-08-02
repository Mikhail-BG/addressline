package code.challenge.addressline.parser;

/**
 * Base class for String parsers.
 */
public class BaseStringParser
{
    private final String input;
    private final boolean isInputValid;

    /**
     * Default constructor.
     *
     * @param input provided input.
     * @param isInputValid true if input passed validations
     */
    protected BaseStringParser(String input, boolean isInputValid)
    {
        this.input = input;
        this.isInputValid = isInputValid;
    }

    protected boolean isInputValid()
    {
        return isInputValid;
    }

    protected String getInput()
    {
        return input;
    }
}
