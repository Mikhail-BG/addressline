package code.challenge.addressline.parser.exception;

/**
 * Exception for non-valid values provided to parse.
 */
public class ParseException extends Exception
{
    public ParseException(String errorMessage)
    {
        super(errorMessage);
    }
}
