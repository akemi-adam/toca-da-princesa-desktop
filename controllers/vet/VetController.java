package controllers.vet;

import java.io.IOException;

import java.net.URL;

import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import exceptions.validation.NullFieldException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import models.Model;
import models.Vet;

import helpers.SceneHelper;

public class VetController implements Initializable
{

    @FXML
    private TextField nameField;

    @FXML
    private ComboBox<Model> selectVet;

    @FXML
    private TextArea specialtyField;

    @FXML
    private Button updateButton;

    @FXML
    void update(ActionEvent event)
    {
        try {

            Vet vet = (Vet) selectVet.getSelectionModel().getSelectedItem();

            String name = nameField.getText() != "" ?
                nameField.getText() : vet.getName();

            String specialty = specialtyField.getText() != "" ?
                specialtyField.getText() : vet.getSpecialty();

            if ((name == null || name == "") && (specialty == null || specialty == ""))
                throw new NullFieldException();

            vet.edit(
                String.format("name = '%s', specialty = '%s'", name, specialty)
            );

            SceneHelper.switchScene(event, "homepage");

        } catch (NullFieldException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Empty fields", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
        selectVet.setItems(vetsList());
    }

    private ObservableList<Model> vetsList()
    {
        return FXCollections.observableArrayList(Vet.all());
    }

}
