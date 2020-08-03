package code.challenge.addressline.parser.validator;

/**
 * Used for String validators.
 */
public interface Validator
{

    /**
     * Validates input value.
     *
     * @param input provided value for validation
     * @return true if valid
     */
    boolean validate(String input);
}
