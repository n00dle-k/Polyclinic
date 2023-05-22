package Context;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import DB.DBwork;
import forms.AllPatient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
//класс с меню
public class ContextReception {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button deleteReceptionLog;

    @FXML
    private Button changeReceptionLog;

    @FXML
    void initialize() {
        deleteReceptionLog.setOnAction(actionEvent -> {Delete();});
        changeReceptionLog.setOnAction(actionEvent -> {Change();});
    }

    private void Delete(){//вызов диалоогового окна при удалении
        deleteReceptionLog.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Dialog/DeleteReceptionLog.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    private void Change(){//вызов окна для изменения данных
        changeReceptionLog.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/forms/ChangeReceptionLog.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
