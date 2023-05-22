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

public class DoctorName {
    public static String lname;
    public static String fname;
    public static String mname;
    public ObservableList<Employee> doctors = FXCollections.observableArrayList();
    public ObservableList<Employee> find_doctors = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Employee> TableEmployees;

    @FXML
    private TableColumn<Employee, String> last_name;

    @FXML
    private TableColumn<Employee, String> first_name;

    @FXML
    private TableColumn<Employee, String> midle_name;

    @FXML
    private Button back;

    @FXML
    private TextField findField;

    @FXML
    private Button findButton;

    @FXML
    void initialize() {
        SetCell();
        DoctorList();
        findButton.setOnAction(actionEvent -> Find());
        back.setOnAction(actionEvent -> GoBack());
        TableEmployees.setRowFactory(employeeTableView -> {
            TableRow row = new TableRow();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Employee employee = TableEmployees.getSelectionModel().getSelectedItem();
                    lname = employee.getLast_name();
                    fname = employee.getFirst_name();
                    mname = employee.getMidle_name();
                    NewRecep();
                }
            });
            return row;
        });
    }

    private void DoctorList (){
        DBwork dBwork = new DBwork();
        String select_employee = "SELECT " + Const.EMPLOYEE_LASTNAME + ", " + Const.EMPLOYEE_FIRSTNAME + ", " + Const.EMPLOYEE_MIDLENAME + " FROM " + Const.EMPLOYEE_TABLE + " WHERE " + Const.EMPLOYEE_POST + " = '" + AllDoctors.post_name + "'";
        try {
            Statement statement = dBwork.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(select_employee);

            while (resultSet.next()){
                Employee employee = new Employee();
                employee.setLast_name(resultSet.getString("employee_last_name"));
                employee.setFirst_name(resultSet.getString("employee_first_name"));
                employee.setMidle_name(resultSet.getString("employee_midle_name"));

                doctors.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableEmployees.setItems(doctors);
    }

    private void SetCell(){//метод работы с таблицей
        first_name.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        last_name.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        midle_name.setCellValueFactory(new PropertyValueFactory<>("midle_name"));
    }

    private void Find(){//метод для поиска
        find_doctors.clear();
        String find_employee = findField.getText();
        DBwork dBwork = new DBwork();
        String select_employee = "SELECT " + Const.EMPLOYEE_LASTNAME + ", " + Const.EMPLOYEE_FIRSTNAME + ", " + Const.EMPLOYEE_MIDLENAME + " FROM " + Const.EMPLOYEE_TABLE + " WHERE " + Const.EMPLOYEE_POST + " = " + AllDoctors.post_name + " AND " + Const.EMPLOYEE_LASTNAME + " LIKE '%" + find_employee + "%'";
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

                find_doctors.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableEmployees.setItems(find_doctors);
    }

    private void GoBack(){
        back.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/forms/AllDoctors.fxml"));
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

    private void NewRecep(){
        back.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/forms/NewReceptionLog.fxml"));
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
