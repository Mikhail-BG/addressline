package code.challenge.addressline;

import code.challenge.addressline.configuration.RegexpProperties;
import code.challenge.addressline.logger.LocalLog;
import code.challenge.addressline.model.AddressModel;
import code.challenge.addressline.parser.AddressParser;

public class AppRunner
{
    public static void main(String[] args)
    {
        LocalLog.info("AppStarted");
        AddressModel addressModel = new AddressModel();
        addressModel.setStreet("Street");
        addressModel.setHousenumber("123B");

        System.out.println(RegexpProperties.CONTAINS_DIGIT);

        AddressParser parser = new AddressParser();
        System.out.println(parser.parseAddress("Winterallee 3").toJson());


    }
}
