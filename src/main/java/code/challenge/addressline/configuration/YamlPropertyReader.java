package code.challenge.addressline.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import code.challenge.addressline.logger.LocalLog;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

/**
 * Reads pre-defined properties from yaml file.
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
     * @return Map with property name and properties
     */
    protected static Map<String, String> readValues(String section)
    {
        Map<String, Map<String, String>> properties = readProperties();

        return properties.get(section);
    }

    /**
     * Reads all values in the property file into Map.
     *
     * @param section section name with properties
     * @return Map with property name and arrays of properties
     */
    @SuppressWarnings("rawtypes")
    protected static Map<String, List<String>> readListValues(String section)
    {
        Map<String, Map<String, List>> properties = readProperties();
        Map<String, List<String>> sectionProperties = new HashMap<>();
        Map<String, List> rawSectionProperties = properties.get(section);
        for (String key : rawSectionProperties.keySet())
        {
            List<String> values = new ArrayList<>();
            for (Object value : rawSectionProperties.get(key))
            {
                values.add((String) value);
            }
            sectionProperties.put(key, values);
        }

        return sectionProperties;
    }

    @SuppressWarnings("unchecked")
    private static <T> Map<String, Map<String, T>> readProperties()
    {
        Map<String, Map<String, T>> properties = null;

        try (InputStream resource = RegexpProperties.class.getResourceAsStream(FILENAME))
        {
            properties = new ObjectMapper(new YAMLFactory()).readValue(resource, Map.class);
        }
        catch (IOException exception)
        {
            LocalLog.fatal("Error reading properties, file: " + FILENAME + "\n" + exception.getMessage());
            exception.printStackTrace();
        }

        return properties;
    }
}
