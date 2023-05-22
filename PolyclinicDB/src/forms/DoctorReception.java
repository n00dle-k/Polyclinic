package forms;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DB.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
//форма с таблицей всех пациентов врача
public class DoctorReception {
    public static String id;
    public ObservableList<Patient> patients = FXCollections.observableArrayList();
    public ObservableList<Patient> find_patients = FXCollections.observableArrayList();

    @FXML
    private TableView<Patient> TableReception;

    @FXML
    private TableColumn<Patient, Integer> patient_id;

    @FXML
    private TableColumn<Patient, String> patient_last_name;

    @FXML
    private TableColumn<Patient, String> patient_first_name;

    @FXML
    private TableColumn<Patient, String> patient_midle_name;

    @FXML
    private Button back;

    @FXML
    private TextField findField;

    @FXML
    private Button findButton;

    @FXML
    void initialize() {
        SetCell();
        Select_log();
        TableReception.setRowFactory(TableReception -> {
            TableRow row = new TableRow();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Patient patient = TableReception.getSelectionModel().getSelectedItem();
                    id = patient.getPatient_id().toString();
                    System.out.println(id);
                    OpenLog();
               }
            });
            return row;
        });
        back.setOnAction(actionEvent -> {GoBack();});
        findButton.setOnAction(actionEvent -> Find());
    }

    private void SetCell(){//работа с таблицей
        patient_id.setCellValueFactory(new PropertyValueFactory<>("patient_id"));
        patient_last_name.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        patient_first_name.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        patient_midle_name.setCellValueFactory(new PropertyValueFactory<>("midle_name"));
    }

    private void Select_log(){//считывание из бд
        DBwork dBwork = new DBwork();
        String select = "SELECT DISTINCT " + Const.PATIENT_TABLE + "." + Const.PATIENT_ID + ", " +Const.PATIENT_TABLE + "." + Const.PATIENT_LASTNAME + ", " + Const.PATIENT_TABLE + "." + Const.PATIENT_FIRSTNAME + ", "  + Const.PATIENT_TABLE + "." + Const.PATIENT_MIDLENAME + " FROM " + Const.PATIENT_TABLE
                + " INNER JOIN " + Const.RECEPTION_LOG_TABLE + " ON " + Const.PATIENT_TABLE + "." + Const.PATIENT_ID + " = " + Const.RECEPTION_LOG_TABLE + "." + Const.RECEPTION_PATIENT
                + " WHERE " + Const.RECEPTION_EMPLOYEE + " = " + StartForm.employeeid;
        try {
            Statement statement = dBwork.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(select);

            while (resultSet.next()){
                Patient patient = new Patient();
                patient.setPatient_id(resultSet.getInt("patient_id"));
                patient.setLast_name(resultSet.getString("last_name"));
                patient.setFirst_name(resultSet.getString("first_name"));
                patient.setMidle_name(resultSet.getString("midle_name"));

                patients.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableReception.setItems(patients);
    }

    private void OpenLog(){//форма с информацией о приемах пациента
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

    private void GoBack(){//выход на страницу авторизации
        back.getScene().getWindow().hide();
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
        stage.show();
    }

    private void Find(){//поиск
        find_patients.clear();
        String find_patient = findField.getText();
        DBwork dBwork = new DBwork();
        String select = "SELECT " + Const.PATIENT_TABLE + "." + Const.PATIENT_ID + ", " +Const.PATIENT_TABLE + "." + Const.PATIENT_LASTNAME + ", " + Const.PATIENT_TABLE + "." + Const.PATIENT_FIRSTNAME + ", "  + Const.PATIENT_TABLE + "." + Const.PATIENT_MIDLENAME + " FROM " + Const.PATIENT_TABLE
                + " INNER JOIN " + Const.RECEPTION_LOG_TABLE + " ON " + Const.PATIENT_TABLE + "." + Const.PATIENT_ID + " = " + Const.RECEPTION_LOG_TABLE + "." + Const.RECEPTION_PATIENT
                + " WHERE " + Const.RECEPTION_EMPLOYEE + " = " + StartForm.employeeid + " AND " + Const.PATIENT_TABLE + "." + Const.PATIENT_LASTNAME + " LIKE '%" + find_patient + "%'";
        System.out.println(select);
        try {
            Statement statement = dBwork.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(select);

            while (resultSet.next()){
                Patient patient = new Patient();
                patient.setPatient_id(resultSet.getInt("patient_id"));
                patient.setLast_name(resultSet.getString("last_name"));
                patient.setFirst_name(resultSet.getString("first_name"));
                patient.setMidle_name(resultSet.getString("midle_name"));

                find_patients.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableReception.setItems(find_patients);
        TableReception.refresh();
    }
}
