package forms;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class Admin {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuBar adminMenu;

    @FXML
    private MenuItem openList;

    @FXML
    private MenuItem dump;

    @FXML
    private MenuItem restore;

    @FXML
    private Button exit;

    @FXML
    void openEmployee(ActionEvent event) {
        adminMenu.getScene().getWindow().hide();
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
        stage.showAndWait();
    }

    @FXML
    void initialize() {
        dump.setOnAction(actionEvent -> {
            Dump();
            System.out.println("dump");
        });
        restore.setOnAction(actionEvent -> {
            Restore();
            System.out.println("restore");
        });
        exit.setOnAction(actionEvent -> {
            exit.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/forms/Start.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
    }

    private void Dump(){
        Process proc = null;
        try {
            proc = Runtime.getRuntime().exec("cmd /c C:\\5\\dump1.bat");
            proc.waitFor();
            proc.destroy();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void Restore(){
        Process proc = null;
        try {
            proc = Runtime.getRuntime().exec("cmd /c C:\\5\\restore.bat");
            proc.waitFor();
            proc.destroy();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
