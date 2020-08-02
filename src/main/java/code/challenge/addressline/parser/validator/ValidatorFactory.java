package code.challenge.addressline.parser.validator;

import java.util.ArrayList;
import java.util.List;

public class ValidatorFactory
{
    private final String input;
    private final List<Validator> validators;

    public ValidatorFactory(String input)
    {
        this.input = input;
        this.validators = new ArrayList<>();
    }

    public ValidatorFactory isSingleWord()
    {
        validators.add(new SingleWordValidator());

        return this;
    }

    public ValidatorFactory isNoDigit()
    {
        validators.add(new NoDigitValidator());

        return this;
    }

    public boolean validate()
    {
        return validators.stream().noneMatch(stringValidator -> stringValidator.validate(input));
    }
}
