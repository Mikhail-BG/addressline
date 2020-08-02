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
import code.challenge.addressline.parser.exception.ParseException;
import code.challenge.addressline.parser.validator.ValidatorFactory;

/**
 * Parses string of address into AddressModel.
 */
public final class AddressStringParser extends BaseStringParser
{
    private static final String ERROR_MSG = "Invalid input value: \"%s\"";

    private Pattern pattern;
    private List<String> inputWords;

    /**
     * Default constructor.
     *
     * @param input provided String input value
     */
    public AddressStringParser(String input)
    {
        // Setup input value and validators for the particular String parser
        super(
                input,
                new ValidatorFactory(input)
                        .isSingleWord()
                        .isNoDigit()
                        .validate()
        );

        this.inputWords = new ArrayList<>();
    }

    /**
     * Parses street name and house number for the provided string line.
     *
     * @return Address model if parsed
     * @throws ParseException in case if validation fails.
     */
    public AddressJsonModel parseAddress()
    {
        if (!isInputValid())
        {
            throw new ParseException(String.format(ERROR_MSG, getInput()));
        }

        // If validations passed then do parsing
        inputWords = splitWords(getInput());

        String houseNumber = parseHouseNumber();
        String street = compileValue(inputWords);

        return new AddressJsonModel(street, houseNumber);
    }

    private List<String> splitWords(String stroke)
    {
        pattern = Pattern.compile(CommonProperties.STR_SPLITTER);
        List<String> rawWords = Arrays.asList(pattern.split(stroke));

        return rawWords.stream().map(String::strip).filter(StringUtils::isNotEmpty).collect(Collectors.toList());
    }

    private String parseHouseNumber()
    {
        String houseNumber;
        int positionOfNumSign = positionOfHouseNumberSight(inputWords);

        // Case without house number sign
        if (positionOfNumSign == -1)
        {
            houseNumber = compileHouseNoNumberSign(new ArrayList<>(), inputWords);
        }
        // Case with house number sign
        else
        {
            houseNumber = compileHouseNumberWithNumberSign(positionOfNumSign);
        }

        return houseNumber;
    }

    private String compileHouseNoNumberSign(List<String> houseNumberWords, List<String> words)
    {
        Map<Integer, String> positionAndNumbers = findNumbers(words);
        int startNumberPosition = positionAndNumbers.keySet().iterator().next() - 1;
        int endNumberPosition = startNumberPosition + 1;

        // Check if next word is house number index
        if (endNumberPosition < words.size() && isWordIndexAtPosition(words, endNumberPosition))
        {
            endNumberPosition += 1;
        }

        houseNumberWords.addAll(filterHouseNumberWords(words, startNumberPosition, endNumberPosition));
        filterStreetWords(houseNumberWords);

        return compileValue(houseNumberWords);
    }

    private String compileHouseNumberWithNumberSign(int positionOfNumSign)
    {
        List<String> houseNumberWords = new ArrayList<>();
        houseNumberWords.add(inputWords.get(positionOfNumSign));

        return compileHouseNoNumberSign(
                houseNumberWords,
                filterHouseNumberWords(inputWords, positionOfNumSign + 1, inputWords.size()));
    }

    private int positionOfHouseNumberSight(List<String> words)
    {
        int position = 0;
        for (String word : words)
        {
            if (DictionaryProperties.NUM_SIGHT_DICTIONARY
                    .stream().anyMatch(dictWord -> dictWord.equalsIgnoreCase(word)))
            {
                break;
            }
            position += 1;
        }

        return position == words.size() ? -1 : position;
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

    private boolean isWordIndexAtPosition(List<String> words, int position)
    {
        // Assume index is one sign length
        return words.get(position).length() == 1;
    }

    private List<String> filterHouseNumberWords(List<String> words, int fromIndex, int toIndex)
    {
        return words.subList(fromIndex, toIndex);
    }

    private void filterStreetWords(List<String> houseNumberWords)
    {
        inputWords.removeAll(houseNumberWords);
    }

    private String compileValue(List<String> words)
    {
        return String.join(CommonProperties.SPACE_SPLITTER, words);
    }
}
