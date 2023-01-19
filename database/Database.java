package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public abstract class Database
{
    public static Connection getConnection()
    {
        try {
            return DriverManager.getConnection(
                "<driver and database>", "<user>", "<password>"
            );
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                null, e.getMessage(), "SQL Exception", JOptionPane.ERROR_MESSAGE
            );
        }

        return null;
    }
}
