package code.challenge.addressline.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import code.challenge.addressline.configuration.CommonProperties;
import code.challenge.addressline.configuration.RegexpProperties;
import code.challenge.addressline.model.AddressModel;

/**
 * Parses string of address into AddressModel.
 */
public final class AddressParser
{
    private Pattern pattern;
    private Matcher matcher;

    /**
     * Parses street and house number for the provided string line.
     *
     * @param stroke provided string line
     * @return Address model
     */
    public AddressModel parseAddress(String stroke)
    {
        findHouseNumber(splitWords(stroke));

        return new AddressModel();
    }

    private List<String> splitWords(String stroke)
    {
        pattern = Pattern.compile(CommonProperties.SPLITTER);

        return Arrays.asList(pattern.split(stroke).clone());
    }

    private List<String> findHouseNumber(List<String> words)
    {
        List<String> strWithDigits = new ArrayList<>();
        for (String word : words)
        {
            pattern = Pattern.compile(RegexpProperties.CONTAINS_DIGIT);
            matcher = pattern.matcher(word);
            if (matcher.find())
            {
                strWithDigits.add(word);
            }
        }

        return strWithDigits;
    }
}
