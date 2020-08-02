package code.challenge.addressline.model;

import java.util.Objects;

/**
 * Model of Address to return.
 */
public class AddressJsonModel extends BaseJsonModel
{
    private String street;
    private String housenumber;

    /**
     * Default constructor.
     *
     * @param street street value
     * @param housenumber house number value
     */
    public AddressJsonModel(String street, String housenumber)
    {
        this.street = street;
        this.housenumber = housenumber;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public String getHousenumber()
    {
        return housenumber;
    }

    public void setHousenumber(String housenumber)
    {
        this.housenumber = housenumber;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        AddressJsonModel that = (AddressJsonModel) o;

        return Objects.equals(street, that.street) && Objects.equals(housenumber, that.housenumber);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(street, housenumber);
    }
}
