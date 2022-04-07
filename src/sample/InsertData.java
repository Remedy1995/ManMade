package sample;

import java.sql.*;

public class InsertData {

    ConnectDB connectDB = new ConnectDB();
    Connection connection;
    Statement statement;
    PreparedStatement preparedStatement;
    ResultSet resultSet;


    {
        try {
            connection = connectDB.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    void getData() throws SQLException, ClassNotFoundException {

        String user = "group6";
        int pass = 1234;


        preparedStatement = connection.prepareStatement("select * from user where username=? and password=?");
        preparedStatement.setString(1, user);
        preparedStatement.setInt(2, pass);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
//
            System.out.println("data already inserted here");

        } else {


            try {
                String deposit = "INSERT  INTO user(username,password) VALUES ('"+user+"','"+pass+"')";
                statement = connection.createStatement();
                statement.execute(deposit);

                System.out.println("successfully inserted data");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}





