package code.challenge.addressline.parser.exception;

import code.challenge.addressline.logger.LocalLog;

public class AddressParseException extends RuntimeException
{
    public AddressParseException(String errorMessage)
    {
        super(errorMessage);
        LocalLog.error(errorMessage);
    }
}
