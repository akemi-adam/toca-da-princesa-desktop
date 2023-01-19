import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

import java.net.URL;

public class Main extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        try {

            FXMLLoader loader = new FXMLLoader(
                new URL("file:/" + new File("layouts\\login.fxml").getAbsolutePath())
            );

            Parent root = loader.load();

            Scene scene = new Scene(root);

            primaryStage.setTitle("Toca da princesa");

            primaryStage.setScene(scene);
            
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
