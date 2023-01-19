package helpers;

import java.io.File;
import java.io.IOException;

import java.net.URL;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;

import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import models.Model;

public abstract class SceneHelper
{
    private static Stage stage;

    private static Scene scene;

    private static Parent root;

    /**
     * Change the scene
     * 
     * @param event | Object of the event
     * @param path | FXML file path
     * @throws IOException
     * 
     * @return void
     */
    public static void switchScene(ActionEvent event, String path) throws IOException
    {
        root = FXMLLoader.load(new URL("file:/" + new File("layouts\\" + path + ".fxml").getAbsolutePath()));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        scene = new Scene(root);

        stage.setScene(scene);

        stage.show();
    }

    public static void switchSceneFromMenu(MenuItem menu, String path) throws IOException
    {
        root = FXMLLoader.load(new URL("file:/" + new File("layouts\\" + path + ".fxml").getAbsolutePath()));

        stage = (Stage) menu.getParentPopup().getOwnerWindow();

        scene = new Scene(root);

        stage.setScene(scene);

        stage.show();
    }

    public static ObservableList<Model> getList(ArrayList<Model> models)
    {
        return FXCollections.observableArrayList(models);
    }
}
