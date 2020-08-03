package code.challenge.addressline.parser.validator;

import code.challenge.addressline.configuration.CommonProperties;

/**
 * Verifies if input value contains at least 2 words.
 */
public class SingleWordValidator implements Validator
{
    @Override
    public boolean validate(String input)
    {
        return input.split(CommonProperties.STR_SPLITTER).length < 2;
    }
}
