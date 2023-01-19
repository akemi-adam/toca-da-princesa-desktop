package helpers;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

import java.io.File;

import models.Employee;

public abstract class AuthHelper
{
    private static final String PATH = new File(".").getAbsolutePath() + "/session/user_auth.ser";

    private static Employee user;

    public static void setUser(Employee employee) throws IOException
    {
        FileOutputStream file = new FileOutputStream(PATH);

        ObjectOutputStream writer = new ObjectOutputStream(file);

        writer.writeObject(employee);

        writer.close();
    }

    public static Employee getUser() throws IOException, ClassNotFoundException
    {
        FileInputStream file = new FileInputStream(PATH);

        ObjectInputStream reader = new ObjectInputStream(file);

        user = (Employee) reader.readObject();

        reader.close();

        return user;
    }
}
