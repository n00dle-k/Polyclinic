package forms;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import DB.Const;
import DB.DBwork;
import DB.Reception;
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
//форма с информацией о приемах пациента
public class AllPatientReception {
    public ObservableList<Reception> receptions = FXCollections.observableArrayList();
    public static String id;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Reception> TableReception;

    @FXML
    private TableColumn<Reception, Integer> recep_log_id;

    @FXML
    private TableColumn<Reception, String> recep_date;

    @FXML
    private TableColumn<Reception, String> diagnosys;

    @FXML
    private TableColumn<Reception, String> treatment;

    @FXML
    private Button back;

    @FXML
    void initialize() {
        SetCell();
        Select();
        TableReception.setRowFactory(receptionTableView -> {
            TableRow row = new TableRow();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Reception reception = TableReception.getSelectionModel().getSelectedItem();
                    id = reception.getLog_id().toString();
                    AddReception();
                }
            });
            return row;
        });
        back.setOnAction(actionEvent -> GoBack());
    }

    private void SetCell(){//работа с таблицей
        recep_log_id.setCellValueFactory(new PropertyValueFactory<>("log_id"));
        recep_date.setCellValueFactory(new PropertyValueFactory<>("recep_date"));
        diagnosys.setCellValueFactory(new PropertyValueFactory<>("diagnosis_id"));
        treatment.setCellValueFactory(new PropertyValueFactory<>("treatment"));
    }

    private void Select(){//считыавние из бд
        DBwork dBwork = new DBwork();
        String select = "SELECT " + Const.RECEPTION_LOG_TABLE + "." + Const.RECEPTION_LOG_ID + ", " + Const.RECEPTION_LOG_TABLE + "." + Const.RECEPTION_DATE + ", " + Const.RECEPTION_LOG_TABLE + "." + Const.RECEPTION_DIAGNOSIS + ", " + Const.RECEPTION_LOG_TABLE + "." + Const.RECEPTION_TREATMENT + " FROM " + Const.RECEPTION_LOG_TABLE +
                " WHERE " + Const.RECEPTION_PATIENT + " = " + DoctorReception.id;
        try {
            Statement statement = dBwork.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(select);

            while (resultSet.next()){
                Reception reception = new Reception();
                reception.setLog_id(resultSet.getInt("log_id"));
                reception.setRecep_date(resultSet.getString("recep_date"));
                reception.setDiagnosis_id(resultSet.getString("diagnosis_id"));
                reception.setTreatment(resultSet.getString("treatment"));

                receptions.add(reception);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        TableReception.setItems(receptions);
    }

    private void AddReception(){//форма с изменением данных о приеме
        back.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/forms/PatientReception.fxml"));
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

    private void GoBack(){//переход на предыдущую страницу
        back.getScene().getWindow().hide();
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
}
