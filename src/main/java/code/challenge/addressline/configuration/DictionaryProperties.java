package code.challenge.addressline.configuration;

import java.util.List;

/**
 * Storage for dictionaries.
 */
public class DictionaryProperties extends YamlPropertyReader
{
    public static final List<String> NUM_SIGHT_DICTIONARY;
    private static final String SECTION = "dictionary";

    static
    {
        NUM_SIGHT_DICTIONARY = readListValues(SECTION).get("numberSights");
    }
}
