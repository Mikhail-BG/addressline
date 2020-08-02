package code.challenge.addressline.configuration;

/**
 * Storage for regexps.
 */
public class RegexpProperties extends YamlPropertyReader
{
    public static final String CONTAINS_DIGIT;
    private static final String SECTION = "regexps";

    static
    {
        CONTAINS_DIGIT = readValues(SECTION).get("containsDigit");
    }
}
