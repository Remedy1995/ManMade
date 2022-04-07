package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class Controller5 {

    @FXML
    private TextField amountLoan;

    @FXML
    private Slider rate;

    @FXML
    private TextField total;

    @FXML
    private TextField yearduration;
    private Stage stage;
    private Scene scene;


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
    void simpleinterest(ActionEvent event) {


        String getrate = String.valueOf(rate.getValue());

        String amountLoanText = amountLoan.getText();

        String year = yearduration.getText();
        if (getrate.isEmpty() || amountLoanText.equals("") || year.equals("")) {
            JOptionPane.showMessageDialog(null, "please input the missing fields");
        } else if (amountLoanText.matches("^[a-zA-Z0-9]+$") || year.matches("^[a-zA-Z0-9]+$")) {
            System.out.println("value contains alphabets try again");
            JOptionPane.showMessageDialog(null, "invalid input please enter the right amount");
            rate.setMin(0);

            amountLoan.setText("");

            yearduration.setText("");

        } else {


            //parse all string into double for computation
            double parserate = Double.parseDouble(getrate);
            double parseyear = Double.parseDouble(year);
            double parseloan = Double.parseDouble(amountLoanText);
            System.out.println(parseloan);
            System.out.println(parseyear);
            System.out.println(parserate);
            //let get our function

            SimpleInterest simpleInterest = new SimpleInterest();
            double simple_interest = simpleInterest.getSimpleInterest(parseloan, parseyear, parserate);

            System.out.println(simple_interest);

            String change = String.format("%.2f", simple_interest);

            System.out.println(change);

//        String seeinterest=String.valueOf(simple_interest);

            total.setText(change);

            rate.setMin(0);

            amountLoan.setText("");

            yearduration.setText("");

        }
    }
}
