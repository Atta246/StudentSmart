package com.example.studentsmart.Controllers;

import com.example.studentsmart.Classes.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;

public class SchedueleController implements Initializable {

    @FXML
    private TableView<ScheduleRow> table;
    @FXML
    private TableColumn<ScheduleRow, String> dayCol;
    @FXML
    private TableColumn<ScheduleRow, String> col1;
    @FXML
    private TableColumn<ScheduleRow, String> col2;
    @FXML
    private TableColumn<ScheduleRow, String> col3;
    @FXML
    private TableColumn<ScheduleRow, String> breakCol;
    @FXML
    private TableColumn<ScheduleRow, String> col4;
    @FXML
    private TableColumn<ScheduleRow, String> col5;
    @FXML
    private TableColumn<ScheduleRow, String> col6;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupTableColumns();
        loadSchedule(DashboardController.student.getClassName());
    }

    private void setupTableColumns() {
        dayCol.setCellValueFactory(new PropertyValueFactory<>("day"));
        col1.setCellValueFactory(new PropertyValueFactory<>("period1"));
        col2.setCellValueFactory(new PropertyValueFactory<>("period2"));
        col3.setCellValueFactory(new PropertyValueFactory<>("period3"));
        breakCol.setCellValueFactory(new PropertyValueFactory<>("breakPeriod"));
        col4.setCellValueFactory(new PropertyValueFactory<>("period4"));
        col5.setCellValueFactory(new PropertyValueFactory<>("period5"));
        col6.setCellValueFactory(new PropertyValueFactory<>("period6"));
    }

    private void loadSchedule(String className) {
        try (DatabaseConnection db = new DatabaseConnection()) {
            Map<String, String[]> scheduleData = db.getClassSchedule(className);
            ObservableList<ScheduleRow> scheduleRows = FXCollections.observableArrayList();

            scheduleData.forEach((day, periods) -> {
                scheduleRows.add(new ScheduleRow(
                        day,
                        periods[0], // Period 1
                        periods[1], // Period 2
                        periods[2], // Period 3
                        periods[3], // Break
                        periods[4], // Period 4
                        periods[5], // Period 5
                        periods[6]  // Period 6
                ));
            });

            table.setItems(scheduleRows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void handleHome(ActionEvent actionEvent) {
        try {
            // Load the FXML file for the home page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/studentsmart/dashboard.fxml"));
            Parent root = loader.load();

            // Get the current stage and set the new scene
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();}

    }

    public void handlecourses(ActionEvent actionEvent) {
        try {
            // Load the FXML file for the courses page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/studentsmart/courses.fxml"));
            Parent root = loader.load();

            // Get the current stage and set the new scene
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }}

    public void handlegrades(ActionEvent actionEvent) {
        try {
            // Load the FXML file for the grades page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/studentsmart/results.fxml"));
            Parent root = loader.load();

            // Get the current stage and set the new scene
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class ScheduleRow {
        private final String day;
        private final String period1;
        private final String period2;
        private final String period3;
        private final String breakPeriod;
        private final String period4;
        private final String period5;
        private final String period6;

        public ScheduleRow(String day, String period1, String period2, String period3,
                           String breakPeriod, String period4, String period5, String period6) {
            this.day = day;
            this.period1 = period1;
            this.period2 = period2;
            this.period3 = period3;
            this.breakPeriod = breakPeriod;
            this.period4 = period4;
            this.period5 = period5;
            this.period6 = period6;
        }

        // Getters
        public String getDay() { return day; }
        public String getPeriod1() { return period1; }
        public String getPeriod2() { return period2; }
        public String getPeriod3() { return period3; }
        public String getBreakPeriod() { return breakPeriod; }
        public String getPeriod4() { return period4; }
        public String getPeriod5() { return period5; }
        public String getPeriod6() { return period6; }
    }

}
