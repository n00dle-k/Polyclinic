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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
//класс, с таблицей всех сотрудников
public class AllEmployees {
    public static String id;
    public ObservableList<Employee> employees = FXCollections.observableArrayList();
    public ObservableList<Employee> find_employees = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Employee> TableEmployees;

    @FXML
    private TableColumn<Employee, Integer> employee_id;

    @FXML
    private TableColumn<Employee, String> first_name;

    @FXML
    private TableColumn<Employee, String> last_name;

    @FXML
    private TableColumn<Employee, String> midle_name;

    @FXML
    private TableColumn<Employee, String> birth_date;

    @FXML
    private TableColumn<Employee, String> phone;

    @FXML
    private TableColumn<Employee, String> address;

    @FXML
    private TableColumn<Employee, String> num_passport;

    @FXML
    private TableColumn<Employee, String> snils;

    @FXML
    private TableColumn<Employee, String> inn;

    @FXML
    private TableColumn<Employee, String> med_policy;

    @FXML
    private TableColumn<Employee, String> post;

    @FXML
    private Button addEmployee;

    @FXML
    private Button back;

    @FXML
    private TextField findField;

    @FXML
    private Button findButton;

    @FXML
    void initialize() {
        SetCell();

        addEmployee.setOnAction(actionEvent -> AddNewEmployee());

        back.setOnAction(actionEvent -> GoBack());

        findButton.setOnAction(actionEvent -> Find());

        EmployeeList();

        TableEmployees.setRowFactory(employeeTableView -> {
            TableRow row = new TableRow();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Employee employee = TableEmployees.getSelectionModel().getSelectedItem();
                    id = employee.getEmployee_id().toString();
                    ContextEmployee();
                }
            });
            return row;
        });
    }

    private void AddNewEmployee(){//метод, вызывающий форму добавления сотрудника
            addEmployee.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/forms/NewEmployeeForm.fxml"));
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

    private void GoBack(){//метод выхода из системы и вызова формы авторизации
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

    private void SetCell(){//метод работы с таблицей
        employee_id.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
        first_name.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        last_name.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        midle_name.setCellValueFactory(new PropertyValueFactory<>("midle_name"));
        birth_date.setCellValueFactory(new PropertyValueFactory<>("birth_date"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        num_passport.setCellValueFactory(new PropertyValueFactory<>("num_passport"));
        inn.setCellValueFactory(new PropertyValueFactory<>("inn"));
        snils.setCellValueFactory(new PropertyValueFactory<>("snils"));
        med_policy.setCellValueFactory(new PropertyValueFactory<>("med_policy"));
        post.setCellValueFactory(new PropertyValueFactory<>("post_name"));
    }

    private void EmployeeList (){//метод выборки сотрудников из бд
        DBwork dBwork = new DBwork();
        String select_employee = "SELECT * FROM " + Const.EMPLOYEE_TABLE;
        try {
            Statement statement = dBwork.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(select_employee);

            while (resultSet.next()){
                Employee employee = new Employee();
                employee.setEmployee_id(resultSet.getInt("employee_id"));
                employee.setFirst_name(resultSet.getString("employee_first_name"));
                employee.setLast_name(resultSet.getString("employee_last_name"));
                employee.setMidle_name(resultSet.getString("employee_midle_name"));
                employee.setBirth_date(resultSet.getString("birth_date"));
                employee.setPhone(resultSet.getString("phone"));
                employee.setAddress(resultSet.getString("address"));
                employee.setNum_passport(resultSet.getString("num_passport"));
                employee.setInn(resultSet.getString("inn"));
                employee.setSnils(resultSet.getString("snils"));
                employee.setMed_policy(resultSet.getString("med_policy"));
                employee.setPost_name(resultSet.getString("post_name"));

                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableEmployees.setItems(employees);
    }

    private void ContextEmployee(){//метод вызова меню для изменения данных сотрудника
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Context/ContextEmployee.fxml"));
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

    private void Find(){//метод для поиска
        find_employees.clear();
        String find_employee = findField.getText();
        DBwork dBwork = new DBwork();
        String select_employee = "SELECT * FROM " + Const.EMPLOYEE_TABLE + " WHERE " + Const.EMPLOYEE_LASTNAME + " LIKE '%" + find_employee + "%'" + " ORDER BY " + Const.EMPLOYEE_LASTNAME;
        try {
            Statement statement = dBwork.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(select_employee);

            while (resultSet.next()){
                Employee employee = new Employee();
                employee.setEmployee_id(resultSet.getInt("employee_id"));
                employee.setFirst_name(resultSet.getString("employee_first_name"));
                employee.setLast_name(resultSet.getString("employee_last_name"));
                employee.setMidle_name(resultSet.getString("employee_midle_name"));
                employee.setBirth_date(resultSet.getString("birth_date"));
                employee.setPhone(resultSet.getString("phone"));
                employee.setAddress(resultSet.getString("address"));
                employee.setNum_passport(resultSet.getString("num_passport"));
                employee.setInn(resultSet.getString("inn"));
                employee.setSnils(resultSet.getString("snils"));
                employee.setMed_policy(resultSet.getString("med_policy"));
                employee.setPost_name(resultSet.getString("post_name"));

                find_employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableEmployees.setItems(find_employees);
    }
}
