package forms;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import DB.Const;
import DB.DBwork;
import DB.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
//класс с таблицей всех пациентов
public class AllPatient {
    public static String id;
    public ObservableList<Patient> patients = FXCollections.observableArrayList();
    public ObservableList<Patient> find_patients = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Patient> TablePatient;

    @FXML
    private TableColumn<Patient, Integer> patient_id;

    @FXML
    private TableColumn<Patient, String> last_name;

    @FXML
    private TableColumn<Patient, String> first_name;

    @FXML
    private TableColumn<Patient, String> midle_name;

    @FXML
    private TableColumn<Patient, String> birth_date;

    @FXML
    private TableColumn<Patient, String> phone;

    @FXML
    private TableColumn<Patient, String> address;

    @FXML
    private TableColumn<Patient, String> num_passport;

    @FXML
    private TableColumn<Patient, String> snils;

    @FXML
    private TableColumn<Patient, String> med_policy;

    @FXML
    private Button AddPatient;

    @FXML
    private Button back;

    @FXML
    private TextField findField;

    @FXML
    private Button findButton;

    @FXML
    void initialize() {
        SetCell();
        AddPatient.setOnAction(actionEvent -> { AddNewPatient();});
        back.setOnAction(actionEvent -> { GoBack();});
        findButton.setOnAction(actionEvent -> { Find();});

        PatientList();

        TablePatient.setRowFactory(patientTableView -> {
            TableRow row = new TableRow();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Patient patient = TablePatient.getSelectionModel().getSelectedItem();
                    id = patient.getPatient_id().toString();
                    ContextPatient();
                }
            });
            return row;
        });
    }

    private void SetCell(){//метод работы с таблицей
        patient_id.setCellValueFactory(new PropertyValueFactory<>("patient_id"));
        last_name.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        first_name.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        midle_name.setCellValueFactory(new PropertyValueFactory<>("midle_name"));
        birth_date.setCellValueFactory(new PropertyValueFactory<>("birth_date"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        num_passport.setCellValueFactory(new PropertyValueFactory<>("num_passport"));
        snils.setCellValueFactory(new PropertyValueFactory<>("snils"));
        med_policy.setCellValueFactory(new PropertyValueFactory<>("med_policy"));
    }

    private void PatientList (){//считывание информации о пациентах из бд
        DBwork dBwork = new DBwork();
        String select_patient = "SELECT * FROM " + Const.PATIENT_TABLE + " ORDER BY " + Const.PATIENT_LASTNAME;
        try {
            Statement statement = dBwork.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(select_patient);

            while (resultSet.next()){
                Patient patient = new Patient();
                patient.setPatient_id(resultSet.getInt("patient_id"));
                patient.setLast_name(resultSet.getString("last_name"));
                patient.setFirst_name(resultSet.getString("first_name"));
                patient.setMidle_name(resultSet.getString("midle_name"));
                patient.setBirth_date(resultSet.getString("birth_date"));
                patient.setPhone(resultSet.getString("phone"));
                patient.setAddress(resultSet.getString("address"));
                patient.setNum_passport(resultSet.getString("num_passport"));
                patient.setSnils(resultSet.getString("snils"));
                patient.setMed_policy(resultSet.getString("med_policy"));
                patients.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        TablePatient.setItems(patients);
    }

    private void GoBack(){//переход на форму авторизации
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

    private void AddNewPatient(){//метод перехода на форму добавления нового пациента
        AddPatient.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/forms/NewPatient.fxml"));
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

    private void ContextPatient(){//метод вызова формы с меню
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Context/ContextPatient.fxml"));
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

    private void Find(){//метод поиска
        find_patients.clear();
        String find_string = findField.getText();
        DBwork dBwork = new DBwork();
        String select_patient = "SELECT * FROM " + Const.PATIENT_TABLE + " WHERE " + Const.PATIENT_LASTNAME + " LIKE '%" + find_string + "%'"+ " ORDER BY " + Const.PATIENT_LASTNAME;
        try {
            Statement statement = dBwork.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(select_patient);
            while (resultSet.next()){
                Patient patient = new Patient();
                patient.setPatient_id(resultSet.getInt("patient_id"));
                patient.setLast_name(resultSet.getString("last_name"));
                patient.setFirst_name(resultSet.getString("first_name"));
                patient.setMidle_name(resultSet.getString("midle_name"));
                patient.setBirth_date(resultSet.getString("birth_date"));
                patient.setPhone(resultSet.getString("phone"));
                patient.setAddress(resultSet.getString("address"));
                patient.setNum_passport(resultSet.getString("num_passport"));
                patient.setSnils(resultSet.getString("snils"));
                patient.setMed_policy(resultSet.getString("med_policy"));
                find_patients.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        TablePatient.setItems(find_patients);
        TablePatient.refresh();
    }
}
