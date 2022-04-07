package sample;

import java.sql.*;

public class CreateTables {
    ConnectDB connectDB;
    Connection connection;
    Statement statement;
    Statement statement1;
    PreparedStatement statement2;

    public Connection getTable() throws SQLException, ClassNotFoundException {


        try {
            connectDB = new ConnectDB();
            connection = connectDB.getConnection();

            //get the database connection

            String CustomerDeposit = "CREATE TABLE CustomerDeposit("
                    + "idNo INT(64) NOT NULL AUTO_INCREMENT,"
                    + "customername VARCHAR(100),"
                    + "customerdeposit INT(100), "
                    + "PRIMARY KEY(idNo))";
            statement = connection.createStatement();
            //This line has the issue
            statement.executeUpdate(CustomerDeposit);
            System.out.println("Table  Customer Deposit Created");


            String User = "CREATE TABLE user("
                    + "idNo INT(64) NOT NULL AUTO_INCREMENT,"
                    + "username VARCHAR(100),"
                    + "password INT(100), "
                    + "PRIMARY KEY(idNo))";
            statement1 = connection.createStatement();
            //This line has the issue
            statement1.executeUpdate(User);
            System.out.println("Table User Created");


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in creating table");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }


}
