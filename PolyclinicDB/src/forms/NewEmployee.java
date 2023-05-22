package forms;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import DB.DBwork;
import DB.Employee;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
//класс с формой для добавления нового сотрудника
public class NewEmployee {

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
    private TextField innField;

    @FXML
    private TextField snilsField;

    @FXML
    private TextField policyField;

    @FXML
    private TextField loginField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button back;

    @FXML
    private TextField postField;

    @FXML
    private Button addButton;


    @FXML
    void initialize() {

        back.setOnAction(actionEvent -> { GoBack();});

        addButton.setOnAction(actionEvent -> {
            addEmployee();
        });
    }

    private void GoBack(){//метод возврата на форму со всеми сотрудниками
        back.getScene().getWindow().hide();
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
        stage.show();
    }

    private void addEmployee() {//метод добавления нового сотрудника
        DBwork dBwork = new DBwork();

        if (!firstNameField.getText().equals("") && !lastNameField.getText().equals("") && !birthDateField.getText().equals("") && !phoneField.getText().equals("") && !addresField.getText().equals("") && !snilsField.getText().equals("") && !passportField.getText().equals("") && !innField.getText().equals("") && !policyField.getText().equals("") && !postField.getText().equals("")){
            String fname = firstNameField.getText();
            String lname = lastNameField.getText();
            String mname = midleNameField.getText();
            String bday = birthDateField.getText();
            String phone = phoneField.getText();
            String adres = addresField.getText();
            String pass = passportField.getText();
            String inn = innField.getText();
            String snils = snilsField.getText();
            String policy = policyField.getText();
            String post = postField.getText();
            String login = loginField.getText();
            String password = passwordField.getText();

            Employee employee = new Employee(fname, lname, mname, bday, phone, adres, pass, inn, snils, policy, post, login, password);

            dBwork.addNewEmployee(employee);
        }
        else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Dialog/AddEmployeeError.fxml"));

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
}


