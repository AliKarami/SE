package sample;

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
import java.util.*;

import javafx.event.ActionEvent;

import java.io.IOException;

class ware {
    String name;
    String man;
    String weight;
    String quantity;
    String price;
    char price_s;
}

class cert {
    String cid;
    String date_to;
    String source_country;
    char entrance;
    int whid;

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
    public TextField CertWarenameTXT;
    @FXML
    public TextField CertNumToTXT;
    @FXML
    public TextField CertNumFromTXT;
    @FXML
    public TextField CertPerpriceToTXT;
    @FXML
    public TextField CertPerpriceFromTXT;
    @FXML
    public DatePicker CertDateFromDP;
    @FXML
    public DatePicker CertDateToDP;
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
    @FXML
    public TextField ruleWareTB;
    @FXML
    public TextField ruleNumFromTB;
    @FXML
    public TextField ruleNumToTB;
    @FXML
    public TextField rulePriceFromTB;
    @FXML
    public TextField rulePriceToTB;
    @FXML
    public DatePicker ruleDateFromDP;
    @FXML
    public DatePicker ruleDateToDP;
    @FXML
    public ListView ruleCerthouseLV;
    @FXML
    public Button ruleCertAddBTN;
    @FXML
    public Button ruleRecBTN;

    //    @override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void RecRule (ActionEvent event) throws IOException {
        String sql;

    }

    @FXML
    public void RecDeclaration (ActionEvent event) throws IOException {
        String sql;
        for (ware w:Data.getData().Warehouse) {
            sql = "INSERT INTO WARE (name,manufacturer,weight,quantity,price,price_s)\n" +
                    "VALUES(\"" + w.name + "\",\"" + w.man + "\"," + w.weight + "," + w.quantity + "," + w.price + ", +'E')";
            SQLHandler.executeUpdate(sql);
        }

        System.out.println(DecMNameTXT.getText());
        char enterance;

        if (RecDecAirRD.isSelected())
            enterance = 'A';
        else if (RecDecSeaRD.isSelected())
            enterance = 'S';
        else
            enterance = 'F';

        for (cert c:Data.getData().Certhouse) {
            sql = "INSERT INTO CERTIFICATES (date_to,source_country,enterance,WHID)\n" +
                    "VALUES(\"" + c.date_to + "\"," + c.source_country + "," + c.entrance + "," + c.whid + ")";
            SQLHandler.executeUpdate(sql);
        }

        sql = "INSERT INTO DECLARATIONS (date,WHID,source_country,enterance,CHID)\n" +
                "VALUES (\"" + DecDateTXT.getText() + "\"," + 1 + ",\"" + DecSourceTXT.getText() + "\",'" + enterance + "'," + 2 + ")";
        SQLHandler.executeUpdate(sql);
        Data.getData().Warehouse = new Vector<ware>();
        Data.getData().Certhouse = new Vector<cert>();
    }

    @FXML
    public void addware (ActionEvent event) throws IOException {
        if (WareNameTXT.getText().equals("") || WareManTXT.getText().equals("") || WareNumTXT.getText().equals("") || WarePriceTXT.getText().equals("") || WareWeightTXT.getText().equals("")) {
            addwareerrorLBL.setVisible(true);
            return;
        } else {
            Data.getData().tmp1.name = WareNameTXT.getText();
            Data.getData().tmp1.man = WareManTXT.getText();
            Data.getData().tmp1.quantity = WareNumTXT.getText();
            Data.getData().tmp1.weight = WareWeightTXT.getText();
            Data.getData().tmp1.price = WarePriceTXT.getText();
            Data.getData().Warehouse.add(Data.getData().tmp1);
            Data.getData().tmp1 = new ware();
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
            Data.getData().tmp2.cid = CertIdTXT.getText();
//            Data.getData().tmp2.num_to = Integer.parseInt(CertNumToTXT.getText());
//            Data.getData().tmp2.perprice_form = Integer.parseInt(CertPerpriceFromTXT.getText());
//            Data.getData().tmp2.perprice_to = Integer.parseInt(CertPerpriceToTXT.getText());
//            Data.getData().tmp2.date_from = CertDateFromDP.getEditor().toString();
            Data.getData().tmp2.date_to = CertDateToDP.getEditor().toString();
            Data.getData().Certhouse.add(Data.getData().tmp2);
            Data.getData().tmp2 = new cert();
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
        Data.getData().certItems.add(Data.getData().tmp2.cid);
        certhouseLV.setItems(Data.getData().certItems);
        Data.getData().tmp2 = new cert();
    }

    @FXML
    public void getTableView(ActionEvent event) throws IOException {

        //code here!

    }

    @FXML
    public void AddUser(ActionEvent event) throws IOException {

        ResultSet rs = SQLHandler.executeQuery("SELECT username FROM USERS WHERE username=\"" + signupname.getText() + "\"");
        try {
            if (rs.next()) {
                duplicateLBL.setVisible(true);
                return;
            }
        } catch (Exception ex) {}


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

        if (SQLHandler.executeUpdate("INSERT INTO USERS (username,password,type,name,family) values (\"" + signupuname.getText() + "\",\"" + signuppword.getText() + "\",\'" + type +  "\',\"" + signupname.getText() + "\",\"" + signupfamily.getText() + "\")") != -1)
            userAddedLBL.setVisible(true);

    }
}