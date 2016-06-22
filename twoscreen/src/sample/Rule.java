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
    Vector<Integer> certs;
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
            setCerts();
        }catch(Exception ex){
          System.err.println("couldn't construct rule");
        }
    }

    private void setCerts() throws SQLException{
        certs = new Vector<Integer>();
        ResultSet rs = SQLHandler.executeQuery("SELECT * FROM CERTHOUSE H,CERIFICATE C WHERE H.CHID=" + chids + " and H.CID=C.CID");
        while(rs.next()){

            certs.add(rs.getInt("CID"));

        }
    }

    public Vector<String> getWares(){
        if(wares == null)
            return new Vector<String>();
        Vector<String> warelist = new Vector<String>(Arrays.asList( wares.split(",")));
        return warelist;
    }

    public Vector<String> getFactors(){
        if(wares == null)
            return new Vector<String>();
        Vector<String> factorlist = new Vector<String>(Arrays.asList( manufactures.split(",")));
        return factorlist;
    }

    public boolean isLegislate(Decleration currentDec,Vector<Cert> satisfiedCerts) throws SQLException{
        Vector<Cert> dec = new Vector<Cert>();
        Vector<Cert> rule = new Vector<Cert>();
        for(int i = 0;i < certs.size();i++){
           dec.add(new Cert(certs.get(i)));
        }
        for(int j = 0;j < currentDec.certs.size();j++){
            dec.add(new Cert(currentDec.certs.get(j)));
        }

        for(Cert rule_cert : rule){
            boolean satisfy = false;
            for(Cert dec_cert : dec){
                if(rule_cert.hasSatisfied(dec_cert)) {
                    satisfiedCerts.add(dec_cert);
                    satisfy = true;
                }
            }
            if(!satisfy)
                return false;
        }

        for(Cert dec_cert : dec){
            if(!RuleCompatibility(dec_cert.wh))
                return false;
        }

        return true;
       /* if (RuleCompatibility(currentDec.wh))
            return true;
        else
            return false;*/
    }

    public boolean RuleCompatibility(WareHouse wh){
        int pr = wh.getTotalPrice();
        int we = wh.getTotalWeight();
        int qu = wh.getTotalQuantity();
        int min = wh.getMinUnitPrice();
        int max = wh.getMaxUnitPrice();
        if(price_from < pr){
            if(price_to < 1 || pr < price_to){
                if(min > per_price_from){
                    if(per_price_to < 1 || max < per_price_to)
                        return true;
                }
            }
        }
        return false;
    }
}