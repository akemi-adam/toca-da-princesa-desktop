package exceptions.validation;

public final class NullFieldException extends Exception
{
    private String field;

    public NullFieldException()
    {
        super("At least one of the fields must be filled out");
    }

    public NullFieldException(String errorMessage, String field)
    {
        super(errorMessage);

        this.field = field;
    }

    public String getField()
    {
        return this.field;
    }
}
