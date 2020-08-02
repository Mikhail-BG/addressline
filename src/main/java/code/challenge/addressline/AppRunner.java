package code.challenge.addressline;

import code.challenge.addressline.configuration.RegexpProperties;
import code.challenge.addressline.logger.LocalLog;
import code.challenge.addressline.model.AddressJsonModel;
import code.challenge.addressline.parser.AddressStringParser;

public class AppRunner
{
    public static void main(String[] args)
    {
        LocalLog.info("AppStarted");
        AddressJsonModel addressJsonModel = new AddressJsonModel();
        addressJsonModel.setStreet("Street");
        addressJsonModel.setHousenumber("123B");

        System.out.println(RegexpProperties.CONTAINS_DIGIT);

        AddressStringParser parser = new AddressStringParser("Winterallee 3");
        System.out.println(parser.parseAddress().toFormattedJson());


    }
}
