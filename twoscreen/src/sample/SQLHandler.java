package sample;

import java.sql.*;
import java.io.*;

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
                    stmt.executeUpdate("INSERT INTO USERS VALUES (1,'admin','admin','A','adminname','adminfamily') ON DUPLICATE KEY UPDATE name=name");
                } catch (Exception e) {
                    throw new IllegalStateException("init.sql file not found or IOException!", e);
                }
            } catch (SQLException e) {
                throw new IllegalStateException("Cannot connect the database!", e);
            }
        } catch (ClassNotFoundException e) {
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
                return rs;
            } catch (SQLException e) {
                throw new IllegalStateException("Cannot connect the database!", e);
            }
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in the classpath!", e);
        }
    }

    public static int executeUpdate(String sql) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            try {
                Statement stmt = con.createStatement();
                int res = stmt.executeUpdate(sql);
                return res;
            } catch (SQLException e) {
                throw new IllegalStateException("Cannot connect the database!", e);
            }
        } catch (ClassNotFoundException e) {
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

}