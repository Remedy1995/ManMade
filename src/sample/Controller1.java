package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class Controller1 {

    @FXML
    private ImageView am;
    private Stage stage;
    private Scene scene;


    @FXML
    void logout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

    @FXML
    void accountbalance(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sample6.fxml"));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void applyLoan(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sample5.fxml"));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void make_deposit(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sample3.fxml"));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void withdrawal(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sample4.fxml"));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
