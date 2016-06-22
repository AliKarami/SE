package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.net.URL;
import java.sql.*;
import java.util.*;

import javafx.event.ActionEvent;

import java.io.IOException;

class ware {
    String wid;
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
    String whid;

}

public class DeclarationController implements Initializable{

    //Decleration of UI items:

    //tabs:
    @FXML
    public Tab DecTAB;
    @FXML
    public Tab CertTAB;
    @FXML
    public Tab RuleTAB;
    @FXML
    public Tab UserTAB;

    //DecTAB:
    @FXML
    public DatePicker DecDateDP;
    @FXML
    public ListView warehouseLV;
    @FXML
    public Button pluswareBTN;
    @FXML
    public TextField DecSourceTXT;
    @FXML
    public RadioButton RecDecAirRD;
    @FXML
    public RadioButton RecDecSeaRD;
    @FXML
    public RadioButton RecDecEarthRD;
    @FXML
    public TextField DecCertidTXT;
    @FXML
    public Button pluscertBTN;
    @FXML
    public Label addcerterrorLBL;
    @FXML
    public ListView certhouseLV;
    @FXML
    public Button RecordDecBTN;
    @FXML
    public TextField SearchDecidTXT;
    @FXML
    public Button searchDecBTN;

    //CertTAB:
    @FXML
    public DatePicker CertDateToDP;
    @FXML
    public TextField CertPriceTXT;
    @FXML
    public TextField CertSourceTXT;
    @FXML
    public RadioButton RecCertAirRD;
    @FXML
    public RadioButton RecCertEarthRD;
    @FXML
    public RadioButton RecCertSeaRD;
    @FXML
    public ListView CertwarehouseLV;
    @FXML
    public Button CertpluswareBTN;
    @FXML
    public Button RecordCertBTN;
    @FXML
    public TextField SearchCertidTXT;
    @FXML
    public Button searchCertBTN;

    //RuleTAB:
    @FXML
    public DatePicker RuleDateFromDP;
    @FXML
    public DatePicker RuleDateToDP;
    @FXML
    public TextField RuleSourceTXT;
    @FXML
    public RadioButton RecRulEarthRD;
    @FXML
    public RadioButton RecRulAirRD;
    @FXML
    public RadioButton RecRulSeaRD;
    @FXML
    public TextField RulePriceFromTXT;
    @FXML
    public TextField RulePriceToTXT;
    @FXML
    public TextField RulePPriceFromTXT;
    @FXML
    public TextField RulePPriceToTXT;
    @FXML
    public TextField RuleWareTXT;
    @FXML
    public Button RuleAddWareBTN;
    @FXML
    public ListView RuleWarehouseLV;
    @FXML
    public TextField RuleManTXT;
    @FXML
    public Button RuleAddManBTN;
    @FXML
    public ListView RuleManhouseLV;
    @FXML
    public Button RecordRuleBTN;
    @FXML
    public TableView ruleTBL;

    //UserTAB:
    @FXML
    public TableView usersTBL;
    @FXML
    public Button deleteUserBTN;
    @FXML
    public TextField signupuname;
    @FXML
    public TextField signuppword;
    @FXML
    public TextField signupname;
    @FXML
    public TextField signupfamily;
    @FXML
    public RadioButton adminRD;
    @FXML
    public RadioButton rulerRD;
    @FXML
    public RadioButton certerRD;
    @FXML
    public RadioButton decerRD;
    @FXML
    public Button addUserBTN;
    @FXML
    public Label duplicateLBL;
    @FXML
    public Label userAddedLBL;
    @FXML
    public Label errorLBL;

    //addware
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
    public RadioButton BPriceRD;
    @FXML
    public RadioButton EPriceRD;
    @FXML
    public RadioButton SPriceRD;
    @FXML
    public Button addwareBTN;
    @FXML
    public Label addwareerrorLBL;

    //addware-d
    @FXML
    public TextField dWareNameTXT;
    @FXML
    public TextField dWareManTXT;
    @FXML
    public TextField dWareWeightTXT;
    @FXML
    public TextField dWareNumTXT;
    @FXML
    public TextField dWarePriceTXT;
    @FXML
    public Button daddwareBTN;
    @FXML
    public Label daddwareerrorLBL;

    //    @override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    //DecTAB

    @FXML
    public void pluswarePopup(ActionEvent event) throws IOException {
        Stage WS = new Stage();
        Parent popup1;
        popup1 = FXMLLoader.load(getClass().getResource("addware-d.fxml"));
        WS.setScene(new Scene(popup1));
        WS.initModality(Modality.APPLICATION_MODAL);
        WS.showAndWait();
        Data.getData().wareItems.add(Data.getData().tmp1.name);
        warehouseLV.setItems(Data.getData().wareItems);
        Data.getData().tmp1 = new ware();
    }

