package controllers;

import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;

import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

import java.net.URL;

import java.util.HashMap;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import exceptions.validation.NotIntegerValueException;
import exceptions.validation.NullFieldException;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import helpers.SceneHelper;
import helpers.ValidationHelper;

import models.Vet;
import models.Cat;
import models.Dog;
import models.Model;

import enums.Type;

public class HomepageController implements Initializable
{
    /* Menu components */

    @FXML
    private MenuItem logoutOption;

    @FXML
    private MenuItem schuleOption;

    @FXML
    private MenuItem historyOption;
    
    /* Table Vet components */

    @FXML
    private TableView<Model> vetTable;
    
    @FXML
    private TableColumn<Vet, Integer> idVetCollumn;
    
    @FXML
    private TableColumn<Vet, String> specialtyCollumn;

    @FXML
    private TableColumn<Vet, String> nameVetCollumn;

    /* Table Dog components */

    @FXML
    private TableView<Model> dogTable;

    @FXML
    private TableColumn<Vet, Integer> ageDogCollumn;

    @FXML
    private TableColumn<Vet, Integer> idDogCollumn;

    @FXML
    private TableColumn<Vet, String> nameDogCollumn;

    @FXML
    private TableColumn<Vet, String> ownerDogCollumn;

    @FXML
    private TableColumn<Vet, Integer> sickDogCollumn;

    @FXML
    private TableColumn<Vet, Integer> pedigreeDogCollumn;

    /* Table Cat components */

    @FXML
    private TableView<Model> catTable;

    @FXML
    private TableColumn<Cat, Integer> ageCatCollumn;

    @FXML
    private TableColumn<Cat, Integer> idCatCollumn;

    @FXML
    private TableColumn<Cat, String> nameCatCollumn;

    @FXML
    private TableColumn<Cat, String> ownerCatCollumn;

    @FXML
    private TableColumn<Cat, Integer> sickCatCollumn;

    @FXML
    private TableColumn<Cat, Integer> blueEyeCatCollumn;

    /* Create Animal Form */

    @FXML
    private TextField nameAnimalField;

    @FXML
    private TextField ageAnimalField;

    @FXML
    private TextField ownerAnimalField;

    @FXML
    private RadioButton sickNoButton;

    @FXML
    private RadioButton catTypeButton;

    @FXML
    private ToggleGroup sickGroup;

    @FXML
    private ToggleGroup princessTypeGroup;

    @FXML
    private Button storeAnimalButton;

    /* Create Cat Form components */

    @FXML
    private ToggleGroup blueEyeGroup;

    @FXML
    private RadioButton blueEyeNoButton;

    /* Create Dog Form components */

    @FXML
    private ToggleGroup pedigreeGroup;

    @FXML
    private RadioButton pedigreeNoButton;

    /* Create Vet Form components */

    @FXML
    private TextField nameVetField;

    @FXML
    private TextArea specialtyField;

    @FXML
    private Button storeVetButton;

    /* Destroy Vet Form components */

    @FXML
    private Button destroyVetButton;

    @FXML
    private TextField destroyVetField;

    /* Destroy Animal Form components */

    @FXML
    private Button destroyAnimalButton;

    @FXML
    private TextField destroyAnimalField;

    /* Update form components */

    @FXML
    private Button updateVetButton;

    /* Methods */

    /**
     * Log out of the application
     * 
     * @param event
     * 
     * @return void
     */
    @FXML
    public void logout(ActionEvent event)
    {
        System.exit(0);
    }

    /**
     * Retrieves the data from the fields, creates a new vet template in the database. Then retrieves the model to transform it into its respective object and adds it to the table.
     * In case both input fields are null, a NullFieldException is thrown stating such an error.
     * 
     * @param event
     * 
     * @return void
     */
    @FXML
    public void storeVet(ActionEvent event)
    {
        try {
            
            String[] fields = { nameVetField.getText(), specialtyField.getText() };

            for (String field : fields) {
                ValidationHelper.throwNullFieldException(field);
            }

            Vet.create("name, specialty", String.format("'%s', '%s'", fields[0], fields[1]));

            Vet vet = (Vet) Vet.where("vets.name = '" + fields[0] + "' and vets.specialty = '" + fields[1] + "' order by vets.id desc").get(0);

            vetTable.getItems().add(vet);

        } catch (NullFieldException e) {
            JOptionPane.showMessageDialog(
                null, e.getMessage(), "Validation error - field null", JOptionPane.ERROR_MESSAGE
            );
        }

        cleanCreateVetFields();
    }

    /**
     * The class implements this method of the Initializable interface to map the columns to the objects that make up the respective table. It then retrieves the templates from the database and defines them as table rows.
     * 
     * @param location
     * @param resources
     * 
     * @return void
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        /* Vet */

        idVetCollumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        nameVetCollumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        specialtyCollumn.setCellValueFactory(new PropertyValueFactory<>("specialty"));

        /* Cat */

        idCatCollumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        nameCatCollumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        ownerCatCollumn.setCellValueFactory(new PropertyValueFactory<>("owner"));

        ageCatCollumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        
        sickCatCollumn.setCellValueFactory(new PropertyValueFactory<>("sick"));

        blueEyeCatCollumn.setCellValueFactory(new PropertyValueFactory<>("blueEye"));

        /* Dog */

        idDogCollumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        nameDogCollumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        ownerDogCollumn.setCellValueFactory(new PropertyValueFactory<>("owner"));

        ageDogCollumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        
        sickDogCollumn.setCellValueFactory(new PropertyValueFactory<>("sick"));

        pedigreeDogCollumn.setCellValueFactory(new PropertyValueFactory<>("pedigree"));

        /* Tables */

