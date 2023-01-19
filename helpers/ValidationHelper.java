package helpers;

import java.util.regex.Pattern;

import exceptions.validation.NullFieldException;
import exceptions.validation.UnformattedDateException;

public abstract class ValidationHelper
{
    private final static Pattern IS_NUMBER_REGEX = Pattern.compile("-?\\d+(\\.\\d+)?");

    private final static String DATE_FORMAT = "(0[1-9]|1[0-9]|2[0-9]|3[0-1]|[1-9])/(0[1-9]|1[0-2]|[1-9])/([0-9]{4})";

    /**
     * Checks whether the entered text field is null or empty. If yes, throws an exception.
     * 
     * @param field | A String
     * @throws NullFieldException
     * 
     * @return void
     */
    public static void throwNullFieldException(String field) throws NullFieldException
    {
        if (field == null || field == "")
            throw new NullFieldException("All field needs to be filled in", field);
    }

    /**
     * Checks if string contains only numbers
     * 
     * @param text
     * @return
     */
    public static boolean isNumber(String text)
    {
        return IS_NUMBER_REGEX.matcher(text).matches();
    }

    public static void throwUnformattedDateException(String date) throws UnformattedDateException
    {
        if (!date.matches(DATE_FORMAT))
            throw new UnformattedDateException();
    }

    /**
     * Returns the date in "DD/MM/YYYY" format
     * 
     * @param date | Date in format "YYYY-MM-DD"
     * 
     * @return the date in "DD/MM/YYYY" format
     */
    public static String getDateFormat(String date)
    {
        String[] array = date.split("-");

        return array[2] + "/" + array[1] + "/" + array[0];
    }

    /**
     * Checks the option selected by the user (in Portuguese). In case of having marked "No" or "Doesn't have", returns 0. In case of "Yes", returns 1.
     * 
     * @param option | Button radio text
     * 
     * @return 1 or 0 depending on the option selected
     */
    public static int checkYesOrNo(String option)
    {
        if (option.equalsIgnoreCase("Não") || option.equalsIgnoreCase("Não possui")) 
            return 0;
        return 1;
    }
}
