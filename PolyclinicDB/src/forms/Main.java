package forms;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//основной класс, из которого запускается программа
public class Main extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Start.fxml"));
        stage.setTitle("Авторизация");
        stage.setScene(new Scene(root, 500, 500));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
