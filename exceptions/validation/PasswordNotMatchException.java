package exceptions.validation;

public final class PasswordNotMatchException extends Exception
{
    public PasswordNotMatchException(String errorMessage)
    {
        super(errorMessage);
    }
}
