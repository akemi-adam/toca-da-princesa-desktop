package exceptions.validation;

public final class UnformattedDateException extends Exception
{
    public UnformattedDateException()
    {
        super("The date you entered is not correctly formed. Please enter a date that conforms to the following pattern: dd/mm/yyyy");
    }
}
