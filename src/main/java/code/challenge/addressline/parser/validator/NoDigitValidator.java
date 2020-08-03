package code.challenge.addressline.parser.validator;

import java.util.regex.Pattern;

import code.challenge.addressline.configuration.RegexpProperties;

/**
 * Verifies if input contains any digit values.
 */
public class NoDigitValidator implements Validator
{
    @Override
    public boolean validate(String input)
    {
        Pattern pattern = Pattern.compile(RegexpProperties.CONTAINS_DIGIT);

        return !pattern.matcher(input).find();
    }
}
