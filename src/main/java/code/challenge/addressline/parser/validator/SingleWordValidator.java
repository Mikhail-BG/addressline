package code.challenge.addressline.parser.validator;

import code.challenge.addressline.configuration.CommonProperties;

/**
 * Verifies if input value includes more than 1 word.
 */
public class SingleWordValidator implements Validator
{
    @Override
    public boolean validate(String input)
    {
        return input.split(CommonProperties.STR_SPLITTER).length < 2;
    }
}
