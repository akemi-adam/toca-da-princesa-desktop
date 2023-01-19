package enums;

public enum Type
{
    CAT("Cat"),
    DOG("Dog");

    private String type;

    private Type(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return this.type;
    }
}
