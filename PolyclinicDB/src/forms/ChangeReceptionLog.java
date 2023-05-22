package forms;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import DB.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
//форма для изменения журнала приема
public class ChangeReceptionLog {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ChangeButton;

    @FXML
    private TextField patient_last_name;

    @FXML
    private TextField patient_first_name;

    @FXML
    private TextField patient_midle_name;

    @FXML
    private TextField employee_last_name;

    @FXML
    private TextField employee_first_name;

    @FXML
    private TextField employee_midle_name;

    @FXML
    private TextField date;

    @FXML
    private Button back;

    @FXML
    void initialize() {
        Select_for_change();
        ChangeButton.setOnAction(actionEvent -> {
            Change();
            ChangeButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/forms/AllReceptionLog.fxml"));

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
        back.setOnAction(actionEvent -> Goback());
    }

    private void Select_for_change(){//считывание из бд
        DBwork dBwork = new DBwork();
        String select = "SELECT " + Const.PATIENT_TABLE + "." + Const.PATIENT_LASTNAME + ", " + Const.PATIENT_TABLE + "." + Const.PATIENT_FIRSTNAME + ", "  + Const.PATIENT_TABLE + "." + Const.PATIENT_MIDLENAME + ", " + Const.EMPLOYEE_TABLE+ "." + Const.EMPLOYEE_LASTNAME + ", " + Const.EMPLOYEE_TABLE+ "." + Const.EMPLOYEE_FIRSTNAME + ", " + Const.EMPLOYEE_TABLE+ "." + Const.EMPLOYEE_MIDLENAME + ", " + Const.RECEPTION_DATE + " FROM " + Const.RECEPTION_LOG_TABLE
                + " INNER JOIN " + Const.PATIENT_TABLE + " ON " + Const.RECEPTION_LOG_TABLE + "." + Const.RECEPTION_PATIENT + " = " + Const.PATIENT_TABLE + "." + Const.PATIENT_ID
                + " INNER JOIN " + Const.EMPLOYEE_TABLE + " ON " + Const.RECEPTION_LOG_TABLE + "." + Const.RECEPTION_EMPLOYEE + " = " + Const.EMPLOYEE_TABLE + "." + Const.EMPLOYEE_ID
                + " WHERE " + Const.RECEPTION_LOG_ID + " = " + AllReceptionLog.id;
        try {
            Statement statement = dBwork.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(select);

            while (resultSet.next()){
                patient_last_name.setText(resultSet.getString("last_name"));
                patient_first_name.setText(resultSet.getString("first_name"));
                patient_midle_name.setText(resultSet.getString("midle_name"));
                employee_last_name.setText(resultSet.getString("employee_last_name"));
                employee_first_name.setText(resultSet.getString("employee_first_name"));
                employee_midle_name.setText(resultSet.getString("employee_midle_name"));
                date.setText(resultSet.getString("recep_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    private void Change(){//изменение данных в бд
        DBwork dBwork = new DBwork();

        String recep_date = date.getText();

        Reception reception = new Reception(recep_date);

        dBwork.ChangeReception(reception);
    }

    private void Goback(){//возврат на предыдущую форму
        back.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/forms/AllReceptionLog.fxml"));
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
