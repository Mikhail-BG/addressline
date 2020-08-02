package code.challenge.addressline.configuration;

/**
 * Storage for common properties.
 */
public class CommonProperties extends YamlPropertyReader
{
    public static final String STR_SPLITTER;
    public static final String APP_SPLITTER;
    public static final String SPACE_SPLITTER;

    private static final String SECTION = "common";

    static
    {
        STR_SPLITTER = readValues(SECTION).get("strSplitter");
        APP_SPLITTER = readValues(SECTION).get("appSplitter");
        SPACE_SPLITTER = readValues(SECTION).get("spaceSplitter");
    }
}
