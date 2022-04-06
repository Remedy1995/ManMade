package sample;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTables {
    ConnectDB connectDB;
    Connection connection;
    Statement statement;

    public Connection getTable() throws SQLException, ClassNotFoundException {

        try{
          connectDB =new ConnectDB();
          connection =connectDB.getConnection();

            //get the database connection

            String CustomerDeposit = "CREATE TABLE CustomerDeposit("
                            + "idNo INT(64) NOT NULL AUTO_INCREMENT,"
                            + "customername VARCHAR(100),"
                            + "customerdeposit INT(100), "
                            + "PRIMARY KEY(idNo))";
             statement = connection.createStatement();
            //This line has the issue
            statement.executeUpdate(CustomerDeposit);
            System.out.println("Table Created");

        }
        catch(SQLException e){
       e.printStackTrace();
            System.out.println("error in creating table");
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return connection;
    }
}
