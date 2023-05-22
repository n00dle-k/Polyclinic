package forms;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import DB.DBwork;
import DB.Patient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
//класс с формой добавления нового пациента
public class NewPatientForm {

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
        addButton.setOnAction(actionEvent -> {
            addPatient();
        });
        back.setOnAction(actionEvent -> {Goback();});
    }

    private void addPatient(){//метод добавления пациента в бд
        DBwork dBwork = new DBwork();

        if (!firstNameField.getText().equals("") && !lastNameField.getText().equals("") && !midleNameField.getText().equals("") && !birthDateField.getText().equals("") && !phoneField.getText().equals("") && !addresField.getText().equals("") && !passportField.getText().equals("") && !snilsField.getText().equals("") && !policyField.getText().equals("")){
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
            dBwork.addNewPatient(patient);
        }
        else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Dialog/AddPatientError.fxml"));
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

    private void Goback(){//метод перехода к предыдущей форме
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
