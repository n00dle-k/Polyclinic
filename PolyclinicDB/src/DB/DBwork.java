package DB;

import forms.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class DBwork {
    public static final String DB_URL = "jdbc:postgresql://127.0.0.1:5433/polyclinic";
    static final String USER = "postgres";
    static final String PASS = "261274";
    public static Connection con;

    public Connection getCon() throws ClassNotFoundException, SQLException {
        con = DriverManager.getConnection(DB_URL, USER, PASS);
        return con;
    }

    public void addNewEmployee(Employee employee){
        String insertEmployee = "INSERT INTO " + Const.EMPLOYEE_TABLE + " (" + Const.EMPLOYEE_FIRSTNAME + ", " + Const.EMPLOYEE_LASTNAME + ", " + Const.EMPLOYEE_MIDLENAME + ", " + Const.EMPLOYEE_BIRTHDAY + ", " + Const.EMPLOYEE_PHONE + ", " + Const.EMPLOYEE_ADDRESS + ", " + Const.EMPLOYEE_PASSPORT + ", " + Const.EMPLOYEE_INN + ", " + Const.EMPLOYEE_SNILS + ", " + Const.EMPLOYEE_POLICY + ", " + Const.EMPLOYEE_POST + ", " + Const.EMPLOYEE_LOGIN + ", " + Const.EMPLOYEE_PASSWORD + ")"
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = getCon().prepareStatement(insertEmployee);
            preparedStatement.setString(1, employee.getFirst_name());
            preparedStatement.setString(2, employee.getLast_name());
            preparedStatement.setString(3, employee.getMidle_name());
            preparedStatement.setString(4, employee.getBirth_date());
            preparedStatement.setString(5, employee.getPhone());
            preparedStatement.setString(6, employee.getAddress());
            preparedStatement.setString(7, employee.getNum_passport());
            preparedStatement.setString(8, employee.getInn());
            preparedStatement.setString(9, employee.getSnils());
            preparedStatement.setString(10, employee.getMed_policy());
            preparedStatement.setString(11, employee.getPost_name());
            preparedStatement.setString(12, employee.getLogin());
            preparedStatement.setString(13, employee.getPassword());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
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

            e.printStackTrace();
        } catch (ClassNotFoundException e) {
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
            e.printStackTrace();
        }
    }

    public void addNewPatient(Patient patient){
        String insertPatient = "INSERT INTO " + Const.PATIENT_TABLE + " (" + Const.PATIENT_FIRSTNAME + ", " + Const.PATIENT_LASTNAME + ", " + Const.PATIENT_MIDLENAME + ", " + Const.PATIENT_BIRTHDAY + ", " + Const.PATIENT_PHONE + ", " + Const.PATIENT_ADDRESS + ", " + Const.PATIENT_PASSPORT + ", " + Const.PATIENT_SNILS + ", " + Const.PATIENT_POLICY + ")"
                + "VALUES(?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = getCon().prepareStatement(insertPatient);
            preparedStatement.setString(1, patient.getFirst_name());
            preparedStatement.setString(2, patient.getLast_name());
            preparedStatement.setString(3, patient.getMidle_name());
            preparedStatement.setString(4, patient.getBirth_date());
            preparedStatement.setString(5, patient.getPhone());
            preparedStatement.setString(6, patient.getAddress());
            preparedStatement.setString(7, patient.getNum_passport());
            preparedStatement.setString(8, patient.getSnils());
            preparedStatement.setString(9, patient.getMed_policy());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
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

            e.printStackTrace();
        } catch (ClassNotFoundException e) {
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

            e.printStackTrace();
        }
    }

    public void getEmployee(Employee employee){
        String select = "SELECT * FROM " + Const.EMPLOYEE_TABLE + " WHERE " + Const.EMPLOYEE_LOGIN + "=? AND " + Const.EMPLOYEE_PASSWORD + " =?";
        try {
            PreparedStatement preparedStatement = getCon().prepareStatement(select);
            preparedStatement.setString(1, employee.getLogin());
            preparedStatement.setString(2, employee.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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
    }

    public void DeleteEmployee(){
        String deleteEmployee = "DELETE FROM " + Const.EMPLOYEE_TABLE + " WHERE " + Const.EMPLOYEE_ID + " = " + AllEmployees.id;
        try {
            PreparedStatement preparedStatement = getCon().prepareStatement(deleteEmployee);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Dialog/DeleteError.fxml"));

            try {
                loader.load();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Dialog/DeleteError.fxml"));

            try {
                loader.load();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            e.printStackTrace();
        }
    }

    public void DeletePatient(){
        String deletePatient = "DELETE FROM " + Const.PATIENT_TABLE + " WHERE " + Const.PATIENT_ID + " = " + AllPatient.id;
        try {
            PreparedStatement preparedStatement = getCon().prepareStatement(deletePatient);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Dialog/DeleteError.fxml"));

            try {
                loader.load();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Dialog/DeleteError.fxml"));

            try {
                loader.load();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            e.printStackTrace();
        }
    }

    public void changeEmployee(Employee employee){
        String update = "UPDATE " + Const.EMPLOYEE_TABLE + " SET " + Const.EMPLOYEE_FIRSTNAME + " =?, " + Const.EMPLOYEE_LASTNAME + " =?, " + Const.EMPLOYEE_MIDLENAME + " =?, " + Const.EMPLOYEE_BIRTHDAY + " =?, " + Const.EMPLOYEE_PHONE + " =?, " + Const.EMPLOYEE_ADDRESS + " =?, " + Const.EMPLOYEE_PASSPORT + " =?, " + Const.EMPLOYEE_INN + " =?, " + Const.EMPLOYEE_SNILS + " =?, " + Const.EMPLOYEE_POLICY + " =?, " + Const.EMPLOYEE_POST + " =?, " + Const.EMPLOYEE_LOGIN + " =?, " + Const.EMPLOYEE_PASSWORD + " =? WHERE " + Const.EMPLOYEE_ID + " = " + AllEmployees.id;
        try {
            PreparedStatement preparedStatement = getCon().prepareStatement(update);

            preparedStatement.setString(1, employee.getFirst_name());
            preparedStatement.setString(2, employee.getLast_name());
            preparedStatement.setString(3, employee.getMidle_name());
            preparedStatement.setString(4, employee.getBirth_date());
            preparedStatement.setString(5, employee.getPhone());
            preparedStatement.setString(6, employee.getAddress());
            preparedStatement.setString(7, employee.getNum_passport());
            preparedStatement.setString(8, employee.getInn());
            preparedStatement.setString(9, employee.getSnils());
            preparedStatement.setString(10, employee.getMed_policy());
            preparedStatement.setString(11, employee.getPost_name());
            preparedStatement.setString(12, employee.getLogin());
            preparedStatement.setString(13, employee.getPassword());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Dialog/ChangeError.fxml"));

            try {
                loader.load();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Dialog/ChangeError.fxml"));

            try {
                loader.load();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            e.printStackTrace();
        }

    }

    public void changePatient(Patient patient){
        String update = "UPDATE " + Const.PATIENT_TABLE + " SET " + Const.PATIENT_FIRSTNAME + " =?, " + Const.PATIENT_LASTNAME + " =?, " + Const.PATIENT_MIDLENAME + " =?, " + Const.PATIENT_BIRTHDAY + " =?, " + Const.PATIENT_PHONE + " =?, " + Const.PATIENT_ADDRESS + " =?, " + Const.PATIENT_PASSPORT + " =?, " + Const.PATIENT_SNILS + " =?, " + Const.PATIENT_POLICY + " =? WHERE" + Const.PATIENT_ID + " = " + AllPatient.id;
        try {
            PreparedStatement preparedStatement = getCon().prepareStatement(update);

            preparedStatement.setString(1, patient.getFirst_name());
            preparedStatement.setString(2, patient.getLast_name());
            preparedStatement.setString(3, patient.getMidle_name());
            preparedStatement.setString(4, patient.getBirth_date());
            preparedStatement.setString(5, patient.getPhone());
            preparedStatement.setString(6, patient.getAddress());
            preparedStatement.setString(7, patient.getNum_passport());
            preparedStatement.setString(8, patient.getSnils());
            preparedStatement.setString(9, patient.getMed_policy());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Dialog/ChangeError.fxml"));

            try {
                loader.load();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Dialog/ChangeError.fxml"));

            try {
                loader.load();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            e.printStackTrace();
        }

    }

    public void getEmployeeForRecep(Employee employee){
        String select = "SELECT " + Const.EMPLOYEE_ID + " FROM " + Const.EMPLOYEE_TABLE + " WHERE " + Const.EMPLOYEE_LASTNAME + "=? AND " + Const.EMPLOYEE_FIRSTNAME + "=? AND " + Const.EMPLOYEE_MIDLENAME + "=?";
        try {

            PreparedStatement preparedStatement = getCon().prepareStatement(select);
            preparedStatement.setString(1, employee.getLast_name());
            preparedStatement.setString(2, employee.getFirst_name());
            preparedStatement.setString(3, employee.getMidle_name());
            preparedStatement.executeQuery();

            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()){
                employee.setEmployee_id(resultSet.getInt("employee_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void getPatientForRecep(Patient patient){
        String select = "SELECT " + Const.PATIENT_ID + " FROM " + Const.PATIENT_TABLE + " WHERE " + Const.PATIENT_LASTNAME + "=? AND " + Const.PATIENT_FIRSTNAME + "=? AND " + Const.PATIENT_MIDLENAME + "=?";
        try {
            PreparedStatement preparedStatement = getCon().prepareStatement(select);
            preparedStatement.setString(1, patient.getLast_name());
            preparedStatement.setString(2, patient.getFirst_name());
            preparedStatement.setString(3, patient.getMidle_name());
            preparedStatement.executeQuery();

            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()){
                patient.setPatient_id(resultSet.getInt("patient_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void insertReception(Reception reception, Patient patient, Employee employee){
        String insert = "INSERT INTO reception_log(patient_id, employee_id, recep_date) VALUES(?, ?, ?)";
        try {
            PreparedStatement preparedStatement = getCon().prepareStatement(insert);

            preparedStatement.setInt(1, patient.getPatient_id());
            preparedStatement.setInt(2, employee.getEmployee_id());
            preparedStatement.setString(3, reception.getRecep_date());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void DeleteReceptionLog(){
        String delete = "DELETE FROM " + Const.RECEPTION_LOG_TABLE + " WHERE " + Const.RECEPTION_LOG_ID + " = " + AllReceptionLog.id;
        try {
            PreparedStatement preparedStatement = getCon().prepareStatement(delete);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void ChangeReception(Reception reception){
        String update = "UPDATE " + Const.RECEPTION_LOG_TABLE + " SET " + Const.RECEPTION_DATE + " =? WHERE " + Const.RECEPTION_LOG_ID + " = " + AllReceptionLog.id;
        try {
            PreparedStatement preparedStatement = getCon().prepareStatement(update);

            preparedStatement.setString(1, reception.getRecep_date());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void PatientReception(Reception reception){
        String update = "UPDATE " + Const.RECEPTION_LOG_TABLE + " SET " + Const.RECEPTION_DIAGNOSIS + " =?, " + Const.RECEPTION_SYMPTOMS + " =?, " + Const.RECEPTION_TREATMENT + " =? WHERE " + Const.RECEPTION_LOG_ID + " = " + AllPatientReception.id;
        System.out.println(update);
        try {
            PreparedStatement preparedStatement = getCon().prepareStatement(update);
            preparedStatement.setString(1, reception.getDiagnosis_id());
            preparedStatement.setString(2, reception.getSymptoms());
            preparedStatement.setString(3, reception.getTreatment());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
