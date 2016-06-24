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
        ResultSet rs = SQLHandler.executeQuery("SELECT * FROM CERTHOUSE H,CERTIFICATE C WHERE H.CHID=" + chids + " and H.CID=C.CID");
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
        SQLHandler.log("start legislate");
        Vector<Cert> dec = new Vector<Cert>();
        Vector<Cert> rule = new Vector<Cert>();

        for(int i = 0;certs != null && i < certs.size();i++){
            SQLHandler.log("Rule Added in islegislate");
           rule.add(new Cert(certs.get(i)));
        }
        SQLHandler.log("for2");
        for(int j = 0;currentDec.certs != null && j < currentDec.certs.size();j++){
            SQLHandler.log("cert Added");
            dec.add(new Cert(currentDec.certs.get(j)));
        }
        SQLHandler.log("for3");
        for(Cert rule_cert : rule){
            SQLHandler.log("not important rule_cert");
            boolean satisfy = false;
            for(Cert dec_cert : dec){
                if(rule_cert.hasSatisfied(dec_cert)) {

                    satisfy = true;
                }
            }
            if(!satisfy)
                return false;
        }
        SQLHandler.log("for4");
        for(Cert dec_cert : dec){
            SQLHandler.log("start cert survey");
            if(validateCert(dec_cert)) {
                SQLHandler.log("validated");
                if (dec_cert.wh.wares.isEmpty()) {
                    SQLHandler.log("empty warehouse");
                    if (price_to < 1 || dec_cert.price_to < price_to)
                        return true;
                } else {
                    SQLHandler.log("not empty warehouse");
                    if (RuleCompatibility(dec_cert.wh)) {
                        satisfiedCerts.add(dec_cert);
                        return true;
                    }
                }
            }
        }
        SQLHandler.log("end ligislate");
        return false;
       /* if (RuleCompatibility(currentDec.wh))
            return true;//
        else
            return false;*/
    }

    public boolean RuleCompatibility(WareHouse wh){
        SQLHandler.log("Rule Compatibilty");
        int pr = wh.getTotalPrice();
        int we = wh.getTotalWeight();
        int qu = wh.getTotalQuantity();
        int min = wh.getMinUnitPrice();
        int max = wh.getMaxUnitPrice();
        if(price_from <= pr){
            SQLHandler.log("1");
            if(price_to < 1 || pr < price_to){
                SQLHandler.log("2");
                if(per_price_from < 1 || min > per_price_from){
                    SQLHandler.log("3");
                    if(per_price_to < 1 || max < per_price_to)
                        SQLHandler.log("4");
                        return true;
                }
            }
        }
        return false;
    }

    public boolean validateCert(Cert ce) throws SQLException{
        if(date_from == null || ce.date_to == null || ce.date_to.after(date_from)) {
            if (date_to == null || ce.date_to == null || ce.date_to.before(date_to)) {
                if (source_country == null || ce.source_country == null || source_country.equals(ce.source_country)) {
                    if (enterance.equals("F") || ce.enterance.equals("F") || enterance.equals(ce.enterance)) {
                         return true;
                    }
                }
            }
        }
        return false;
    }
}