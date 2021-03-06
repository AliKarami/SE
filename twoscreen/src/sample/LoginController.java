package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.*;
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
    private Button loginBTN;

    @FXML
    private TextField uname;

    @FXML
    private TextField pword;

    @FXML
    private Label errorLBL;

    @FXML
    public void GoToDeclaration(ActionEvent event) throws IOException {
        Stage stage = Main.mainwindow;
        Parent root;

            try  {
                char userType = 'N';
                ResultSet rs = SQLHandler.executeQuery("SELECT type,password FROM USERS WHERE username=\"" + uname.getText() + "\"");
                if (!rs.next()) {
                    errorLBL.setVisible(true);
                    return;
                }
                else {
                    if (!pword.getText().equals(rs.getString("password"))) {
                        errorLBL.setVisible(true);
                        return;
                    }

                }

                Data.getData().curUserType = rs.getString("type").charAt(0);
                root = FXMLLoader.load(getClass().getResource("Declaration.fxml"));
                Scene scene = new Scene(root , 800 , 500);
                stage.setScene(scene);
                stage.show();

            } catch (SQLException e) {
                throw new IllegalStateException("Password Checking Exception", e);
            }
    }
}
