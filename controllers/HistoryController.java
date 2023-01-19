package controllers;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;

import java.net.URL;

import java.util.ResourceBundle;

import exceptions.NoEditableModelException;
import exceptions.validation.NullFieldException;
import exceptions.validation.UnformattedDateException;

import models.Model;
import models.Schedule;

import helpers.SceneHelper;
import helpers.ValidationHelper;

public class HistoryController implements Initializable
{
    @FXML
    private Button changeSceneButton;

    @FXML
    private TextField dateExitField;

    @FXML
    private ListView<Model> historyListView;

    @FXML
    private Button markExitDateButton;

    @FXML
    void changeScene(ActionEvent event)
    {
        try {
            SceneHelper.switchScene(event, "homepage");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An error has occurred internally in the program. Please try the action again.", "Internal error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    void markExitDate(ActionEvent event)
    {
        try {
            
            String exitDate = dateExitField.getText();
    
            ValidationHelper.throwNullFieldException(exitDate);
    
            ValidationHelper.throwUnformattedDateException(exitDate);
    
            String[] dateArray = exitDate.split("/");
    
            String date = String.format("%s-%s-%s", dateArray[2], dateArray[1], dateArray[0]);

            Schedule schedule = (Schedule) historyListView.getSelectionModel().getSelectedItem();

            if (schedule == null)
                throw new NullFieldException();
    
            if (schedule.getExitDate() != null)
                throw new NoEditableModelException("The appointment has already been finalized, it is no longer possible to schedule another departure date.");

            schedule.edit(String.format("exit_date = '%s'", date));

            historyListView.setItems(
                SceneHelper.getList(Schedule.all())
            );

        } catch (NullFieldException e) {
            
            JOptionPane.showMessageDialog(null, e.getMessage(), "Is there a null field", JOptionPane.ERROR_MESSAGE);

        } catch (UnformattedDateException e) {

            JOptionPane.showMessageDialog(null, e.getMessage(), "Invalid Date", JOptionPane.ERROR_MESSAGE);

        } catch(NoEditableModelException e) {

            JOptionPane.showMessageDialog(null, e.getMessage(), "No editable model", JOptionPane.ERROR_MESSAGE);

        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        historyListView.setItems(
            SceneHelper.getList(Schedule.all())
        );
    }

}
