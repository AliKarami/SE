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
    Vector<String> wares;
    String source_country;
    String enterance;
    Vector<String> certs;

    public Decleration(int d) throws SQLException{
        ResultSet rs = SQLHandler.executeQuery("SELECT * FROM Decleration WHERE DID=" + d);
        if(rs.next()) {

            did = d;
            date = rs.getDate("date");
            source_country = rs.getString("source_country");
            enterance = rs.getString("enterance");
            setWares(rs.getInt("WHID"));
            setCerts(rs.getInt("CHID"));

        }else{
            did = -1;
            System.err.println("couldn't find decleration in construction!");
        }
    }

    private void setWares(int whid) throws SQLException{
        wares = new Vector<String>();
        ResultSet rs = SQLHandler.executeQuery("SELECT * FROM WAREHOUSE H,WARE W WHERE H.WHID=" + whid + " and H.WID=W.WID");
        while(rs.next()){

                wares.add(rs.getString("name"));

        }
    }

    private void setCerts(int chid) throws SQLException{
        certs = new Vector<String>();
    }

    public boolean wareCompatibility(Vector<String> rulewares){
        if(rulewares.size() < 1)
            return true;
        for(int i = 0;i < wares.size();i++)
            for(int j = 0;j < rulewares.size();j++)
                if(wares.get(i).equals(rulewares.get(j)))
                    return true;
        return false;
    }

}
