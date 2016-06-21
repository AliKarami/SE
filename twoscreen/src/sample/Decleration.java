package sample;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
/**
 * Created by mohammad on 6/21/16.
 */
public class Decleration {

    int did;
    Date date;

    int chids;
    WareHouse wh;
    String source_country;
    String enterance;
    Vector<Integer> certs;

    public Decleration(int d) throws SQLException{
        ResultSet rs = SQLHandler.executeQuery("SELECT * FROM Decleration WHERE DID=" + d);
        if(rs.next()) {

            did = d;
            date = rs.getDate("date");
            source_country = rs.getString("source_country");
            enterance = rs.getString("enterance");
            chids = rs.getInt("CHID");
            wh = new WareHouse(rs.getInt("WHID"));
            setCerts();
        }else{
            did = -1;
            System.err.println("couldn't find decleration in construction!");
        }
    }


    private void setCerts() throws SQLException{
        certs = new Vector<Integer>();
        ResultSet rs = SQLHandler.executeQuery("SELECT * FROM CERTHOUSE H,CERIFICATE C WHERE H.CHID=" + chids + " and H.CID=C.CID");
        while(rs.next()){

            certs.add(rs.getInt("CID"));

        }
    }

    public boolean wareCompatibility(Vector<String> rulewares){

        if(rulewares.size() < 1)
            return true;
        for(int i = 0;i < wh.wares.size();i++)
            for(int j = 0;j < rulewares.size();j++)
                if(wh.wares.get(i).equals(rulewares.get(j)))
                    return true;
        return false;
    }

}
