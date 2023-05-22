package forms;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
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
//класс для добавления нового приема
public class NewReceptionLog {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addButton;

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
        Select_for_change_patient();
        Select_for_change_doctor();
        addButton.setOnAction(actionEvent -> {
            addReception();
            addButton.getScene().getWindow().hide();
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
    }

    private void addReception() {//добавление в бд нового приема
        DBwork dBwork = new DBwork();

        if (!patient_last_name.getText().equals("") && !patient_first_name.getText().equals("") && !patient_midle_name.getText().equals("") && !employee_last_name.getText().equals("") && !employee_first_name.getText().equals("") && !employee_midle_name.getText().equals("") && !date.getText().equals("")){
            String patient_lname = patient_last_name.getText();
            String patient_fname = patient_first_name.getText();
            String patient_mname = patient_midle_name.getText();
            String employee_lname = employee_last_name.getText();
            String employee_fname = employee_first_name.getText();
            String employee_mname = employee_midle_name.getText();
            String recep_date = date.getText();

            Reception reception = new Reception(recep_date);
            Employee employee = new Employee(employee_lname, employee_fname, employee_mname);
            Patient patient = new Patient(patient_lname, patient_fname, patient_mname);

            dBwork.getEmployeeForRecep(employee);
            dBwork.getPatientForRecep(patient);

            dBwork.insertReception(reception, patient, employee);
        }
        else{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Dialog/AddReceptionLogError.fxml"));

            try {
                loader.load();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    private void Select_for_change_patient(){//считывание из бд
        DBwork dBwork = new DBwork();
        String select = "SELECT " + Const.PATIENT_LASTNAME + ", " + Const.PATIENT_FIRSTNAME + ", "  + Const.PATIENT_MIDLENAME + " FROM " + Const.PATIENT_TABLE +
                " WHERE " + Const.PATIENT_ID + " = " + AllPatient.id;
        try {
            Statement statement = dBwork.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(select);

            while (resultSet.next()){
                patient_last_name.setText(resultSet.getString("last_name"));
                patient_first_name.setText(resultSet.getString("first_name"));
                patient_midle_name.setText(resultSet.getString("midle_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void Select_for_change_doctor(){//считывание из бд
        DBwork dBwork = new DBwork();
        String select = "SELECT " + Const.EMPLOYEE_LASTNAME + ", " + Const.EMPLOYEE_FIRSTNAME + ", "  + Const.EMPLOYEE_MIDLENAME + " FROM " + Const.EMPLOYEE_TABLE +
                " WHERE " + Const.EMPLOYEE_LASTNAME + " = '" + DoctorName.lname + "' AND " + Const.EMPLOYEE_FIRSTNAME + " = '" + DoctorName.fname + "' AND " + Const.EMPLOYEE_MIDLENAME + " = '" + DoctorName.mname + "'";
        try {
            Statement statement = dBwork.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(select);

            while (resultSet.next()){
                employee_last_name.setText(resultSet.getString("employee_last_name"));
                employee_first_name.setText(resultSet.getString("employee_first_name"));
                employee_midle_name.setText(resultSet.getString("employee_midle_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void Goback(){//метод перехода к предыдущей форме
        back.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/forms/DoctorName.fxml"));
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
