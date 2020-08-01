package code.challenge.addressline.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import code.challenge.addressline.configuration.CommonProperties;
import code.challenge.addressline.configuration.RegexpProperties;
import code.challenge.addressline.model.AddressModel;
import code.challenge.addressline.parser.validator.InputValidator;

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
        if (InputValidator.isInvalidInput(stroke))
        {
            return new AddressModel();
        }

        List<String> words = splitWords(stroke);
        String houseNumber = parseHouseNumber(words);
        String street = compileStreet(filterStreet(words, houseNumber));

        return new AddressModel(street, houseNumber);
    }

    private List<String> splitWords(String stroke)
    {
        pattern = Pattern.compile(CommonProperties.STR_SPLITTER);
        List<String> rawWords = Arrays.asList(pattern.split(stroke));

        return rawWords.stream().map(String::trim).collect(Collectors.toList());
    }

    private boolean isHasNumSight(List<String> words)
    {

        return false;
    }

    private String parseHouseNumber(List<String> words)
    {
        String houseNumber;
        List<String> houseNumbers = findHouseNumber(words);

        if (houseNumbers.size() > 2)
        {
            houseNumber = "complicated";
        }
        else
        {
            houseNumber = houseNumbers.get(0);
        }

        return houseNumber;
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

    private List<String> filterStreet(List<String> words, String houseNumber)
    {
        words.remove(houseNumber);

        return words;
    }

    private String compileStreet(List<String> words)
    {
        return String.join(CommonProperties.SPACE_SPLITTER, words);
    }
}
