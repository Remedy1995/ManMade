package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;

public class Controller {

    @FXML
    private TextField passwordtext;

    @FXML
    private TextField usertext;

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    void addbutton(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
         ConnectDB connectDB=new ConnectDB();
         Connection connection=connectDB.getConnection();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
//         String sql="INSERT INTO user (username,password) VALUES ('"+usertext.getText()+"','"+passwordtext.getText()+"')";
//        Statement statement=connection.createStatement();
//        statement.execute(sql);
//        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setHeaderText("You have successfully added to the database");
//        alert.setTitle("SUCCESS");
//        alert.showAndWait();

        String uname=usertext.getText();
        String password=passwordtext.getText();

        if(uname.equals("") || password.equals("")){
            JOptionPane.showMessageDialog(null,"Username or password empty");

        }

        else {
            try {
//            JOptionPane.showMessageDialog(null,"you are welcome");ooo
                preparedStatement = connection.prepareStatement("select * from user where username=? and password=?");
                preparedStatement.setString(1, uname);
                preparedStatement.setString(2, password);
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
//                    JOptionPane.showMessageDialog(null, "you are welcome");

                    Parent root = FXMLLoader.load(getClass().getResource("sample2.fxml"));
                       stage= (Stage)((Node)event.getSource()).getScene().getWindow();
                       stage.setResizable(false);
                       scene=new Scene(root);
                       stage.setScene(scene);
                       stage.show();



                } else {
                    JOptionPane.showMessageDialog(null, "incorrect login please try again");
                 //clear data in textfield
                  passwordtext.setText("");
                  usertext.setText("");


                }
            }
            catch (SQLException e)

            {
                e.printStackTrace();
            }
        }

    }

}
