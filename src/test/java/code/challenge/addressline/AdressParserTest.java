package code.challenge.addressline;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import code.challenge.addressline.logger.LocalLog;
import code.challenge.addressline.parser.AddressStringParser;

class AddressParserTest
{
    @BeforeEach
    public void init(TestInfo testInfo)
    {
        LocalLog.info("Executed: " + testInfo.getDisplayName());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/SimpleTest.csv", numLinesToSkip = 1, delimiter = ';')
    public void simpleStringAddressParsing(String input, String expectedJson)
    {
        Assertions.assertEquals(expectedJson, new AddressStringParser(input).parseAddress().toFormattedJson());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/ComplicatedTest.csv", numLinesToSkip = 1, delimiter = ';')
    public void complicatedStringAddressParsing(String input, String expectedJson)
    {
        Assertions.assertEquals(expectedJson, new AddressStringParser(input).parseAddress().toFormattedJson());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/OtherCountries.csv", numLinesToSkip = 1, delimiter = ';')
    public void otherCountriesStringAddressParsing(String input, String expectedJson)
    {
        Assertions.assertEquals(expectedJson, new AddressStringParser(input).parseAddress().toFormattedJson());
    }
}
