package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

    Connection connection;

    String dbname = "midsem";
    String dbusername = "root";
    String password = "";


    public Connection getConnection() throws ClassNotFoundException, SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbname, dbusername, password);
        } catch (Exception e) {
            e.printStackTrace();

        }

        return connection;
    }
}