package controllers.auth;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;

import java.net.URL;

import java.security.NoSuchAlgorithmException;

import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import exceptions.validation.NullFieldException;
import exceptions.validation.WrongPasswordException;
import helpers.AuthHelper;
import helpers.PasswordHelper;
import helpers.SceneHelper;
import helpers.ValidationHelper;

import models.Employee;

public class LoginController implements Initializable
{

    @FXML
    private TextField cpfField;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button redirectRegisterButton;

    @FXML
    void login(ActionEvent event)
    {
        try {
            
            String[] fields = { cpfField.getText(), passwordField.getText() };

            for (String field : fields) {
                ValidationHelper.throwNullFieldException(field);
            }

            Employee user = Employee.where("cpf = " + fields[0]).get(0);

            if (!PasswordHelper.check(fields[1], user.getPassword()))
                throw new WrongPasswordException();

            AuthHelper.setUser(user);

            SceneHelper.switchScene(event, "homepage");

        } catch (NullFieldException e) {
            
            JOptionPane.showMessageDialog(
                null, e.getMessage(), "Validation error - " + e.getField() + " field null", JOptionPane.ERROR_MESSAGE
            );

            passwordField.setText("");

        } catch (NoSuchAlgorithmException e) {

            JOptionPane.showMessageDialog(
                null, "An internal error has occurred", "Error", JOptionPane.ERROR_MESSAGE
            );

            passwordField.setText("");

        } catch (WrongPasswordException e) {
            
            JOptionPane.showMessageDialog(
                null, e.getMessage(), "Wrong password", JOptionPane.ERROR_MESSAGE
            );

            passwordField.setText("");

        } catch (IOException e) {

            e.printStackTrace();

        } catch (IndexOutOfBoundsException e) {

            JOptionPane.showMessageDialog(
                null, "The CPF entered does not correspond to any user. Please enter a CPF that is registered.", "CPF not found", JOptionPane.ERROR_MESSAGE
            );

            passwordField.setText("");

            passwordField.setText("");
        }
    }

    @FXML
    void redirectRegisterForm(ActionEvent event)
    {
        try {
            SceneHelper.switchScene(event, "register");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
        cpfField.textProperty().addListener((obs, odlText, newText) ->
        {
            if (newText.length() > 10) {
                cpfField.setText(odlText);
            }
        });
    }

}
