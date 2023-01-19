package exceptions.validation;

public final class WrongPasswordException extends Exception
{
    public WrongPasswordException()
    {
        super("The password you entered is wrong. Please enter a valid password.");
    }
}
