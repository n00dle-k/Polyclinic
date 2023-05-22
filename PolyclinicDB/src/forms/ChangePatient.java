package forms;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import DB.Const;
import DB.DBwork;
import DB.Employee;
import DB.Patient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
//класс с формой изменения данных пациента
public class ChangePatient {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField midleNameField;

    @FXML
    private TextField birthDateField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField addresField;

    @FXML
    private TextField passportField;

    @FXML
    private TextField snilsField;

    @FXML
    private TextField policyField;

    @FXML
    private Button back;

    @FXML
    private Button addButton;

    @FXML
    void initialize() {
        selectForChange();
        addButton.setOnAction(actionEvent -> {
            Update();
        });
        back.setOnAction(actionEvent -> {Goback();});
    }

    private void selectForChange(){//считывание информации для изменения данных
        DBwork dBwork = new DBwork();
        String selectForChange = "SELECT " + Const.PATIENT_FIRSTNAME + ", " + Const.PATIENT_LASTNAME + ", " + Const.PATIENT_MIDLENAME + ", " + Const.PATIENT_BIRTHDAY + ", " + Const.PATIENT_PHONE + ", " + Const.PATIENT_ADDRESS + ", " + Const.PATIENT_PASSPORT + ", " + Const.PATIENT_SNILS + " FROM " + Const.PATIENT_TABLE + " WHERE " + Const.PATIENT_ID + " = " + AllPatient.id;
        try {
            Statement statement = dBwork.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(selectForChange);
            while (resultSet.next()){
                firstNameField.setText(resultSet.getString("first_name"));
                lastNameField.setText(resultSet.getString("last_name"));
                midleNameField.setText(resultSet.getString("midle_name"));
                birthDateField.setText(resultSet.getString("birth_date"));
                phoneField.setText(resultSet.getString("phone"));
                addresField.setText(resultSet.getString("address"));
                passportField.setText(resultSet.getString("num_passport"));
                snilsField.setText(resultSet.getString("snils"));
                policyField.setText(resultSet.getString("med_policy"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void Update(){//метод изменения данных в бд
        DBwork dBwork = new DBwork();

        String fname = firstNameField.getText();
        String lname = lastNameField.getText();
        String mname = midleNameField.getText();
        String bday = birthDateField.getText();
        String phone = phoneField.getText();
        String adres = addresField.getText();
        String pass = passportField.getText();
        String snils = snilsField.getText();
        String policy = policyField.getText();

        Patient patient = new Patient(fname, lname, mname, bday, phone, adres, pass, snils, policy);

        dBwork.changePatient(patient);
    }

    private void Goback(){//возврат на предыдущую форму
        back.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/forms/AllPatient.fxml"));
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
