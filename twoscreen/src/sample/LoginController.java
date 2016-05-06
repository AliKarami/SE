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

        String url = "jdbc:mysql://localhost:3306/Customs";
        String username = "root";
        String password = "akmz8ki";

        try {
            Class.forName("com.mysql.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(url, username, password)) {

                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT password FROM USERS WHERE username=\"" + uname.getText() + "\"");
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

                root = FXMLLoader.load(getClass().getResource("Declaration.fxml"));
                Scene scene = new Scene(root , 800 , 500);
                stage.setScene(scene);
                stage.show();

            } catch (SQLException e) {
                throw new IllegalStateException("Cannot connect the database!", e);
            }

        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in the classpath!", e);
        }



    }
}
