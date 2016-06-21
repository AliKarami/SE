package sample;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.math.*;

/**
 * Created by mohammad on 6/21/16.
 */
public class WareHouse {

    Vector<Integer> wares;

    public WareHouse(int whid) {
        wares = new Vector<Integer>();
        try {
            ResultSet rs = SQLHandler.executeQuery("SELECT * FROM WAREHOUSE H,WARE W WHERE H.WHID=" + whid + " and H.WID=W.WID");
            while (rs.next()) {

                wares.add(rs.getInt("WID"));

            }
        }catch(Exception ex){
            System.err.println("warehouse construction error!");
        }
    }

    public int getMinUnitPrice(){
        int min = Integer.MAX_VALUE;
        try {
            for (Integer id : wares) {
                ResultSet rs = SQLHandler.executeQuery("SELECT * FROM WARE WHERE WID=" + id);
                if(rs.next()){
                    min += Math.min(rs.getInt("price"),min);
                }
            }
        }catch (Exception ex){
            System.out.println("get min error!");
        }
        return min;
    }



    public int getMaxUnitPrice(){
        int max = Integer.MIN_VALUE;
        try {
            for (Integer id : wares) {
                ResultSet rs = SQLHandler.executeQuery("SELECT * FROM WARE WHERE WID=" + id);
                if(rs.next()){
                    max += Integer.max(max,rs.getInt("price"));
                }
            }
        }catch (Exception ex){
            System.out.println("get total max error!");
        }
        return max;
    }

    public int getTotalWeight(){
        int total = 0;
        try {
            for (Integer id : wares) {
                ResultSet rs = SQLHandler.executeQuery("SELECT * FROM WARE WHERE WID=" + id);
                if(rs.next()){
                    total += rs.getInt("weight");
                }
            }
        }catch (Exception ex){
            System.out.println("get total weight error!");
        }
        return total;
    }


    public int getTotalPrice(){
        int total = 0;
        try {
            for (Integer id : wares) {
                ResultSet rs = SQLHandler.executeQuery("SELECT * FROM WARE WHERE WID=" + id);
                if(rs.next()){
                    total += rs.getInt("price")*rs.getInt("quantity");
                }
            }
        }catch (Exception ex){
            System.out.println("get total price error!");
        }
        return total;
    }

    public int getTotalQuantity(){
        int total = 0;
        try {
            for (Integer id : wares) {
                ResultSet rs = SQLHandler.executeQuery("SELECT * FROM WARE WHERE WID=" + id);
                if(rs.next()){
                    total += rs.getInt("quantity");
                }
            }
        }catch (Exception ex){
            System.out.println("get total price error!");
        }
        return total;
    }

    public boolean subset(WareHouse wh) throws SQLException{
        Vector<Ware> wh_rule = new Vector<Ware>();
        Vector<Ware> wh_dec = new Vector<Ware>();
        for(Integer w1 : wares)
            wh_rule.add(new Ware(w1));
        for(Integer w2 : wh.wares)
            wh_dec.add(new Ware(w2));

        for(Ware w1 : wh_rule)
            for(Ware w2 : wh_dec)
                if(w1.name.equals(w2.name) && !w1.subset(w2))
                    return false;
        return true;
    }

    public void minus(WareHouse wh) throws SQLException{
        Vector<Ware> wh_rule = new Vector<Ware>();
        Vector<Ware> wh_dec = new Vector<Ware>();
        for(Integer w1 : wares)
            wh_rule.add(new Ware(w1));
        for(Integer w2 : wh.wares)
            wh_dec.add(new Ware(w2));

        for(Ware w1 : wh_rule)
            for(Ware w2 : wh_dec)
                if(w1.name.equals(w2.name))
                  w1.minus(w2);

    }

}
