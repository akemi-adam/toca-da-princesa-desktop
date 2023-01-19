package controllers.animal;

import java.io.IOException;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import models.Model;
import models.Animal;

import exceptions.validation.NotIntegerValueException;

import helpers.SceneHelper;
import helpers.ValidationHelper;

public class AnimalController implements Initializable
{

    @FXML
    private TextField nameField;

    @FXML
    private TextField ownerField;

    @FXML
    private TextField ageField;

    @FXML
    private ComboBox<Model> selectPrincess;

    @FXML
    private ToggleGroup sickButtonGroup;

    @FXML
    private RadioButton sickNoButton;

    @FXML
    public void update(ActionEvent event)
    {
        try {

            Animal animal = (Animal) selectPrincess.getSelectionModel().getSelectedItem();

            int sick = ValidationHelper.checkYesOrNo(
                ((RadioButton) sickButtonGroup.getSelectedToggle()).getText()
            );

            String age = ageField.getText() != "" ?
                ageField.getText() : String.format("%d", animal.getAge());

            String name = nameField.getText() != "" ?
                nameField.getText() : animal.getName();

            String owner = ownerField.getText() != "" ?
                ownerField.getText() : animal.getOwner();

            if (!ValidationHelper.isNumber(age))
                throw new NotIntegerValueException();
            
            animal.edit(
                String.format("name = '%s', owner = '%s', sick = %s, age = %s", name, owner, sick, age)
            );

            SceneHelper.switchScene(event, "homepage");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NotIntegerValueException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
        this.selectPrincess.setItems(SceneHelper.getList(Animal.all()));
    }
}
