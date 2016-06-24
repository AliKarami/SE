package sample;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SQLHandler {

    static String url = "jdbc:mysql://localhost:3306/Customs";
    static String username = "root";
    static String password = "akmz8ki";
    private static Connection con = null;

    SQLHandler() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in the classpath!", e);
        }
        try {
            Connection con = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static String currentTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public static void log(String str) {
        try {
            String temp = "[" + currentTime() + "] " + str + "\n";
            Files.write(Paths.get("/home/ali/Documents/SE/Gomrok/twoscreen/out/log.txt"), temp.getBytes(), StandardOpenOption.APPEND);
//
        } catch (IOException e) {
            System.err.println("logging exception!");
        }
    }

    public static void initTables() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                con = DriverManager.getConnection(url, username, password);
                Statement stmt = con.createStatement();
                String s            = new String();
                StringBuffer sb = new StringBuffer();
                try {
                    BufferedReader in = new BufferedReader(new FileReader(new File("/home/ali/Documents/SE/Gomrok/twoscreen/src/sample/init.sql")));
                    while((s = in.readLine()) != null)
                    {
                        sb.append(s);
                    }
                    in.close();
                    String[] inst = sb.toString().split(";");

                    for(int i = 0; i<inst.length; i++)
                    {
                        if(!inst[i].trim().equals(""))
                        {
                            stmt.executeUpdate(inst[i]);
                            System.out.println(">>"+inst[i]);
                        }
                    }
                    log("initializing Tables completed.");
                    stmt.executeUpdate("INSERT INTO USERS VALUES (1,'admin','admin','A','adminname','adminfamily') ON DUPLICATE KEY UPDATE name=name");
                    log("admin:admin user added.");
                } catch (Exception e) {
                    log("init.sql file not found or IOException!");
                    throw new IllegalStateException("init.sql file not found or IOException!", e);
                }
            } catch (SQLException e) {
                log("Cannot connect the database!");
                throw new IllegalStateException("Cannot connect the database!", e);
            }
        } catch (ClassNotFoundException e) {
            log("Cannot find the driver in the classpath!");
            throw new IllegalStateException("Cannot find the driver in the classpath!", e);
        }
    }

    public static ResultSet executeQuery(String sql) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                con = DriverManager.getConnection(url, username, password);
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                String res = (!rs.isBeforeFirst())?"doesn't":"";
                log("executed Query: \"" + sql + "\" " + res + " have result.");
                return rs;
            } catch (SQLException e) {
                log("Cannot connect the database or SQL error on excecuteQuery \"" + sql + "\"!");
                throw new IllegalStateException("Cannot connect the database or SQL error!", e);
            }
        } catch (ClassNotFoundException e) {
            log("Cannot find the driver in the classpath on excecuteQuery \"" + sql + "\"!");
            throw new IllegalStateException("Cannot find the driver in the classpath!", e);
        }
    }

    public static int executeUpdate(String sql) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            try {
                Statement stmt = con.createStatement();
                int res = stmt.executeUpdate(sql);
                log("executed Update: \"" + sql + "\" update " + res + " rows.");
                return res;
            } catch (SQLException e) {
                log("Cannot connect the database or SQL error on excecuteUpdate \"" + sql + "\"!");
                throw new IllegalStateException("Cannot connect the database or SQL error!", e);
            }
        } catch (ClassNotFoundException e) {
            log("Cannot find the driver in the classpath on excecuteQuery \"" + sql + "\"!");
            throw new IllegalStateException("Cannot find the driver in the classpath!", e);
        }
    }

    public static int getMaxWHID() {
        int whid = -1;
        ResultSet rs = SQLHandler.executeQuery("SELECT WHID FROM WAREHOUSE ORDER BY WHID DESC LIMIT 1;");
        try {
            if (!rs.next()) {
                whid = 0;
            } else {
                whid = Integer.parseInt(rs.getString("WHID"));
            }
        } catch (SQLException e) {
            System.err.println("finding max whid exception!");
        }
        return whid;
    }

    public static int getMaxCHID() {
        int chid = -1;
        ResultSet rs = SQLHandler.executeQuery("SELECT CHID FROM CERTHOUSE ORDER BY CHID DESC LIMIT 1;");
        try {
            if (!rs.next()) {
                chid = 0;
            } else {
                chid = Integer.parseInt(rs.getString("CHID"));
            }
        } catch (SQLException e) {
            System.err.println("finding max chid exception!");
        }
        return chid;
    }

    public static int getMaxWID() {
        int wid = -1;
        ResultSet rs = SQLHandler.executeQuery("SELECT WID FROM WARE ORDER BY WID DESC LIMIT 1;");
        try {
            if (!rs.next()) {
                wid = 0;
            } else {
                wid = Integer.parseInt(rs.getString("WID"));
            }
        } catch (SQLException e) {
            System.err.println("finding max wid exception!");
        }
        return wid;
    }

    public static int getMaxCID() {
        int cid = -1;
        ResultSet rs = SQLHandler.executeQuery("SELECT CID FROM CERTIFICATE ORDER BY CID DESC LIMIT 1;");
        try {
            if (!rs.next()) {
                cid = 0;
            } else {
                cid = Integer.parseInt(rs.getString("CID"));
            }
        } catch (SQLException e) {
            System.err.println("finding max cid exception!");
        }
        return cid;
    }

    public static int getLastDID() {
        int did = -1;
        ResultSet rs = SQLHandler.executeQuery("SELECT DID FROM DECLERATION ORDER BY DID DESC LIMIT 1;");
        try {
            if (!rs.next()) {
                did = 0;
            } else {
                did = Integer.parseInt(rs.getString("DID"));
            }
        } catch (SQLException e) {
            System.err.println("finding last did exception!");
        }
        return did;
    }

    public static int getLastRID() {
        int rid = -1;
        ResultSet rs = SQLHandler.executeQuery("SELECT RID FROM RULE ORDER BY RID DESC LIMIT 1;");
        try {
            if (!rs.next()) {
                rid = 0;
            } else {
                rid = Integer.parseInt(rs.getString("RID"));
            }
        } catch (SQLException e) {
            System.err.println("finding last rid exception!");
        }
        return rid;
    }

}