package code.challenge.addressline;


import org.junit.jupiter.api.AfterEach;
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
        LocalLog.info("Started test: " + testInfo.getTestMethod().orElseThrow().getName() +
                "\nwith test data: " + testInfo.getDisplayName());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/SimpleTest.csv", numLinesToSkip = 1, delimiter = ';')
    public void simpleStringAddressParsing(String input, String expectedJson)
    {
        Assertions.assertEquals(expectedJson, getAndLogActualJson(input));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/ComplicatedTest.csv", numLinesToSkip = 1, delimiter = ';')
    public void complicatedStringAddressParsing(String input, String expectedJson)
    {
        Assertions.assertEquals(expectedJson, getAndLogActualJson(input));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/OtherCountries.csv", numLinesToSkip = 1, delimiter = ';')
    public void otherCountriesStringAddressParsing(String input, String expectedJson)
    {
        Assertions.assertEquals(expectedJson, getAndLogActualJson(input));
    }

    @AfterEach
    public void tearDown(TestInfo testInfo)
    {
        LocalLog.info("Executed test: " + testInfo.getTestMethod().orElseThrow().getName());
    }

    private String getAndLogActualJson(String input)
    {
        String actualJson = new AddressStringParser(input).parseAddress().toFormattedJson();
        LocalLog.info("Parsed JSON: " + actualJson);

        return actualJson;
    }
}