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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
//класс, отвечающий за работу страницы авторизации
public class StartForm {
    public static String employeeid;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private Button SignInButton;

    @FXML
    void initialize() {
        SignInButton.setOnAction(actionEvent -> {
            String loginText = login_field.getText().trim();
            String passwordText = password_field.getText().trim();
            try {
                SignInUser(loginText,passwordText);
            }catch (NullPointerException e){
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/Dialog/SignInError.fxml"));

                try {
                    loader.load();
                } catch (IOException e1) {
                    e.printStackTrace();
                }

                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            }
        });
    }

    private void loginAdmin() {//метод, вызывающий форму сотрудника отдела кадров
        SignInButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/forms/Admin.fxml"));

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
    private void loginReception() {//метод, вызывающий формму сотрудника регистратуры
        SignInButton.getScene().getWindow().hide();
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

    private void loginDoctor() {//метод, вызывающий форму врача
        SignInButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/forms/DoctorReception.fxml"));

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

    private void SignInUser (String loginText, String passwordText){//метод, передающий логин и пароль и определяющий роль пользователя
        DBwork dBwork = new DBwork();
        Employee employee = new Employee();
        employee.setLogin(loginText);
        employee.setPassword(passwordText);
        dBwork.getEmployee(employee);
        employeeid = employee.getEmployee_id().toString();
        if(employee.getPost_name().equals("Заместитель главного врача")){
            loginAdmin();
        }
        else if (employee.getPost_name().equals("Сотрудник регистратуры")){
            loginReception();
        }
        else {
            loginDoctor();
        }
    }
}

