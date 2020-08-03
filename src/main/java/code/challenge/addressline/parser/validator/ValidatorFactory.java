package code.challenge.addressline.parser.validator;

import java.util.ArrayList;
import java.util.List;

/**
 * Builds validator sets and does validations.
 */
public class ValidatorFactory
{
    private final String input;
    private final List<Validator> validators;

    /**
     * Default constructor.
     *
     * @param input provided value for validation
     */
    public ValidatorFactory(String input)
    {
        this.input = input;
        this.validators = new ArrayList<>();
    }

    /**
     * Factory method.
     * Add single word validation.
     *
     * @return this instance
     */
    public ValidatorFactory isSingleWord()
    {
        validators.add(new SingleWordValidator());

        return this;
    }

    /**
     * Factory method.
     * Add no digit validation.
     *
     * @return this instance
     */
    public ValidatorFactory isNoDigit()
    {
        validators.add(new NoDigitValidator());

        return this;
    }

    /**
     * Factory method.
     * Do validations.
     *
     * @return true if input is valid
     */
    public boolean validate()
    {
        return validators.stream().noneMatch(stringValidator -> stringValidator.validate(input));
    }
}
