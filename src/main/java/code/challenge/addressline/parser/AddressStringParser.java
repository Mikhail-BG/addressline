package code.challenge.addressline.parser;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import code.challenge.addressline.configuration.CommonProperties;
import code.challenge.addressline.configuration.DictionaryProperties;
import code.challenge.addressline.configuration.RegexpProperties;
import code.challenge.addressline.model.AddressJsonModel;
import code.challenge.addressline.parser.exception.AddressParseException;
import code.challenge.addressline.parser.validator.ValidatorFactory;

/**
 * Parses string of address into AddressModel.
 */
public final class AddressStringParser extends BaseStringParser
{
    private static final String ERROR_MSG = "Invalid input value: \"%s\"";

    private Pattern pattern;
    private final List<String> inputWords;

    public AddressStringParser(String input)
    {
        super(
                input,
                new ValidatorFactory(input)
                        .isSingleWord()
                        .isNoDigit()
                        .validate());

        this.inputWords = splitWords(getInput());
    }

    /**
     * Parses street and house number for the provided string line.
     *
     * @return Address model if parsed
     */
    public AddressJsonModel parseAddress()
    {
        if (!isInputValid())
        {
            throw new AddressParseException(String.format(ERROR_MSG, getInput()));
        }

        String houseNumber = parseHouseNumber();
        String street = compileStreet();

        return new AddressJsonModel(street, houseNumber);
    }

    private List<String> splitWords(String stroke)
    {
        pattern = Pattern.compile(CommonProperties.STR_SPLITTER);
        List<String> rawWords = Arrays.asList(pattern.split(stroke));

        return rawWords.stream().map(String::trim).filter(StringUtils::isNotEmpty).collect(Collectors.toList());
    }

    private String parseHouseNumber()
    {
        String houseNumber;
        int positionOfNumSign = positionOfHouseNumberSight(inputWords);

        // Consider number sign in the beginning and house number follows after number sign
        if (positionOfNumSign == 0)
        {
            houseNumber = compileHouseNoNumberSign();
        }
        else
        {
            houseNumber = compileHouseNumberWithNumberSign(positionOfNumSign);
        }

        return houseNumber;
    }

    private String compileHouseNoNumberSign()
    {
        List<String> houseNumberWords;
        Map<Integer, String> positionAndNumbers = findNumbers(inputWords);
        int startNumberPosition = positionAndNumbers.keySet().iterator().next() - 1;
        int endNumberPosition = startNumberPosition + 1;

        if (endNumberPosition < inputWords.size() && isWordIndexAtPosition(endNumberPosition))
        {
            endNumberPosition += 1;
        }

        houseNumberWords = filterHouseNumberWords(startNumberPosition, endNumberPosition);
        filterStreetWords(houseNumberWords);

        return compileHouseNumber(houseNumberWords);
    }

    private String compileHouseNumberWithNumberSign(int positionOfNumSign)
    {
        List<String> houseNumberWords = new ArrayList<>();
        houseNumberWords.add(inputWords.get(positionOfNumSign - 1));
        houseNumberWords.addAll(filterHouseNumberWords(positionOfNumSign, inputWords.size()));
        filterStreetWords(houseNumberWords);
        return compileHouseNumber(houseNumberWords);
    }

    private int positionOfHouseNumberSight(List<String> words)
    {
        int position = 0;
        for (String word : words)
        {
            position += 1;
            if (DictionaryProperties.NUM_SIGHT_DICTIONARY
                    .stream().anyMatch(dictWord -> dictWord.equalsIgnoreCase(word)))
            {
                break;
            }
        }

        return position == words.size() ? 0 : position;
    }

    private Map<Integer, String> findNumbers(List<String> words)
    {
        Map<Integer, String> strWithDigits = new HashMap<>();
        int position = 0;
        for (String word : words)
        {
            position += 1;
            pattern = Pattern.compile(RegexpProperties.CONTAINS_DIGIT);
            Matcher matcher = pattern.matcher(word);
            if (matcher.find())
            {
                strWithDigits.put(position, word);
            }
        }

        return strWithDigits;
    }

    private boolean isWordIndexAtPosition(int position)
    {
        return inputWords.get(position).length() == 1;
    }

    private List<String> filterHouseNumberWords(int fromIndex, int toIndex)
    {
        List<String> copyInputWords = new ArrayList<>(inputWords);

        return copyInputWords.subList(fromIndex, toIndex);
    }

    private void filterStreetWords(List<String> houseNumberWords)
    {
        inputWords.removeAll(houseNumberWords);
    }

    private String compileValue(List<String> words)
    {
        return String.join(CommonProperties.SPACE_SPLITTER, words);
    }

    private String compileStreet()
    {
        return compileValue(inputWords);
    }

    private String compileHouseNumber(List<String> words)
    {
        return (compileValue(words));
    }
}
