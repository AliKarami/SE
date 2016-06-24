package sample;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by mohammad on 6/21/16.
 */
public class Cert {

    int cid;
    Date date_to;
    int price_to;
    String source_country;
    String enterance;
    WareHouse wh;

    public Cert(int d) throws SQLException {
        ResultSet rs = SQLHandler.executeQuery("SELECT * FROM Certificate WHERE CID=" + d);
        if(rs.next()) {

            cid = d;
            date_to = rs.getDate("date_to");
            price_to = rs.getInt("price_to");
            source_country = rs.getString("source_country");
            enterance = rs.getString("enterance");
            wh = new WareHouse(rs.getInt("WHID"));

        }else{
            cid = -1;
            System.err.println("couldn't find certificate in construction!");
        }
    }

    public boolean hasSatisfied(Cert cert) throws SQLException{
       /* if(cert.date_to == null || date_to == null || cert.date_to.before(date_to)){
            if(cert.source_country == null || source_country == null || cert.source_country.equals(source_country)){
                if(cert.enterance.equals("F") || enterance.equals("F") || cert.enterance.equals(enterance)){
                    if(wh.wares.isEmpty() || cert.wh.wares.isEmpty() || cert.wh.subset(wh))
                        return true;
                }
            }
        }*/
        if(cid == cert.cid)
            return true;
        return false;
    }

    public Vector<String> getWares(){
        Vector<String> warelist = new Vector<String>();
        try {
            for (Integer id : wh.wares) {
                ResultSet rs = SQLHandler.executeQuery("SELECT * FROM Certificate WHERE CID=" + id);
                while(rs.next())
                    warelist.add(rs.getString("name"));
            }
        }catch (Exception ex){
            System.err.println("get certificate wares error!");
        }
        return warelist;
    }

}
