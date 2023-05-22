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

public class AllDoctors {
    public static String post_name;
    public ObservableList<Employee> doctors = FXCollections.observableArrayList();
    public ObservableList<Employee> find_doctors = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Employee> TableEmployees;

    @FXML
    private TableColumn<Employee, String> post;

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
                    post_name = employee.getPost_name();
                    DoctorName();
                }
            });
            return row;
        });
    }

    private void SetCell(){
        post.setCellValueFactory(new PropertyValueFactory<>("post_name"));
    }

    private void DoctorList (){
        DBwork dBwork = new DBwork();
        String select_employee = "SELECT DISTINCT " + Const.EMPLOYEE_POST + " FROM " + Const.EMPLOYEE_TABLE + " WHERE " + Const.EMPLOYEE_POST + " != 'Сотрудник регистратуры' AND " + Const.EMPLOYEE_POST + " != 'Сотрудник отдела кадров'";
        try {
            Statement statement = dBwork.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(select_employee);

            while (resultSet.next()){
                Employee employee = new Employee();
                employee.setPost_name(resultSet.getString("post_name"));

                doctors.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableEmployees.setItems(doctors);
    }

    private void Find(){//метод для поиска
        find_doctors.clear();
        String find_doctor = findField.getText();
        DBwork dBwork = new DBwork();
        String select_employee = "SELECT DISTINCT " + Const.EMPLOYEE_POST + " FROM " + Const.EMPLOYEE_TABLE + " WHERE " + Const.EMPLOYEE_POST + " LIKE '%" + find_doctor + "%'";
        try {
            Statement statement = dBwork.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(select_employee);

            while (resultSet.next()){
                Employee employee = new Employee();
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
    }

    private void DoctorName(){
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
