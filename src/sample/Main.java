package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        Scene scene = new Scene(new Group(), 500, 400);
        scene.getStylesheets().add("/DarkTheme.css");
        primaryStage.setTitle("El Temps"); //canviem el nom.
        primaryStage.setScene(new Scene(root, 285, 500)); //canviem la mida
        primaryStage.show();



    }


    public static void main(String[] args) {
        launch(args);
    }
}
