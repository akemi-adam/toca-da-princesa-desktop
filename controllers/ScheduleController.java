package controllers;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import models.Animal;
import models.Employee;
import models.Model;
import models.Schedule;
import models.Vet;

import java.io.IOException;

import java.net.URL;

import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import exceptions.validation.NullFieldException;
import exceptions.validation.UnformattedDateException;
import helpers.AuthHelper;
import helpers.SceneHelper;
import helpers.ValidationHelper;

public class ScheduleController implements Initializable
{
    @FXML
    private TextField dateEntryField;

    @FXML
    private Button scheduleButton;

    @FXML
    private Button cancelScheduleButton;

    @FXML
    private ComboBox<Model> selectAnimal;

    @FXML
    private ComboBox<Model> selectVet;

    @FXML
    void createSchedule(ActionEvent event)
    {
        try {
            
            String dateEntry = dateEntryField.getText();

            ValidationHelper.throwNullFieldException(dateEntry);

            ValidationHelper.throwUnformattedDateException(dateEntry);

            String[] dateArray = dateEntryField.getText().split("/");

            String date = String.format("%s-%s-%s", dateArray[2], dateArray[1], dateArray[0]);

            Vet vet = (Vet) selectVet.getSelectionModel().getSelectedItem();

            Animal animal = (Animal) selectAnimal.getSelectionModel().getSelectedItem();

            Employee user = AuthHelper.getUser();

            Schedule.create(
                "animal_id, employee_id, vet_id, entry_date", String.format("%s, %s, %s, '%s'", animal.getId(), user.getId(), vet.getId(), date)
            );

        } catch (IOException|ClassNotFoundException e) {

            JOptionPane.showMessageDialog(null, "An error has occurred internally in the program. Please try the action again.", "Internal error", JOptionPane.ERROR_MESSAGE);

        } catch (NullFieldException e) {

            JOptionPane.showMessageDialog(null, e.getMessage(), "Is there a null field", JOptionPane.ERROR_MESSAGE);

        } catch (UnformattedDateException e) {

            JOptionPane.showMessageDialog(null, e.getMessage(), "Invalid Date", JOptionPane.ERROR_MESSAGE);

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        selectVet.setItems(SceneHelper.getList(Vet.all()));

        selectAnimal.setItems(SceneHelper.getList(Animal.all()));
    }

    public void cancelSchedule(ActionEvent event)
    {
        try {
            SceneHelper.switchScene(event, "homepage");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An error has occurred internally in the program. Please try the action again.", "Internal error", JOptionPane.ERROR_MESSAGE);
        }
    }
}