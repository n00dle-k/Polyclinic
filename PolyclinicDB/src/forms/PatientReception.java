package forms;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import DB.Const;
import DB.DBwork;
import DB.Reception;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
//класс с формой изменения информации о приеме
public class PatientReception {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button back;

    @FXML
    private Button addButton;

    @FXML
    private TextField last_name;

    @FXML
    private TextField symptoms;

    @FXML
    private TextField treatment;

    @FXML
    private TextField diagnosis;

    @FXML
    private TextField midle_name;

    @FXML
    private TextField first_name;
    @FXML
    void initialize() {
        selectForChange();
        addButton.setOnAction(actionEvent -> {
            Change();
            addButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/forms/AllPatientReception.fxml"));
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
        back.setOnAction(actionEvent -> {Goback();});
    }

    private void selectForChange(){//счтиывание информации из бд
        DBwork dBwork = new DBwork();
        String select = "SELECT " + Const.PATIENT_TABLE + "." + Const.PATIENT_LASTNAME + ", " + Const.PATIENT_TABLE + "." + Const.PATIENT_FIRSTNAME + ", "  + Const.PATIENT_TABLE + "." + Const.PATIENT_MIDLENAME + ", " + Const.RECEPTION_SYMPTOMS + ", " + Const.RECEPTION_DIAGNOSIS + ", " + Const.RECEPTION_TREATMENT + " FROM " + Const.RECEPTION_LOG_TABLE
                + " INNER JOIN " + Const.PATIENT_TABLE + " ON " + Const.RECEPTION_LOG_TABLE + "." + Const.RECEPTION_PATIENT + " = " + Const.PATIENT_TABLE + "." + Const.PATIENT_ID
                + " WHERE " + Const.RECEPTION_LOG_TABLE + "." + Const.RECEPTION_LOG_ID + " = " + AllPatientReception.id;
        try {
            Statement statement = dBwork.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(select);
            while (resultSet.next()){
                last_name.setText(resultSet.getString("last_name"));
                first_name.setText(resultSet.getString("first_name"));
                midle_name.setText(resultSet.getString("midle_name"));
                symptoms.setText(resultSet.getString("symptoms"));
                diagnosis.setText(resultSet.getString("diagnosis_id"));
                treatment.setText(resultSet.getString("treatment"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void Change(){//изменение информации в бд
        DBwork dBwork = new DBwork();
        String recep_symptoms = symptoms.getText();
        String recep_treatment = treatment.getText();
        String recep_diagnosis = diagnosis.getText();

        Reception reception = new Reception(recep_symptoms, recep_treatment, recep_diagnosis);
        dBwork.PatientReception(reception);
    }

    private void Goback(){//переход на предыдущую форму
        back.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/forms/AllPatientReception.fxml"));
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
