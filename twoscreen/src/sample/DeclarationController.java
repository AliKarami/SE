package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Vector;

class ware {
    String name;
    String man;
    String weight;
    String number;
    String price;
}

class cert {
    String id;
    String desc;
}

public class DeclarationController implements Initializable{


    @FXML
    private TextField signupuname;

    @FXML
    private TextField signuppword;

    @FXML
    private TextField signupname;

    @FXML
    private TextField signupfamily;

    @FXML
    private RadioButton emp;

    @FXML
    private RadioButton ruler;

    @FXML
    private RadioButton admin;

    @FXML
    private Label errorLBL;

    @FXML
    private Label duplicateLBL;

    @FXML
    private Label userAddedLBL;

    @FXML
    public TableView<DeclarationController> usersTBL;

    @FXML
    public TableColumn unameCOL;

    @FXML
    public TableColumn nameCOL;

    @FXML
    public TableColumn familyCOL;

    @FXML
    public TableColumn typeCOL;

    @FXML
    public ListView warehouseLV;

    @FXML
    public ListView certhouseLV;

    @FXML
    public TextField WareNameTXT;

    @FXML
    public TextField WareManTXT;

    @FXML
    public TextField WareWeightTXT;

    @FXML
    public TextField WareNumTXT;

    @FXML
    public TextField WarePriceTXT;

    @FXML
    public TextField CertIdTXT;

    @FXML
    public TextArea CertDescTXT;

    @FXML
    public Label addwareerrorLBL;

    @FXML
    public Label addcerterrorLBL;

    @FXML
    public Button addwareBTN;

    @FXML
    public Button addcertBTN;

    @FXML
    public TextField DecMIDTXT;

    @FXML
    public TextField DecMNameTXT;

    @FXML
    public TextField DecDateTXT;

    @FXML
    public TextField DecSourceTXT;

    @FXML
    public RadioButton RecDecAirRD;

    @FXML
    public RadioButton RecDecSeaRD;

    @FXML
    public RadioButton RecDecFloorRD;

    //    @override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void RecDeclaration (ActionEvent event) throws IOException {
        String url = "jdbc:mysql://localhost:3306/Customs";
        String username = "root";
        String password = "akmz8ki";

