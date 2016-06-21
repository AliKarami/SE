package sample;

import java.util.*;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by mohammad on 6/21/16.
 */
public class Rule {

    int rid;
    Date date_from;
    Date date_to;
    String source_country;
    String enterance;
    int price_from;
    int price_to;
    int per_price_from;
    int per_price_to;
    String wares;
    String manufactures;
    int chids;

    public Rule(ResultSet rs){
        try {
            rid = rs.getInt("RID");
            date_from = rs.getDate("date_from");
            date_to = rs.getDate("date_to");
            source_country = rs.getString("source_country");
            enterance = rs.getString("enterance");
            price_from = rs.getInt("price_from");
            price_to = rs.getInt("price_to");
            per_price_from = rs.getInt("per_price_from");
            per_price_to = rs.getInt("per_price_to");
            wares = rs.getString("wares");
            manufactures = rs.getString("manufactures");
            chids = rs.getInt("CHID");
        }catch(Exception ex){
          System.err.println("couldn't construct rule");
        }
    }

    public Vector<String> getWares(){
        Vector<String> warelist = new Vector<String>(Arrays.asList( wares.split(",")));
        return warelist;
    }
}
