package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {


    public List<String> warenames = new ArrayList<String>();

    public ObservableList<String> wareItems =FXCollections.observableArrayList (warenames);

    public static Stage mainwindow;
    @Override
    public void start(Stage primaryStage) throws Exception{

        SQLHandler.initTables();

        mainwindow = primaryStage;
        Parent root;
        root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        mainwindow.setTitle("گمرک مرزی");
        mainwindow.setScene(new Scene(root, 800  , 500));
        mainwindow.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}