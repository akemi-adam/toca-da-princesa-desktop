package exceptions.validation;

public final class NotIntegerValueException extends Exception
{
    public NotIntegerValueException()
    {
        super("The value you entered is invalid. Please enter a number.");
    }
}
