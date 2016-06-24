package sample;

import java.util.*;
import java.sql.*;

/**
 * Created by mohammad on 6/21/16.
 */
public class dbHandler {

    //private class db = new SQLHandler();

    public boolean registerDec(int did){
        try {
            Decleration currentDec = new Decleration(did);
            Vector<Rule> depRules = getDependentRules(currentDec);
            Vector<Rule> result = checkLaws(currentDec,depRules);
            if(result == null)
                System.out.println("Register Success!");
            else if(result.size() > 0) {
                System.out.println("Register Failed!");
                SQLHandler.executeUpdate("DELETE * FROM Decleration WHERE DID=" + did);
            }
            else
                System.out.println("Register in vector size Exception!");
            return true;
        }catch (Exception ex){
            System.err.println("registration SQL Exception");
        }
        return false;
    }


    public Vector<Rule> getDependentRules(Decleration currentDec){
        Vector<Rule> rules = new Vector<Rule>();
        try {
            java.sql.Date sqlDate = new java.sql.Date(currentDec.date.getTime());
            String query = "SELECT * FROM RULE WHERE " +
                                    "(source_country IS NULL or source_country='" + currentDec.source_country + "') " +
                                "and (enterance='F' or enterance='" + currentDec.enterance + "') " +
                                "and ((date_from IS NULL and date_to IS NULL) " +
                                    "or (date_from IS NULL and '" + sqlDate + "' < date_to) " +
                                    "or (date_to IS NULL and '" + sqlDate + "' > date_from) " +
                                    "or ('" + sqlDate + "' > date_from and '" + sqlDate + "' < date_to))";
            ResultSet rs = SQLHandler.executeQuery(query);
            while(rs.next()){
                Rule r = new Rule(rs);
                if(currentDec.wareCompatibility(r.getWares()) || currentDec.factorCompatibility(r.getFactors()))
                    if(r.RuleCompatibility(currentDec.wh))
                        rules.add(r);
            }

        }catch(Exception ex){
            System.err.println("get Rules are dependant exception!");
        }
        return rules;
    }

    public Vector<Rule> checkLaws(Decleration currentDec,Vector<Rule> rules){
        Vector<Rule> result = new Vector<Rule>();
        Vector<Cert> satisfiedCerts = new Vector<Cert>();
        try {
            for (Rule r : rules) {
                if (!(r.isLegislate(currentDec,satisfiedCerts))) {
                    result.add(r);
                }
            }
        }catch (Exception ex){
            System.err.println("check law exception!");
            return result;
        }finally {
            if (result.size() < 1) {
                updateCertificates(currentDec,satisfiedCerts);
                return null;
            } else
                return result;
        }

    }

    public void updateCertificates(Decleration currentDec,Vector<Cert> satisfiedCerts){
        try {
            for (Cert crt : satisfiedCerts) {
                crt.wh.minus(currentDec.wh);
            }
        }catch (Exception ex){
            System.err.println("update certificates error!");
        }
    }


}
