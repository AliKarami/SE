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
    String price_s;

    public Ware(int d) throws SQLException {
        ResultSet rs = SQLHandler.executeQuery("SELECT * FROM Ware WHERE WID=" + d);
        if(rs.next()) {

            wid = d;
            name = rs.getString("name");
            manufacturer = rs.getString("manufacturer");
            weight = rs.getInt("weight");
            quantity = rs.getInt("quantity");
            price = rs.getInt("price");
            price_s = rs.getString("price_s");
        }else{
            wid = -1;
            System.err.println("couldn't find ware in construction!");
        }
    }

    public boolean subset(Ware w){
        if(w.manufacturer == null || manufacturer.equals(w.manufacturer)){
            if(w.weight < 1 || weight < w.weight){
                if(w.quantity < 1 || quantity < w.quantity) {
                    if (w.price < 1)
                        return true;
                    else{
                        switch(w.price_s.charAt(0)){
                            case 'B':
                                if(price > w.price)
                                    return true;
                                break;
                            case 'S':
                                if(price < w.price)
                                    return true;
                                break;
                            case 'E':
                                if(price == w.price)
                                    return true;
                                break;
                            default:
                                System.err.println("Price_S String error!");
                                return false;
                        }
                    }
                }
            }
        }
        return false;
    }

    public void minus(Ware w){
        if(weight > 0 && w.weight > 0)
            SQLHandler.executeUpdate("UPDATE Ware SET wheight=wheight-" + w.weight + " WHERE WID=" + wid);
        if(quantity > 0 && w.quantity > 0)
            SQLHandler.executeUpdate("UPDATE Ware SET quantity=quantity-" + w.quantity + " WHERE WID=" + wid);

    }

}
