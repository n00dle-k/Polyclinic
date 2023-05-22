package Dialog;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import DB.DBwork;
import forms.AllEmployees;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DeleteEmployee {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button delete;

    @FXML
    private Button noDelete;

    @FXML
    void initialize() {
       delete.setOnAction(actionEvent -> {
           Delete();
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(getClass().getResource("/forms/AllEmployees.fxml"));

           try {
               loader.load();
           } catch (IOException e) {
               e.printStackTrace();
           }

           Parent root = loader.getRoot();
           Stage stage = new Stage();
           stage.setScene(new Scene(root));
           stage.show();
       });
    }

    private void Delete(){
        DBwork dBwork = new DBwork();
        dBwork.DeleteEmployee();
    }
}
