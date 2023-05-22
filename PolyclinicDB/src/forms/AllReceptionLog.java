package forms;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import DB.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
//класс с таблицей журнала приемов пациента
public class AllReceptionLog {
    public static String id;
    public ObservableList<Reception> receptions = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML

    private TableView<Reception> TableReception;

    @FXML
    private TableColumn<Reception, Integer> log_id;

    @FXML
    private TableColumn<Reception, String> patient_last_name;

    @FXML
    private TableColumn<Reception, String> patient_first_name;

    @FXML
    private TableColumn<Reception, String> patient_midle_name;

    @FXML
    private TableColumn<Reception, String> employee_last_name;

    @FXML
    private TableColumn<Reception, String> employee_first_name;

    @FXML
    private TableColumn<Reception, String> employee_midle_name;

    @FXML
    private TableColumn<Reception, String> date;

    @FXML
    private Button addReceptionLog;

    @FXML
    private Button back;

    @FXML
    void initialize() {
        SetCell();
        Select_log();

        addReceptionLog.setOnAction(actionEvent -> {
            addReception();
        });

        back.setOnAction(actionEvent -> {
            Goback();
        });

        TableReception.setRowFactory(receptionTableView -> {
            TableRow row = new TableRow();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Reception reception = TableReception.getSelectionModel().getSelectedItem();
                    id = reception.getLog_id().toString();
                    System.out.println(id);
                    ContextReceptionLog();
                }
            });
            return row;
        });

    }

    private void Select_log(){//считывание информации из бд
        DBwork dBwork = new DBwork();
        String select = "SELECT " + Const.RECEPTION_LOG_ID + ", " + Const.PATIENT_TABLE + "." + Const.PATIENT_LASTNAME + ", " + Const.PATIENT_TABLE + "." + Const.PATIENT_FIRSTNAME + ", "  + Const.PATIENT_TABLE + "." + Const.PATIENT_MIDLENAME + ", " + Const.EMPLOYEE_TABLE+ "." + Const.EMPLOYEE_LASTNAME + ", " + Const.EMPLOYEE_TABLE+ "." + Const.EMPLOYEE_FIRSTNAME + ", " + Const.EMPLOYEE_TABLE+ "." + Const.EMPLOYEE_MIDLENAME + ", " + Const.RECEPTION_DATE + " FROM " + Const.RECEPTION_LOG_TABLE
                + " INNER JOIN " + Const.PATIENT_TABLE + " ON " + Const.RECEPTION_LOG_TABLE + "." + Const.RECEPTION_PATIENT + " = " + Const.PATIENT_TABLE + "." + Const.PATIENT_ID
                + " INNER JOIN " + Const.EMPLOYEE_TABLE + " ON " + Const.RECEPTION_LOG_TABLE + "." + Const.RECEPTION_EMPLOYEE + " = " + Const.EMPLOYEE_TABLE + "." + Const.EMPLOYEE_ID
                + " WHERE " + Const.PATIENT_TABLE + "." + Const.PATIENT_ID + " = " + AllPatient.id;

        try {
            Statement statement = dBwork.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(select);

            while (resultSet.next()){
                Reception reception = new Reception();
                reception.setLog_id(resultSet.getInt("log_id"));
                reception.setPatient_lname(resultSet.getString("last_name"));
                reception.setPatient_fname(resultSet.getString("first_name"));
                reception.setPatient_mname(resultSet.getString("midle_name"));
                reception.setEmployee_lname(resultSet.getString("employee_last_name"));
                reception.setEmployee_fname(resultSet.getString("employee_first_name"));
                reception.setEmployee_mname(resultSet.getString("employee_midle_name"));
                reception.setRecep_date(resultSet.getString("recep_date"));

                receptions.add(reception);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableReception.setItems(receptions);
    }

    private void SetCell(){//работа с таблицей
        log_id.setCellValueFactory(new PropertyValueFactory<>("log_id"));
        patient_first_name.setCellValueFactory(new PropertyValueFactory<>("patient_fname"));
        patient_last_name.setCellValueFactory(new PropertyValueFactory<>("patient_lname"));
        patient_midle_name.setCellValueFactory(new PropertyValueFactory<>("patient_mname"));
        employee_first_name.setCellValueFactory(new PropertyValueFactory<>("employee_fname"));
        employee_last_name.setCellValueFactory(new PropertyValueFactory<>("employee_lname"));
        employee_midle_name.setCellValueFactory(new PropertyValueFactory<>("employee_mname"));
        date.setCellValueFactory(new PropertyValueFactory<>("recep_date"));
    }

    private void addReception(){//переход на форму добавления нового приема
        addReceptionLog.getScene().getWindow().hide();
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

    private void Goback(){//переход на предыдущую форму
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

    private void ContextReceptionLog(){//форма с меню
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Context/ContextReception.fxml"));
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
