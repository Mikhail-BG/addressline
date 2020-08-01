package code.challenge.addressline.parser.validator;

import java.util.regex.Pattern;

import code.challenge.addressline.configuration.CommonProperties;
import code.challenge.addressline.configuration.RegexpProperties;
import code.challenge.addressline.logger.LocalLog;

public class InputValidator
{
    public static boolean isInvalidInput(String input)
    {
        return isSingleWord(input) || isNoDigit(input);
    }

    private static boolean isSingleWord(String input)
    {
        boolean isSingleWord = input.split(CommonProperties.STR_SPLITTER).length < 2;
        if (isSingleWord)
        {
            LocalLog.error("Input value contains one word: " + input);
        }

        return isSingleWord;
    }

    private static boolean isNoDigit(String input)
    {
        Pattern pattern = Pattern.compile(RegexpProperties.CONTAINS_DIGIT);
        boolean isNoDigit = !pattern.matcher(input).find();

        if (isNoDigit)
        {
            LocalLog.error("Input value does not include digits: " + input);
        }

        return isNoDigit;
    }
}
