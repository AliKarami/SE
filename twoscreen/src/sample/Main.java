package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage mainwindow;

    @Override
    public void start(Stage primaryStage) throws Exception{
        mainwindow = primaryStage;
        Parent root;
        root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        mainwindow.setTitle("BAZARGANANE JAVAN");
        mainwindow.setScene(new Scene(root, 800  , 500));
        mainwindow.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
