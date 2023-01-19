package exceptions;

public class NoEditableModelException extends Exception
{
    public NoEditableModelException()
    {
        super("This data is no longer editable");
    }

    public NoEditableModelException(String message)
    {
        super(message);
    }
}
