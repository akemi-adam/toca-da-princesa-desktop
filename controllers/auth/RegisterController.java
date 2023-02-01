package controllers.auth;

import java.io.IOException;

import java.net.URL;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import models.Employee;

import exceptions.validation.PasswordNotMatchException;
import helpers.AuthHelper;
import helpers.PasswordHelper;
import helpers.SceneHelper;

public class RegisterController implements Initializable
{

    @FXML
    private TextField ageField;

    @FXML
    private TextField cpfField;

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordAgainField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registerButton;

    @FXML
    private Button redirectLoginButton;

    /**
     * Stores the field values in an array and checks if all fields have been filled in. If any field is null, a NullFieldException will be thrown and handled in a catch block.
     * Otherwise, it now checks if the passwords are equal. If not, a PasswordNotMatchException is thrown and handled.
     * Finally, after this simple validation, the field values are formatted into a String and then inserted into the database.
     * 
     * @param event
     * @author <a href="https://github.com/akemi-adam">Akemi Adam</a>
     * 
     * @return void
     */
    @FXML
    public void register(ActionEvent event)
    {
        try {

            String[] fields = { nameField.getText(), ageField.getText(), cpfField.getText(), passwordField.getText(), passwordAgainField.getText() };

            if (!passwordField.getText().equals(passwordAgainField.getText()))
                throw new PasswordNotMatchException("Passwords are not the same");

            String passwordHash = PasswordHelper.getSaltedHash(fields[3]);

            String values = String.format("'%s', %s, '%s', '%s'", fields[0], fields[1], fields[2], passwordHash);

            Employee.create("name, age, cpf, password", values);

            AuthHelper.setUser(
                Employee.where("cpf = " + fields[2]).get(0)
            );
            
            SceneHelper.switchScene(event, "homepage");

        } catch (PasswordNotMatchException e) {
            
            JOptionPane.showMessageDialog(
                null, e.getMessage(), "Validation error - Passwords don't match", JOptionPane.ERROR_MESSAGE
            );

            resetPasswords();

        } catch (NoSuchAlgorithmException|IOException e) {

            JOptionPane.showMessageDialog(
                null, "An internal error has occurred", "Error", JOptionPane.ERROR_MESSAGE
            );

            resetPasswords();
        
        } catch (SQLException e) {
        
            JOptionPane.showMessageDialog(null, e.getMessage(), "Validation error", JOptionPane.ERROR_MESSAGE);

            resetPasswords();
        }
    }

    /**
     * Redirects the user to the login form
     * 
     * @param event | Event object
     * @author <a href="https://github.com/akemi-adam">Akemi Adam</a>
     * 
     * @return void
     */
    @FXML
    public void redirectLoginForm(ActionEvent event)
    {
        try {
            SceneHelper.switchScene(event, "login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Resets the password fields
     * 
     * @author <a href="https://github.com/akemi-adam">Akemi Adam</a>
     * 
     * @return void
     */
    protected void resetPasswords()
    {
        this.passwordAgainField.setText("");

        this.passwordField.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        cpfField.textProperty().addListener((obs, odlText, newText) ->
        {
            if (newText.length() > 10) {
                cpfField.setText(odlText);
            }
        });
    }
}