package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;

import java.io.IOException;

public class LoginController implements Initializable{

//    @override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private Button btn1;


    @FXML
    public void GoToDeclaration(ActionEvent event) throws IOException {
        Stage stage = Main.mainwindow;
        Parent root;
        root = FXMLLoader.load(getClass().getResource("Declaration.fxml"));

        Scene scene = new Scene(root , 800 , 500);
        stage.setScene(scene);
        stage.show();
    }
}
