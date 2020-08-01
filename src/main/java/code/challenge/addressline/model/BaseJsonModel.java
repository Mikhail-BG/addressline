package code.challenge.addressline.model;

import org.apache.commons.lang3.StringUtils;

import code.challenge.addressline.logger.LocalLog;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Base class for JSON models
 */
public class BaseJsonModel
{
    public String toJson()
    {
        String json = StringUtils.EMPTY;

        try
        {
            json = new ObjectMapper().writeValueAsString(this);
        }
        catch (JsonProcessingException exception)
        {
            LocalLog.error("Error parsing Json: " + this.toString());
            exception.printStackTrace();
        }

        return json;
    }
}
