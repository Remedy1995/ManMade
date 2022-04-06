package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller6 {



    @FXML
    private TextField customer_nametext;



    @FXML
    private TextField showbalance;
    ConnectDB connectDB=new ConnectDB();
    Connection connection=connectDB.getConnection();
    PreparedStatement preparedStatement;
    private Stage stage;
    private Scene scene;


    public Controller6() throws SQLException, ClassNotFoundException {
    }

    @FXML
    void check_balance(ActionEvent event) throws SQLException {
           String customername=customer_nametext.getText();

        if (customername.equals("")) {
            JOptionPane.showMessageDialog(null, "Customer name empty");
        }


        else{
                      try {
                          //checking whether entered user exist
                          String checkname = "SELECT * FROM customerdeposit WHERE customername=?";
                          preparedStatement = connection.prepareStatement((checkname));
                          preparedStatement.setString(1, customername);
                          ResultSet resultSet2 = preparedStatement.executeQuery();

                          if (resultSet2.next()) {
                              //if user exist then print the balance
                              String query = "SELECT * FROM customerdeposit WHERE customername=?";
                              preparedStatement = connection.prepareStatement((query));
                              preparedStatement.setString(1, customername);
                              ResultSet resultSet = preparedStatement.executeQuery();

                              while (resultSet.next()) {

                                  System.out.println(resultSet.getString("customerdeposit"));
                                  double customerfunds = resultSet.getDouble("customerdeposit");
                                  String change = String.format("%.2f", customerfunds);
                                    showbalance.setText(change);
                                  customer_nametext.setText("");
                              }
                          }
                          else{
                              JOptionPane.showMessageDialog(null,"account holder does not exist");
                              customer_nametext.setText("");
                          }
                      }

                          catch(Exception e){
                          e.printStackTrace();

            }



        }

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

}
