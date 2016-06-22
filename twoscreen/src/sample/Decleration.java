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
            if(validateCert(rs))
                certs.add(rs.getInt("CID"));

        }
    }

    public boolean wareCompatibility(Vector<String> rulewares) throws SQLException{

        Vector<String> names = wh.getWareNames();

       if(rulewares.size() < 1)
            return true;
        for(int i = 0;i < names.size();i++)
            for(int j = 0;j < rulewares.size();j++)
                if(names.get(i).equals(rulewares.get(j)))
                    return true;
        return false;
    }

    public boolean factorCompatibility(Vector<String> rulefactors) throws SQLException{

        Vector<String> factors = wh.getFactorNames();

       if(rulefactors.size() < 1)
            return true;
        for(int i = 0;i < factors.size();i++)
            for(int j = 0;j < rulefactors.size();j++)
                if(factors.get(i).equals(rulefactors.get(j)))
                    return true;
        return false;
    }

    public boolean validateCert(ResultSet rs) throws SQLException{
        if(rs.getDate("date_to") == null || date == null || date.before(rs.getDate("date_to"))){
            if(source_country == null || rs.getString("source_country") == null || source_country == rs.getString("source_country")){
                if(enterance == null || rs.getString("enterance") == null || enterance == rs.getString("enterance")){
                    Cert ce = new Cert(rs.getInt("CHID"));
                    if(wh.wares.isEmpty() || ce.wh.wares.isEmpty() || wh.subset(ce.wh))
                        return true;
                }
            }
        }
        return false;
    }

}
