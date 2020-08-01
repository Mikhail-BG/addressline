package code.challenge.addressline.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import code.challenge.addressline.logger.LocalLog;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

/**
 * Reads pre-defined property yaml file.
 */
public class YamlPropertyReader
{
    private static final String FILENAME = "/properties/properties.yml";

    protected YamlPropertyReader()
    {
    }

    /**
     * Reads all values in the property file into Map.
     *
     * @param section section name with properties
     * @return Map with properties
     */
    @SuppressWarnings("unchecked")
    protected static Map<String, String> readValues(String section)
    {
        Map<String, Map<String, String>> Properties = null;
        InputStream resource = RegexpProperties.class.getResourceAsStream(FILENAME);
        try
        {
            Properties = new ObjectMapper(new YAMLFactory()).readValue(resource, HashMap.class);
        }
        catch (IOException exception)
        {
            LocalLog.error("Error reading REGEXP values" + exception.getMessage());
            exception.printStackTrace();
        }

        if (Properties == null)
        {
            throw new RuntimeException("Error reading properties, file: " + FILENAME);
        }

        return Properties.get(section);
    }
}
