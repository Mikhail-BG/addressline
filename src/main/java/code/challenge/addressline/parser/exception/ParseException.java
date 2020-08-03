package code.challenge.addressline.parser.exception;

import code.challenge.addressline.logger.LocalLog;

/**
 * Exception for non-valid values provided to parse.
 */
public class ParseException extends Exception
{
    public ParseException(String errorMessage)
    {
        super(errorMessage);
        LocalLog.error(errorMessage);
    }
}
