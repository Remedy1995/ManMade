package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.*;

public class Controller3 {


    @FXML
    private TextField amount_deposittext;

    @FXML
    private TextField customer_nametext;

    @FXML
    private Label errortext;

    ConnectDB connectDB=new ConnectDB();
    Connection connection=connectDB.getConnection();
    Statement preparedStatement;
    DatabaseMetaData dbm = connection.getMetaData();
    ResultSet tables;
    PreparedStatement preparedStatement1;
    private Stage stage;
    private Scene scene;
    private Image event;


    public Controller3() throws SQLException, ClassNotFoundException {
    }
    @FXML
    void homeback(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sample2.fxml"));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void make_deposit(ActionEvent event) throws SQLException, ClassNotFoundException {
        String checkdeposit=amount_deposittext.getText();
        String customer_name=customer_nametext.getText();
         //create the table
          CreateTables createTables=new CreateTables();
              boolean isnumber=true;

        tables=dbm. getTables(null, null, "customerdeposit", null);
        if (tables.next()) {
            System.out.println("please the tables already exist");

            if (checkdeposit.equals("") || customer_name.equals("")) {
                JOptionPane.showMessageDialog(null, "Customer name or amount empty");

            }

            else if(checkdeposit.matches("^[a-zA-Z0-9]+$")){
                System.out.println("value contains alphabets try again");
                JOptionPane.showMessageDialog(null, "invalid input please enter the right amount");

                amount_deposittext.setText("");
                customer_nametext.setText("");

            }
               else {

                   double amount=Double.parseDouble(checkdeposit);

                System.out.println(amount);
//

                String checkifuserexists="SELECT * FROM customerdeposit WHERE customername=?";
                preparedStatement1=connection.prepareStatement((checkifuserexists));
                preparedStatement1.setString(1,customer_name);
                ResultSet resultSet2=preparedStatement1.executeQuery();

                if(resultSet2.next())
                {
                    double customerfunds = resultSet2.getDouble("customerdeposit");
                    System.out.println(customerfunds);

                    //function to compute fro deposits

                    Deposit deposit=new Deposit();
                    double totaldeposit= deposit.doDeposit(amount,customerfunds);
                    System.out.println( deposit.doDeposit(amount,customerfunds));

                    String query1 = "update customerdeposit set customerdeposit= ? where  customername= ?";
                    preparedStatement1 = connection.prepareStatement(query1);
                    preparedStatement1.setDouble(1, totaldeposit);
                    preparedStatement1.setString(2, customer_name);

                    // execute the java preparedstatement
                    preparedStatement1.executeUpdate();

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText(customer_name + " you have successfully made deposit of " + amount);
                    alert.setTitle("SUCCESS");
                    alert.showAndWait();

                    amount_deposittext.setText("");
                    customer_nametext.setText("");


                }
                else{


                      //if the customer does not exist let create a new user and assign a amount to hold his or her deposited value
                 String deposit = "INSERT INTO customerdeposit(customername,customerdeposit) VALUES ('" + customer_name + "','" + amount + "')";
                preparedStatement = connection.createStatement();
                preparedStatement.execute(deposit);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(customer_name + " you have successfully made deposit of " + amount);
                alert.setTitle("SUCCESS");
              alert.showAndWait();

                    amount_deposittext.setText("");
                    customer_nametext.setText("");


                }

//
//



            }
//
            }


        else{

                createTables.getTable();

            }

//        JOptionPane.showMessageDialog(null,"my name is " +customer_name+" and you have deposited an amount of "+enteredamount);
    }


    @FXML
    void backbutton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sample2.fxml"));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goback(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("sample2.fxml"));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
