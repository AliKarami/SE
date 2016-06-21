package sample;

import java.util.*;
import java.sql.*;

/**
 * Created by mohammad on 6/21/16.
 */
public class dbHandler {

    //private class db = new SQLHandler();

    public Vector<Rule> getDependentRules(int did){
        Vector<Rule> rules = new Vector<Rule>();
        try {
            Decleration currentDec = new Decleration(did);
            String query = "SELECT * FROM RULE WHERE " +
                                    "(source_country IS NULL or source_country='" + currentDec.source_country + "') " +
                                "and (enterance IS NULL or enterance='" + currentDec.enterance + "') " +
                                "and ((date_from IS NULL and date_to IS NULL) " +
                                    "or (date_from IS NULL and '" + currentDec.date + "' < date_to) " +
                                    "or (date_to IS NULL and '" + currentDec.date + "' > date_from) " +
                                    "or ('" + currentDec.date + "' > date_from and '" + currentDec.date + "' < date_to))";
            ResultSet rs = SQLHandler.executeQuery(query);
            while(rs.next()){
                Rule r = new Rule(rs);
                if(currentDec.wareCompatibility(r.getWares()))
                    rules.add(r);
            }

        }catch(Exception ex){
            System.err.println("get Rules are dependant exception!");
        }
        return rules;
    }



}