        try {
            Class.forName("com.mysql.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(url, username, password)) {

                Statement stmt = connection.createStatement();
                String sql;
                for (ware w:Data.getData().Warehouse) {
                    sql = "INSERT INTO WARE (name,manufacturer,weight,number,price_per)\n" +
                            "VALUES(\"" + w.name + "\",\"" + w.man + "\"," + w.weight + "," + w.number + "," + w.price + ")";
                    stmt.executeUpdate(sql);
                }
                System.out.println(DecMNameTXT.getText());
                sql = "INSERT INTO MERCHANTS (name,family)\n" +
                        "VALUES (\"" + DecMNameTXT.getText() + "\",\"" + DecMNameTXT.getText() + "\")";
                stmt.executeUpdate(sql);
                char enterance;

                if (RecDecAirRD.isSelected())
                    enterance = 'A';
                else if (RecDecSeaRD.isSelected())
                    enterance = 'S';
                else
                    enterance = 'F';


                sql = "INSERT INTO DECLARATIONS (MID,date,WHID,source_country,enterance,CHID)\n" +
                        "VALUES (" + 1 + ",\"" + DecDateTXT.getText() + "\"," + 1 + ",\"" + DecSourceTXT.getText() + "\",'" + enterance + "'," + 2 + ")";
                stmt.executeUpdate(sql);

            } catch (SQLException e) {
                throw new IllegalStateException("Cannot connect the database!", e);
            }

        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in the classpath!", e);
        }
    }


    @FXML
    public void addware (ActionEvent event) throws IOException {
        if (WareNameTXT.getText().equals("") || WareManTXT.getText().equals("") || WareNumTXT.getText().equals("") || WarePriceTXT.getText().equals("") || WareWeightTXT.getText().equals("")) {
            addwareerrorLBL.setVisible(true);
            return;
        } else {
            Data.getData().tmp1.name = WareNameTXT.getText();
            Data.getData().tmp1.man = WareManTXT.getText();
            Data.getData().tmp1.number = WareNumTXT.getText();
            Data.getData().tmp1.weight = WareWeightTXT.getText();
            Data.getData().tmp1.price = WarePriceTXT.getText();
            Data.getData().Warehouse.add(Data.getData().tmp1);
            Stage stage = (Stage) addwareBTN.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    public void addcert (ActionEvent event) throws IOException {
        if (CertIdTXT.getText().equals("")) {
            addcerterrorLBL.setVisible(true);
            return;
        } else {
            Data.getData().tmp2.id = CertIdTXT.getText();
            Data.getData().tmp2.desc = CertDescTXT.getText();
            Data.getData().Certhouse.add(Data.getData().tmp2);
            Stage stage = (Stage) addcertBTN.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    public void pluswarePopup(ActionEvent event) throws IOException {
        Stage WS = new Stage();
        Parent popup1;
        popup1 = FXMLLoader.load(getClass().getResource("Ware.fxml"));
        WS.setScene(new Scene(popup1));
        WS.initModality(Modality.APPLICATION_MODAL);
        WS.showAndWait();
        Data.getData().wareItems.add(Data.getData().tmp1.name);
        warehouseLV.setItems(Data.getData().wareItems);
        Data.getData().tmp1 = new ware();
    }

    @FXML
    public void pluscertPopup(ActionEvent event) throws IOException {
        Stage CS = new Stage();
        System.out.println("1");
        Parent popup2;
        System.out.println("2");
        popup2 = FXMLLoader.load(getClass().getResource("Cert.fxml"));
        System.out.println("3");
        CS.setScene(new Scene(popup2));
        System.out.println("4");
        CS.initModality(Modality.APPLICATION_MODAL);
        CS.showAndWait();
        Data.getData().certItems.add(Data.getData().tmp2.id);
        certhouseLV.setItems(Data.getData().certItems);
        Data.getData().tmp2 = new cert();
    }

    @FXML
    public void getTableView(ActionEvent event) throws IOException {
        String url = "jdbc:mysql://localhost:3306/Customs";
        String username = "root";
        String password = "akmz8ki";

        try {
            Class.forName("com.mysql.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(url, username, password)) {

                //code here!

            } catch (SQLException e) {
                throw new IllegalStateException("Cannot connect the database!", e);
            }

        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in the classpath!", e);
        }
    }

    @FXML
    public void AddUser(ActionEvent event) throws IOException {
        String url = "jdbc:mysql://localhost:3306/Customs";
        String username = "root";
        String password = "akmz8ki";

        try {
            Class.forName("com.mysql.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(url, username, password)) {

                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT username FROM USERS WHERE username=\"" + signupname.getText() + "\"");

                if (rs.next()) {
                    duplicateLBL.setVisible(true);
                    return;
                }

                if (signupuname.getText().equals("") || signuppword.getText().equals("") || signupname.getText().equals("") || signupfamily.getText().equals("")) {
                    errorLBL.setVisible(true);
                    return;
                }

                if (!emp.isSelected() && !ruler.isSelected() && !admin.isSelected()) {
                    errorLBL.setVisible(true);
                    return;
                }

                char type;

                if (admin.isSelected())
                    type = 'A';
                else if (ruler.isSelected())
                    type = 'R';
                else
                    type = 'E';

                if (stmt.executeUpdate("INSERT INTO USERS (username,password,type,name,family) values (\"" + signupuname.getText() + "\",\"" + signuppword.getText() + "\",\'" + type +  "\',\"" + signupname.getText() + "\",\"" + signupfamily.getText() + "\")") != -1)
                    userAddedLBL.setVisible(true);
            } catch (SQLException e) {
                throw new IllegalStateException("Cannot connect the database!", e);
            }

        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in the classpath!", e);
        }



    }
}
