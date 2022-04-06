package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;

public class Controller4 {

    @FXML
    private TextField amount_withdrawal;

    @FXML
    private TextField customer_nametext;


    @FXML
    private Label errortext;


    ConnectDB connectDB=new ConnectDB();
    Connection connection=connectDB.getConnection();
    PreparedStatement preparedStatement;
    PreparedStatement preparedStatement1;
    private Stage stage;
    private Scene scene;

    public Controller4() throws SQLException, ClassNotFoundException {
    }
    @FXML
    void goback(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sample2.fxml"));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void make_withdrawal(ActionEvent event) throws SQLException {
        String withdrawal = amount_withdrawal.getText();
        String customername = customer_nametext.getText();

        if (withdrawal.equals("") || customername.equals("")) {
            JOptionPane.showMessageDialog(null, "Customer name or amount empty");
        } else if (withdrawal.matches("[a-zA-Z]+$")) {
            System.out.println("value contains alphabets try again");
            JOptionPane.showMessageDialog(null, "invalid input please enter the right amount");
            amount_withdrawal.setText("");
            customer_nametext.setText("");

        } else {

            double amount = Double.parseDouble(withdrawal);

            System.out.println(amount);

               try{

                   String checkname="SELECT * FROM customerdeposit WHERE customername=?";
                   preparedStatement=connection.prepareStatement((checkname));
                   preparedStatement.setString(1,customername);
                   ResultSet resultSet2=preparedStatement.executeQuery();

              if(resultSet2.next()){
                  //check if the right user exist and can withdraw his or her money


                  String query="SELECT * FROM customerdeposit WHERE customername=?";
                   preparedStatement=connection.prepareStatement((query));
                   preparedStatement.setString(1,customername);
                   ResultSet resultSet=preparedStatement.executeQuery();

                   while (resultSet.next()) {

                       System.out.println(resultSet.getString("customerdeposit"));
                       double customerfunds = resultSet.getDouble("customerdeposit");
                       String checkuser=resultSet.getString("customername");
                       //let check if user is in our database;

                       //let check if customer funds are enough for withdrawal;

                       if (customerfunds < amount) {
                           JOptionPane.showMessageDialog(null, "please your funds are not enough");

                           amount_withdrawal.setText("");
                           customer_nametext.setText("");
                       }

                       else {
                           //compute for the withdrawal
                           //get the Withdrawal function
                           Withdrawal withdrawmoney = new Withdrawal();
                           withdrawmoney.getWithdrawal(customerfunds, amount);
                           double remaining = (withdrawmoney.getWithdrawal(customerfunds, amount));
                           System.out.println(withdrawmoney.getWithdrawal(customerfunds, amount));
                           //create a variable to hold the remaining balance and update the database;

                           try {


                               // create the java mysql update preparedstatement
                               String query1 = "update customerdeposit set customerdeposit= ? where  customername= ?";
                               preparedStatement1 = connection.prepareStatement(query1);
                               preparedStatement1.setDouble(1, remaining);
                               preparedStatement1.setString(2, customername);

                               // execute the java preparedstatement
                               preparedStatement1.executeUpdate();

                               JOptionPane.showMessageDialog(null, "you have succesfully made a withdrawal");

                               amount_withdrawal.setText("");
                               customer_nametext.setText("");
                           } catch (Exception e) {
                               System.err.println("Got an exception! ");
                               System.err.println(e.getMessage());
                           }
                       }
                   }



              }

                   else{
                       JOptionPane.showMessageDialog(null,"Oops this account user does not exist");
                  amount_withdrawal.setText("");
                  customer_nametext.setText("");
              }





//
//
            }
              catch(SQLException e){
                   e.printStackTrace();
                  JOptionPane.showMessageDialog(null,"sorry");
               }
        }

    }



}
