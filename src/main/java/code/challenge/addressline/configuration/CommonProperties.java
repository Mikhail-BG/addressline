package code.challenge.addressline.configuration;

/**
 * Storage for common properties
 */
public class CommonProperties extends YamlPropertyReader
{
    public static final String SPLITTER;
    private static final String SECTION = "common";

    static
    {
        SPLITTER = readValues(SECTION).get("splitter");
    }
}
