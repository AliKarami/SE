package sample;

import java.sql.*;
import java.io.*;

public class SQLHandler {

    static String url = "jdbc:mysql://localhost:3306/Customs";
    static String username = "root";
    static String password = "akmz8ki";

    public static void initTables() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                Connection con = DriverManager.getConnection(url, username, password);
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
                con.close();
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
                Connection connection = DriverManager.getConnection(url, username, password);
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                connection.close();
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

            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                Statement stmt = connection.createStatement();
                int res = stmt.executeUpdate(sql);
                connection.close();
                return res;
            } catch (SQLException e) {
                throw new IllegalStateException("Cannot connect the database!", e);
            }
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in the classpath!", e);
        }
    }

}