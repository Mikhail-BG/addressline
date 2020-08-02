package code.challenge.addressline.model;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import code.challenge.addressline.logger.LocalLog;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Base class for JSON models.
 */
public class BaseJsonModel
{

    /**
     * Converts to customer JSON format.
     *
     * @return converted JSON
     */
    public String toFormattedJson()
    {
        String json = StringUtils.EMPTY;

        try
        {
            json = new ObjectMapper().writer()
                    .writeValueAsString(this)
                    .replaceAll("\":", "\": ")
                    .replaceAll("\",", "\", ");
        }
        catch (JsonProcessingException exception)
        {
            LocalLog.error("Error parsing Json: " + this.toString());
            exception.printStackTrace();
        }

        return json;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }
}
