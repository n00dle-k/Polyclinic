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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
//класс отвечающий за изменения данных сотрудника
public class ChangeEmployee {

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
    private Button changeButton;

    @FXML
    private TextField postField;

    @FXML
    void initialize() {
        selectForChange();
        changeButton.setOnAction(actionEvent -> {Update();
        });
        back.setOnAction(actionEvent -> GoBack());
    }

    private void selectForChange(){//считывание информации о сотруднике из бд
        DBwork dBwork = new DBwork();
        String selectForChange = "SELECT " + Const.EMPLOYEE_FIRSTNAME + ", " + Const.EMPLOYEE_LASTNAME + ", " + Const.EMPLOYEE_MIDLENAME + ", " + Const.EMPLOYEE_BIRTHDAY + ", " + Const.EMPLOYEE_PHONE + ", " + Const.EMPLOYEE_ADDRESS + ", " + Const.EMPLOYEE_PASSPORT + ", " + Const.EMPLOYEE_INN + ", " + Const.EMPLOYEE_SNILS + ", " + Const.EMPLOYEE_POLICY + ", " + Const.EMPLOYEE_POST + ", " + Const.EMPLOYEE_LOGIN + ", " + Const.EMPLOYEE_PASSWORD +" FROM " + Const.EMPLOYEE_TABLE + " WHERE " + Const.EMPLOYEE_ID + " = " + AllEmployees.id;
        try {
            Statement statement = dBwork.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(selectForChange);
            while (resultSet.next()){
                firstNameField.setText(resultSet.getString("employee_first_name"));
                lastNameField.setText(resultSet.getString("employee_last_name"));
                midleNameField.setText(resultSet.getString("employee_midle_name"));
                birthDateField.setText(resultSet.getString("birth_date"));
                phoneField.setText(resultSet.getString("phone"));
                addresField.setText(resultSet.getString("address"));
                passportField.setText(resultSet.getString("num_passport"));
                innField.setText(resultSet.getString("inn"));
                snilsField.setText(resultSet.getString("snils"));
                policyField.setText(resultSet.getString("med_policy"));
                postField.setText(resultSet.getString("post_name"));
                loginField.setText(resultSet.getString("login"));
                passwordField.setText(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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

    private void Update(){//метод изменения данных в бд
        DBwork dBwork = new DBwork();

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

        dBwork.changeEmployee(employee);
    }
}
