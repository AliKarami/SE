package sample;

import javafx.collections.FXCollections;
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
import java.time.format.DateTimeFormatter;
import java.util.*;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.time.LocalDate;

class ware {
    String name;
    String man;
    String weight;
    String quantity;
    String price;
    char price_s;
}

public class DeclarationController implements Initializable{

    //Decleration of UI items:

    @FXML
    public TabPane tabPANE;

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
    public TextField RuleCertidTXT;
    @FXML
    public Button pluscertRBTN;
    @FXML
    public ListView RuleCerthouseLV;
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

//        SingleSelectionModel<Tab> selectionModel = tabPANE.getSelectionModel();
        switch (Data.getData().curUserType) {
            case 'R':
                DecTAB.setDisable(true);
                CertTAB.setDisable(true);
                UserTAB.setDisable(true);
//                selectionModel.select(RuleTAB);
                break;
            case 'C':
                DecTAB.setDisable(true);
                RuleTAB.setDisable(true);
                UserTAB.setDisable(true);
//                selectionModel.select(CertTAB);
                break;
            case 'D':
                CertTAB.setDisable(true);
                RuleTAB.setDisable(true);
                UserTAB.setDisable(true);
//                selectionModel.select(DecTAB);
                break;
            case 'A':
                break;
        }
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
        String certID = DecCertidTXT.getText();
        Data.getData().Certhouse.add(certID);
        Data.getData().certItems.add(certID);
        certhouseLV.setItems(Data.getData().certItems);
        DecCertidTXT.setText("");
    }

    @FXML
    public void RecDeclaration (ActionEvent event) throws IOException {
        boolean certstrue = true;
        boolean success = false;
        for (String cid:Data.getData().Certhouse) {
            ResultSet rs = SQLHandler.executeQuery("SELECT CID FROM CERTIFICATE WHERE CID=" + cid);
            try {
                if (!rs.isBeforeFirst() || rs==null) {
                    SQLHandler.log("on or more cid in RecordDecleration is wrong.");
                    certstrue = false;
                    break;
                }
            } catch (SQLException e) {
                System.err.println("DecCidChecker exception!");
            }

        }
        if (certstrue) {
            int whid = SQLHandler.getMaxWHID()+1;
            for (ware w:Data.getData().Warehouse) {
                int wid = SQLHandler.getMaxWID()+1;
                SQLHandler.executeUpdate("INSERT INTO WARE (wid,name,manufacturer,weight,quantity,price,price_s)\n" +
                        "VALUES(" + wid + ",\"" + w.name + "\"," + (w.man.equals("")?"NULL":("\""+w.man+"\"")) + "," + (w.weight.equals("")?"NULL":(w.weight)) + "," + (w.quantity.equals("")?"NULL":(w.quantity)) + "," + (w.price.equals("")?"NULL":(w.price)) + "," + (w.price_s==Character.MIN_VALUE?"NULL":("\'"+w.price_s+"\'")) + ")");
                SQLHandler.executeUpdate("INSERT INTO WAREHOUSE (whid,wid)\n" +
                        "VALUES(" + whid + "," + wid +")");
            }
            int chid = SQLHandler.getMaxCHID()+1;
            for (String cid:Data.getData().Certhouse) {
                SQLHandler.executeUpdate("INSERT INTO CERTHOUSE (chid,cid)\n" +
                        "VALUES(" + chid + "," + cid +")");
            }

            char enterance;
            if (RecDecAirRD.isSelected())
                enterance = 'A';
            else if (RecDecEarthRD.isSelected())
                enterance = 'E';
            else if (RecDecSeaRD.isSelected())
                enterance = 'W';
            else
                enterance = 'F';

            SQLHandler.executeUpdate("INSERT INTO DECLERATION (date,WHID,source_country,enterance,CHID)\n" +
                    "VALUES (" + (DecDateDP.getValue()==null?"NULL":("\""+DecDateDP.getValue()+"\"")) + "," + whid + "," + (DecSourceTXT.getText().equals("")?"NULL":("\""+DecSourceTXT.getText()+"\"")) + ",\'" + enterance + "\'," + (Data.getData().Certhouse.size()==0?"NULL":chid) + ")");

            int did = SQLHandler.getLastDID();

            success = Data.getData().dbh.registerDec(did);

            DecDateDP.setValue(null);
            Data.getData().Warehouse.clear();
            Data.getData().warenames.clear();
            warehouseLV.setItems(Data.getData().wareItems);
            RecDecAirRD.setSelected(false);
            RecDecEarthRD.setSelected(false);
            RecDecSeaRD.setSelected(false);
            DecCertidTXT.setText("");
            Data.getData().Certhouse.clear();
            Data.getData().certnames.clear();
            certhouseLV.setItems(Data.getData().certItems);
            certhouseLV.refresh();

            if (success) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Decleration Added");
                alert.setHeaderText(null);
                alert.setContentText("Decleration Added Successfully.\n" +
                        "DID#: " + SQLHandler.getLastDID());

                alert.showAndWait();
            }
            else {
                //message cross-rids to user!
                Vector<Integer> ills = Data.getData().dbh.getIllegals();
                String illsStr = "";
                for (int ill:ills) {
                    illsStr = illsStr +" " + ill;
                }
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Inadequate Certificates Error");
                alert.setHeaderText(null);
                alert.setContentText("Error: rules #" + illsStr + " are not satisfied by certificates.");

                alert.showAndWait();
            }
        }
        else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("CID Error");
                alert.setHeaderText(null);
                alert.setContentText("Error: one or more CIDs entered are wrong!");

                alert.showAndWait();
                System.err.println("cid error!"); //message cid is wrong!
        }
    }

    @FXML
    public void SearchDec (ActionEvent event) throws IOException {
        ResultSet rs = SQLHandler.executeQuery("SELECT * FROM DECLERATION WHERE DID=" + SearchDecidTXT.getText());
        try {
            if(rs.next()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Decleration found.");
                alert.setHeaderText(null);
                alert.setContentText("Decleration found:.\n" +
                        "DID#: " + rs.getString("DID") + "\n"+
                        "To Date: " + rs.getString("date") + "\n"+
                        "WHID#: " + rs.getString("WHID") + "\n"+
                        "source country: " + rs.getString("source_country") + "\n"+
                        "enterance: " + rs.getString("enterance") + "\n"+
                        "CHID#: " + rs.getString("CHID") + "\n");

                alert.showAndWait();
            }
        } catch (SQLException e) {
            System.err.println("searching decleration exception!");
        }
    }

    //CertTAB

    @FXML
    public void pluswarecPopup (ActionEvent event) throws IOException {
        Stage WS = new Stage();
        Parent popup1;
        popup1 = FXMLLoader.load(getClass().getResource("addware.fxml"));
        WS.setScene(new Scene(popup1));
        WS.initModality(Modality.APPLICATION_MODAL);
        WS.showAndWait();
        Data.getData().cwareItems.add(Data.getData().tmp2.name);
        CertwarehouseLV.setItems(Data.getData().cwareItems);
        Data.getData().tmp2 = new ware();
    }

    @FXML
    public void RecCert (ActionEvent event) throws IOException {
        int whid = SQLHandler.getMaxWHID()+1;
        for (ware w:Data.getData().cWarehouse) {
            int wid = SQLHandler.getMaxWID()+1;
            SQLHandler.executeUpdate("INSERT INTO WARE (wid,name,manufacturer,weight,quantity,price,price_s)\n" +
                    "VALUES(" + wid + ",\"" + w.name + "\"," + (w.man.equals("")?"NULL":("\""+w.man+"\"")) + "," + (w.weight.equals("")?"NULL":(w.weight)) + "," + (w.quantity.equals("")?"NULL":(w.quantity)) + "," + (w.price.equals("")?"NULL":(w.price)) + "," + (w.price_s==Character.MIN_VALUE?"NULL":("\'"+w.price_s+"\'")) + ")");
            SQLHandler.executeUpdate("INSERT INTO WAREHOUSE (whid,wid)\n" +
                    "VALUES(" + whid + "," + wid +")");
        }

        char enterance;
        if (RecCertAirRD.isSelected())
            enterance = 'A';
        else if (RecCertEarthRD.isSelected())
            enterance = 'E';
        else if (RecCertSeaRD.isSelected())
            enterance = 'W';
        else
            enterance = 'F';

        int cid = SQLHandler.getMaxCID()+1;
        SQLHandler.executeUpdate("INSERT INTO CERTIFICATE (cid,date_to,price_to,source_country,enterance,whid) \n" +
                    "VALUES(" + cid + "," + (CertDateToDP.getValue()==null?"NULL":("\""+CertDateToDP.getValue()+"\"")) + "," + (CertPriceTXT.getText().equals("")?"NULL":(CertPriceTXT.getText())) + "," + (CertSourceTXT.getText().equals("")?"NULL":("\""+CertSourceTXT.getText()+"\"")) + ",\'" + enterance + "\'," + (Data.getData().cWarehouse.size()==0?"NULL":whid) + ")");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Certification Added");
        alert.setHeaderText(null);
        alert.setContentText("Certification Added Successfully.\n" +
                "CID#: " + SQLHandler.getMaxCID());

        alert.showAndWait();

        CertDateToDP.setValue(null);
        CertPriceTXT.setText("");
        CertSourceTXT.setText("");
        RecCertAirRD.setSelected(false);
        RecCertSeaRD.setSelected(false);
        RecCertEarthRD.setSelected(false);
        Data.getData().cWarehouse.clear();
        Data.getData().cwarenames.clear();
        CertwarehouseLV.setItems(Data.getData().cwareItems);
        CertwarehouseLV.refresh();

    }

    @FXML
    public void SearchCert (ActionEvent event) throws IOException {
        ResultSet rs = SQLHandler.executeQuery("SELECT * FROM CERTIFICATE WHERE CID=" + SearchCertidTXT.getText());
        try {
            if(rs.next()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Certification found.");
                alert.setHeaderText(null);
                alert.setContentText("Certification found:.\n" +
                        "CID#: " + rs.getString("CID") + "\n"+
                        "Date to: " + rs.getString("date_to") + "\n"+
                        "Price to: " + rs.getString("price_to") + "\n"+
                        "source country: " + rs.getString("source_country") + "\n"+
                        "enterance: " + rs.getString("enterance") + "\n"+
                        "WHID#: " + rs.getString("WHID") + "\n");

                alert.showAndWait();
            }
        } catch (SQLException e) {
            System.err.println("searching certification exception!");
        }
    }

    //RuleTAB

    @FXML
    public void addwareR (ActionEvent event) throws IOException {
        String warename = RuleWareTXT.getText();
        Data.getData().rWarehouse.add(warename);
        Data.getData().rwareItems.add(warename);
        RuleWarehouseLV.setItems(Data.getData().rwareItems);
        RuleWareTXT.setText("");
    }

    @FXML
    public void addmanR (ActionEvent event) throws IOException {
        String manname = RuleManTXT.getText();
        Data.getData().rManhouse.add(manname);
        Data.getData().rmanItems.add(manname);
        RuleManhouseLV.setItems(Data.getData().rmanItems);
        RuleManTXT.setText("");
    }

    @FXML
    public void addcertR (ActionEvent event) throws IOException {
        String certname = RuleCertidTXT.getText();
        Data.getData().rCerthouse.add(certname);
        Data.getData().rcertItems.add(certname);
        RuleCerthouseLV.setItems(Data.getData().rcertItems);
        RuleCertidTXT.setText("");
    }

    @FXML
    public void RecRule (ActionEvent event) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (String w:Data.getData().rWarehouse) {
            if (Data.getData().rWarehouse.get(Data.getData().rWarehouse.size()-1).equals(w)) {
                sb.append(w);
            } else
                sb.append(w+",");
        }
        String wares = "" + sb.toString();
        sb = new StringBuilder();
        for (String m:Data.getData().rManhouse) {
            if (Data.getData().rManhouse.get(Data.getData().rManhouse.size()-1).equals(m)) {
                sb.append(m);
            } else
                sb.append(m+",");
        }
        String mans = "" + sb.toString();
        int chid = SQLHandler.getMaxCHID()+1;
        for (String cid:Data.getData().rCerthouse) {
            SQLHandler.executeUpdate("INSERT INTO CERTHOUSE (chid,cid)\n" +
                    "VALUES(" + chid + "," + cid +")");
        }
        char enterance;
        if (RecRulAirRD.isSelected())
            enterance = 'A';
        else if (RecRulEarthRD.isSelected())
            enterance = 'E';
        else if (RecRulSeaRD.isSelected())
            enterance = 'W';
        else
            enterance = 'F';
        SQLHandler.executeUpdate("INSERT INTO RULE (date_from,date_to,source_country,enterance,price_from,price_to,per_price_from,per_price_to,ware_names,manufacturer_names,CHID)\n" +
                "VALUES (" + (RuleDateFromDP.getValue()==null?"NULL":("\""+RuleDateFromDP.getValue()+"\"")) + "," + (RuleDateToDP.getValue()==null?"NULL":("\""+RuleDateToDP.getValue()+"\"")) + "," + (RuleSourceTXT.getText().equals("")?"NULL":("\""+RuleSourceTXT.getText()+"\"")) + ",\'" + enterance + "\'," + (RulePriceFromTXT.getText().equals("")?"NULL":(RulePriceFromTXT.getText())) + "," + (RulePriceToTXT.getText().equals("")?"NULL":(RulePriceToTXT.getText())) + "," + (RulePPriceFromTXT.getText().equals("")?"NULL":(RulePPriceFromTXT.getText())) + "," + (RulePPriceToTXT.getText().equals("")?"NULL":(RulePPriceToTXT.getText())) + "," + (wares.equals("")?"NULL":("\""+wares+"\"")) + "," + (mans.equals("")?"NULL":("\""+mans+"\"")) + "," + (Data.getData().rCerthouse.size()==0?"NULL":chid) + ")");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Rule Added");
        alert.setHeaderText(null);
        alert.setContentText("Rule Added Successfully.\n" +
                "RID#: " + SQLHandler.getLastRID());

        alert.showAndWait();

        RuleDateFromDP.setValue(null);
        RuleDateToDP.setValue(null);
        RuleSourceTXT.setText("");
        RecRulEarthRD.setSelected(false);
        RecRulAirRD.setSelected(false);
        RecRulSeaRD.setSelected(false);
        RulePriceFromTXT.setText("");
        RulePriceToTXT.setText("");
        RulePPriceFromTXT.setText("");
        RulePPriceToTXT.setText("");
        RuleWareTXT.setText("");
        RuleManTXT.setText("");
        RuleCertidTXT.setText("");
        Data.getData().rWarehouse.clear();
        Data.getData().rManhouse.clear();
        Data.getData().rCerthouse.clear();
        Data.getData().rwarenames.clear();
        Data.getData().rmannames.clear();
        Data.getData().rcertnames.clear();
        RuleWarehouseLV.setItems(Data.getData().rwareItems);
        RuleManhouseLV.setItems(Data.getData().rmanItems);
        RuleCerthouseLV.setItems(Data.getData().rcertItems);
    }

    //UserTAB

    @FXML
    public void DelUser (ActionEvent event) throws IOException {
        int targetuid = -1;
        if (targetuid!=-1) {
            SQLHandler.executeUpdate("DELETE FROM USERS WHERE UID=" + targetuid);
            SQLHandler.log("User #" + targetuid + " Deleted.");
        }
        else {
            //messege incorrect user selected!
        }
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
        if (WareNameTXT.getText().equals("")) {
            addwareerrorLBL.setVisible(true);
            return;
        } else {
            Data.getData().tmp2.name = WareNameTXT.getText();
            Data.getData().tmp2.man = WareManTXT.getText();
            Data.getData().tmp2.quantity = WareNumTXT.getText();
            Data.getData().tmp2.weight = WareWeightTXT.getText();
            Data.getData().tmp2.price = WarePriceTXT.getText();
            if (BPriceRD.isSelected())
                Data.getData().tmp2.price_s = 'B';
            else if (EPriceRD.isSelected())
                Data.getData().tmp2.price_s = 'E';
            else if (SPriceRD.isSelected())
                Data.getData().tmp2.price_s = 'S';
            Data.getData().cWarehouse.add(Data.getData().tmp2);
            Stage stage = (Stage) addwareBTN.getScene().getWindow();
            stage.close();
        }
    }

    //addware-d

    @FXML
    public void addwared (ActionEvent event) throws IOException {
        if (dWareNameTXT.getText().equals("") || dWareManTXT.getText().equals("") || dWarePriceTXT.getText().equals("") || (dWareNumTXT.getText().equals("")  && dWareWeightTXT.getText().equals(""))) {
            daddwareerrorLBL.setVisible(true);
            return;
        } else {
            Data.getData().tmp1.name = dWareNameTXT.getText();
            Data.getData().tmp1.man = dWareManTXT.getText();
            Data.getData().tmp1.quantity = dWareNumTXT.getText();
            Data.getData().tmp1.weight = dWareWeightTXT.getText();
            Data.getData().tmp1.price = dWarePriceTXT.getText();
            Data.getData().tmp1.price_s = 'E';
            Data.getData().Warehouse.add(Data.getData().tmp1);
            Stage stage = (Stage) daddwareBTN.getScene().getWindow();
            stage.close();
        }
    }

}