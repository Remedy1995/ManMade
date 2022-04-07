package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Main extends Application {

    ConnectDB connectDB=new ConnectDB();
    Connection connection=connectDB.getConnection();
    ResultSet tables;
    DatabaseMetaData dbm;

    public Main() throws SQLException, ClassNotFoundException {
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        //create all tables
        CreateTables createTables=new CreateTables();
        InsertData insertData=new InsertData();
        insertData.getData();

        dbm = connection.getMetaData();
        tables=dbm. getTables(null, null, "customerdeposit", null);
//        tables=dbm. getTables(null, null, "user", null);
        if (tables.next()) {
            System.out.println("please the tables already exist");
        }

        else {
            createTables.getTable();
        }
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Microfinance Application");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