        vetTable.setItems(SceneHelper.getList(Vet.all()));

        catTable.setItems(SceneHelper.getList(Cat.all()));

        dogTable.setItems(SceneHelper.getList(Dog.all()));
    }

    

    /**
     * Clean all fields of vet create form
     * 
     * @return void
     */
    private void cleanCreateVetFields()
    {
        nameVetField.setText("");
        specialtyField.setText("");
    }

    private void cleanCreateAnimalFields()
    {
        nameAnimalField.setText("");

        ageAnimalField.setText("");
        
        ownerAnimalField.setText("");
        
        sickNoButton.setSelected(true);
        
        catTypeButton.setSelected(true);
        
        blueEyeNoButton.setSelected(true);
        
        pedigreeNoButton.setSelected(true);
    }

    /**
     * Validates the id entered by the user and if everything is correct, the model is deleted from the database and the table is updated with the new data total in the database.
     * It may throw a NullFieldException or a NotIntegerValueException.
     * 
     * @param event
     * 
     * @return void
     */
    @FXML
    public void destroyVet(ActionEvent event)
    {
        try {
            
            String value = destroyVetField.getText();

            ValidationHelper.throwNullFieldException(value);

            int id = Integer.parseInt(value);

            if (!ValidationHelper.isNumber(value))
                throw new NotIntegerValueException();
            
            Vet.delete("vets.id = " + id);

            vetTable.setItems(SceneHelper.getList(Vet.all()));

        } catch (NullFieldException e) {
            JOptionPane.showMessageDialog(
                null, e.getMessage(), "Validation error - field null", JOptionPane.ERROR_MESSAGE
            );
        } catch (NotIntegerValueException e) {
            JOptionPane.showMessageDialog(
                null, e.getMessage(), "Invalid ID", JOptionPane.ERROR_MESSAGE
            );
        }

        destroyVetField.setText("");
    }

    /**
     * Changes the screen to the edit veterinarian form
     * 
     * @param event
     * 
     * @return void
     */
    @FXML
    public void updateVet(ActionEvent event)
    {
        try {
            SceneHelper.switchScene(event, "vet/update");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void storeAnimal(ActionEvent event)
    {
        try {

            HashMap<String, String> fields = new HashMap<String, String>();

            fields.put("name", nameAnimalField.getText());

            fields.put("owner", ownerAnimalField.getText());

            fields.put("age", ageAnimalField.getText());

            fields.put("sick", ((RadioButton) sickGroup.getSelectedToggle()).getText());

            fields.put("type", ((RadioButton) princessTypeGroup.getSelectedToggle()).getText());

            fields.put("blue_eye", ((RadioButton) blueEyeGroup.getSelectedToggle()).getText());

            fields.put("pedigree", ((RadioButton) pedigreeGroup.getSelectedToggle()).getText());

            for (String key : fields.keySet()) {
                ValidationHelper.throwNullFieldException(fields.get(key));
            }

            if (!ValidationHelper.isNumber(fields.get("age")))
                throw new NotIntegerValueException();

            int sick = ValidationHelper.checkYesOrNo(fields.get("sick"));

            String animalValues = String.format("'%s', '%s', %s, %s", fields.get("name"), fields.get("owner"), fields.get("age"), sick);

            String animalCollumns = "name, owner, age, sick";

            if (fields.get("type").equals(Type.CAT.getType())) {
                
                Cat.create(animalCollumns, animalValues, "blue_eye", Integer.toString(ValidationHelper.checkYesOrNo(fields.get("blue_eye"))));

                catTable.setItems(SceneHelper.getList(Cat.all()));

            } else if (fields.get("type").equals(Type.DOG.getType())) {
                
                Dog.create(animalCollumns, animalValues, "pedrigree", Integer.toString(ValidationHelper.checkYesOrNo(fields.get("pedigree"))));

                dogTable.setItems(SceneHelper.getList(Dog.all()));
            }

        } catch (NullFieldException e) {

            JOptionPane.showMessageDialog(null, e.getMessage(), "Null field", JOptionPane.ERROR_MESSAGE);

        } catch (NotIntegerValueException e) {

            JOptionPane.showMessageDialog(null, e.getMessage(), "Invalid age", JOptionPane.ERROR_MESSAGE);

        }

        cleanCreateAnimalFields();
    }

    @FXML
    public void destroyAnimal(ActionEvent event)
    {
        try {

            String value = destroyAnimalField.getText();

            if (!ValidationHelper.isNumber(value))
                throw new NotIntegerValueException();

            int id = Integer.parseInt(value);

            String selectText = ((RadioButton) princessTypeGroup.getSelectedToggle()).getText();

            if (selectText.equalsIgnoreCase(Type.CAT.getType())) {

                Cat cat = Cat.find(id);

                cat.destroy();

                catTable.setItems(SceneHelper.getList(Cat.all()));

            } else if (selectText.equalsIgnoreCase(Type.DOG.getType())) {
                
                Dog dog = Dog.find(id);

                dog.destroy();

                dogTable.setItems(SceneHelper.getList(Dog.all()));
            }

        } catch (NotIntegerValueException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Invalid ID", JOptionPane.ERROR_MESSAGE);
        }

        destroyAnimalField.setText("");
    }

    @FXML
    public void updateAnimal(ActionEvent event)
    {
        try {
            SceneHelper.switchScene(event, "animal\\update");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void changeHistoryScene(ActionEvent event)
    {
        try {
            SceneHelper.switchSceneFromMenu(historyOption, "schedules\\index");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void changeSchuleScene(ActionEvent event)
    {
        try {
            SceneHelper.switchSceneFromMenu(schuleOption, "schedules\\create");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
