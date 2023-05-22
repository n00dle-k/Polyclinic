package Context;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
//класс, отвечающий за меню при нажатии на сотрудника
public class ContextEmployee {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button deleteEmployee;

    @FXML
    private Button changeEmployee;

    @FXML
    void initialize() {
        deleteEmployee.setOnAction(actionEvent -> {Delete();});
        changeEmployee.setOnAction(actionEvent -> {Change();});
    }

    private void Delete(){//метод вызова диалогового окна при удалении
        deleteEmployee.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Dialog/DeleteEmployee.fxml"));
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

    private void Change(){//метод вызова формы для изменения данных
        changeEmployee.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/forms/ChangeEmployee.fxml"));
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