    @FXML
    public void addcert (ActionEvent event) throws IOException {
        if (DecCertidTXT.getText().equals("")) {
            addcerterrorLBL.setVisible(true);
            return;
        } else {
            Data.getData().tmp2.cid = DecCertidTXT.getText();
//            Data.getData().tmp2.num_to = Integer.parseInt(CertNumToTXT.getText());
//            Data.getData().tmp2.perprice_form = Integer.parseInt(CertPerpriceFromTXT.getText());
//            Data.getData().tmp2.perprice_to = Integer.parseInt(CertPerpriceToTXT.getText());
//            Data.getData().tmp2.date_from = CertDateFromDP.getEditor().toString();
            Data.getData().tmp2.date_to = CertDateToDP.getEditor().toString();
            Data.getData().Certhouse.add(Data.getData().tmp2);
            Data.getData().tmp2 = new cert();
            Stage stage = (Stage) pluscertBTN.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    public void RecDeclaration (ActionEvent event) throws IOException {
        String sql;
        for (ware w:Data.getData().Warehouse) {
            sql = "INSERT INTO WARE (name,manufacturer,weight,quantity,price,price_s)\n" +
                    "VALUES(\"" + w.name + "\",\"" + w.man + "\"," + w.weight + "," + w.quantity + "," + w.price + ", +'E')";
            SQLHandler.executeUpdate(sql);
        }

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
                "VALUES (\"" + DecDateDP + "\"," + 1 + ",\"" + DecSourceTXT.getText() + "\",'" + enterance + "'," + 2 + ")";
        SQLHandler.executeUpdate(sql);
        Data.getData().Warehouse = new Vector<ware>();
        Data.getData().Certhouse = new Vector<cert>();
    }

    @FXML
    public void SearchDec (ActionEvent event) throws IOException {

    }

    //CertTAB

    @FXML
    public void pluswarecPopup (ActionEvent event) throws IOException {

    }

    @FXML
    public void RecCert (ActionEvent event) throws IOException {

    }

    @FXML
    public void SearchCert (ActionEvent event) throws IOException {

    }

    //RuleTAB

    @FXML
    public void addwareR (ActionEvent event) throws IOException {

    }

    @FXML
    public void addmanR (ActionEvent event) throws IOException {

    }

    @FXML
    public void RecRule (ActionEvent event) throws IOException {

    }

    //UserTAB

    @FXML
    public void DelUser (ActionEvent event) throws IOException {

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

        if (!adminRD.isSelected() && !rulerRD.isSelected() && !certerRD.isSelected()&& !decerRD.isSelected()) {
            errorLBL.setVisible(true);
            return;
        }

        char type;

        if (adminRD.isSelected())
            type = 'A';
        else if (rulerRD.isSelected())
            type = 'R';
        else if (certerRD.isSelected())
            type = 'C';
        else
            type = 'D';

        if (SQLHandler.executeUpdate("INSERT INTO USERS (username,password,type,name,family) values (\"" + signupuname.getText() + "\",\"" + signuppword.getText() + "\",\'" + type +  "\',\"" + signupname.getText() + "\",\"" + signupfamily.getText() + "\")") != -1)
            userAddedLBL.setVisible(true);

    }

    //addware

    @FXML
    public void addwarec (ActionEvent event) throws IOException {
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
            Stage stage = (Stage) addwareBTN.getScene().getWindow();
            stage.close();
        }
    }

    //addware-d

    @FXML
    public void addwared (ActionEvent event) throws IOException {
        if (dWareNameTXT.getText().equals("") || dWareManTXT.getText().equals("") || dWareNumTXT.getText().equals("") || dWarePriceTXT.getText().equals("") || dWareWeightTXT.getText().equals("")) {
            daddwareerrorLBL.setVisible(true);
            return;
        } else {
            Data.getData().tmp1.name = dWareNameTXT.getText();
            Data.getData().tmp1.man = dWareManTXT.getText();
            Data.getData().tmp1.quantity = dWareNumTXT.getText();
            Data.getData().tmp1.weight = dWareWeightTXT.getText();
            Data.getData().tmp1.price = dWarePriceTXT.getText();
            Data.getData().Warehouse.add(Data.getData().tmp1);
            Stage stage = (Stage) daddwareBTN.getScene().getWindow();
            stage.close();
        }
    }

}