package com.example.exam1;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

public class Controller {

    @FXML
    private TextField patientNameField;
    @FXML
    private TextField diagnosisField;
    @FXML
    private DatePicker dateField;
    @FXML
    private TableView<Diagnosis> tableView;
    @FXML
    private TableColumn<Diagnosis, String> patientNameColumn;
    @FXML
    private TableColumn<Diagnosis, String> diagnosisColumn;
    @FXML
    private TableColumn<Diagnosis, String> dateColumn;

    private ObservableList<Diagnosis> data;

    private DatabaseHandler databaseHandler;

    @FXML
    public void initialize() {
        databaseHandler = new DatabaseHandler();

        patientNameColumn.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        diagnosisColumn.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        data = FXCollections.observableArrayList();
        tableView.setItems(data);
    }

    @FXML
    public void addDiagnosis() {
        String patientName = patientNameField.getText();
        String diagnosis = diagnosisField.getText();
        String date = dateField.getValue().toString();

        String query = "INSERT INTO Diagnoses (patientName, diagnosis, date) VALUES (?, ?, ?)";

        try (Connection connection = databaseHandler.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, patientName);
            preparedStatement.setString(2, diagnosis);
            preparedStatement.setString(3, date);

            preparedStatement.executeUpdate();

            showAlert(Alert.AlertType.INFORMATION, "Success", "Diagnosis added successfully!");

            clearFields();

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to add diagnosis.");
        }
    }

    @FXML
    public void searchDiagnoses() {
        data.clear(); // Clear existing data before fetching new data

        String query = "SELECT * FROM Diagnoses";

        try (Connection connection = databaseHandler.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String patientName = resultSet.getString("patientName");
                String diagnosis = resultSet.getString("diagnosis");
                String date = resultSet.getString("date");

                Diagnosis diagnosisEntry = new Diagnosis(patientName, diagnosis, date);
                data.add(diagnosisEntry);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to retrieve diagnoses.");
        }
    }


    private void clearFields() {
        patientNameField.clear();
        diagnosisField.clear();
        dateField.setValue(null);
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
