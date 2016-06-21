package sample;

import sample.SQLHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by mohammad on 6/21/16.
 */
public class Ware {
    int wid;
    String name;
    String manufacturer;
    int weight;
    int quantity;
    int price;
    int to_price;

    public Ware(int d) throws SQLException {
        ResultSet rs = SQLHandler.executeQuery("SELECT * FROM Ware WHERE WID=" + d);
        if(rs.next()) {

            wid = d;
            name = rs.getString("name");
            manufacturer = rs.getString("manufacturer");
            weight = rs.getInt("weight");
            quantity = rs.getInt("quantity");
            price = rs.getInt("price");
            to_price = rs.getInt("to_price");
        }else{
            wid = -1;
            System.err.println("couldn't find ware in construction!");
        }
    }

    public boolean subset(Ware w){
        if(w.manufacturer == null || manufacturer.equals(w.manufacturer)){
            if(w.weight < 1 || weight < w.weight){
                if(w.quantity < 1 || quantity < w.quantity)
                    if(w.price < 1 || price < w.price)
                        return true;
            }
        }
        return false;
    }
}
