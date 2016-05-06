package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {


    public List<String> warenames = new ArrayList<>();

    public ObservableList<String> wareItems =FXCollections.observableArrayList (warenames);

    public static Stage mainwindow;
    @Override
    public void start(Stage primaryStage) throws Exception{

        String url = "jdbc:mysql://localhost:3306/Customs";
        String username = "root";
        String password = "akmz8ki";

        try {
            Class.forName("com.mysql.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(url, username, password)) {

                Statement stmt = connection.createStatement();
                String sql;

                sql = "CREATE TABLE IF NOT EXISTS `USERS` (\n" +
                        "  `UID` int(11) NOT NULL AUTO_INCREMENT,\n" +
                        "  `username` varchar(45) NOT NULL,\n" +
                        "  `password` varchar(45) NOT NULL,\n" +
                        "  `type` char(1) NOT NULL,\n" +
                        "  `name` varchar(45) NOT NULL,\n" +
                        "  `family` varchar(45) NOT NULL,\n" +
                        "  PRIMARY KEY (`UID`),\n" +
                        "  UNIQUE KEY `username_UNIQUE` (`username`)\n" +
                        ") ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;\n";
                stmt.executeUpdate(sql);
                sql = "CREATE TABLE IF NOT EXISTS `MERCHANTS` (\n" +
                        "  `MID` int(11) NOT NULL AUTO_INCREMENT,\n" +
                        "  `name` varchar(45) DEFAULT NULL,\n" +
                        "  `family` varchar(45) DEFAULT NULL,\n" +
                        "  PRIMARY KEY (`MID`)\n" +
                        ") ENGINE=InnoDB DEFAULT CHARSET=latin1;\n";
                stmt.executeUpdate(sql);
                sql = "CREATE TABLE IF NOT EXISTS `WARE` (\n" +
                        "  `WID` int(11) NOT NULL AUTO_INCREMENT,\n" +
                        "  `name` varchar(45) NOT NULL,\n" +
                        "  `manufacturer` varchar(45) NOT NULL,\n" +
                        "  `weight` double NOT NULL,\n" +
                        "  `number` int(11) DEFAULT NULL,\n" +
                        "  `price_per` double NOT NULL,\n" +
                        "  PRIMARY KEY (`WID`)\n" +
                        ") ENGINE=InnoDB DEFAULT CHARSET=latin1;\n";
                stmt.executeUpdate(sql);
                sql = "CREATE TABLE IF NOT EXISTS `CERTIFICATES` (\n" +
                        "  `CID` int(11) NOT NULL AUTO_INCREMENT,\n" +
                        "  `description` varchar(300) DEFAULT NULL,\n" +
                        "  PRIMARY KEY (`CID`)\n" +
                        ") ENGINE=InnoDB DEFAULT CHARSET=latin1;\n";
                stmt.executeUpdate(sql);
                sql = "CREATE TABLE IF NOT EXISTS `WAREHOUSE` (\n" +
                        "  `WHID` int(11) NOT NULL,\n" +
                        "  `WID` int(11) NOT NULL,\n" +
                        "  KEY `fk_WAREHOUSE_1_idx` (`WID`),\n" +
                        "  CONSTRAINT `fk_WAREHOUSE_1` FOREIGN KEY (`WID`) REFERENCES `WARE` (`WID`) ON DELETE NO ACTION ON UPDATE NO ACTION\n" +
                        ") ENGINE=InnoDB DEFAULT CHARSET=latin1;";
                stmt.executeUpdate(sql);
                sql = "CREATE TABLE IF NOT EXISTS `CERTHOUSE` (\n" +
                        "  `CHID` int(11) NOT NULL,\n" +
                        "  `CID` int(11) NOT NULL,\n" +
                        "  PRIMARY KEY (`CHID`),\n" +
                        "  KEY `fk_CERTHOUSE_1_idx` (`CID`),\n" +
                        "  CONSTRAINT `fk_CERTHOUSE_1` FOREIGN KEY (`CID`) REFERENCES `CERTIFICATES` (`CID`) ON DELETE CASCADE\n" +
                        ") ENGINE=InnoDB DEFAULT CHARSET=latin1;\n";
                stmt.executeUpdate(sql);
                sql = "CREATE TABLE IF NOT EXISTS `DECLARATIONS` (\n" +
                        "  `DID` int(11) NOT NULL AUTO_INCREMENT,\n" +
                        "  `MID` int(11) NOT NULL,\n" +
                        "  `date` date NOT NULL,\n" +
                        "  `WHID` int(11) NOT NULL,\n" +
                        "  `source_country` varchar(45) NOT NULL,\n" +
                        "  `enterance` char(1) NOT NULL,\n" +
                        "  `CHID` int(11) NOT NULL,\n" +
                        "  PRIMARY KEY (`DID`),\n" +
                        "  KEY `fk_DECLARATIONS_1_idx` (`MID`),\n" +
                        "  CONSTRAINT `fk_DECLARATIONS_1` FOREIGN KEY (`MID`) REFERENCES `MERCHANTS` (`MID`) ON DELETE NO ACTION ON UPDATE NO ACTION\n" +
                        ") ENGINE=InnoDB DEFAULT CHARSET=latin1;\n";
                stmt.executeUpdate(sql);
                sql = "INSERT IGNORE INTO USERS\n" +
                        "values (1,'admin', 'admin', 'A' , 'admin' , 'admin') ";
                stmt.executeUpdate(sql);


            } catch (SQLException e) {
                throw new IllegalStateException("Cannot connect the database!", e);
            }

        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in the classpath!", e);
        }

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
