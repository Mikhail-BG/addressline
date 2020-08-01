package code.challenge.addressline;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import code.challenge.addressline.configuration.DictionaryProperties;
import code.challenge.addressline.logger.LocalLog;
import code.challenge.addressline.parser.AddressParser;

class AddressParserTest
{
    private final AddressParser addressParser = new AddressParser();

    @BeforeEach
    public void init(TestInfo testInfo)
    {
        LocalLog.info("Executed: " + testInfo.getDisplayName());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/SimpleTest.csv", numLinesToSkip = 1, delimiter = ';')
    public void simpleStringAddressParsing(String solidLine, String expectedJson)
    {
        Assertions.assertEquals(expectedJson, addressParser.parseAddress(solidLine).toString());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/ComplicatedTest.csv", numLinesToSkip = 1, delimiter = ';')
    public void complicatedStringAddressParsing(String solidLine, String expectedJson)
    {
        Assertions.assertEquals(expectedJson, addressParser.parseAddress(solidLine).toString());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/OtherCountries.csv", numLinesToSkip = 1, delimiter = ';')
    public void otherCountriesStringAddressParsing(String solidLine, String expectedJson)
    {
        Assertions.assertEquals(expectedJson, addressParser.parseAddress(solidLine).toString());
    }
}
