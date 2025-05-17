package com.example.studentsmart.Controllers;

import com.example.studentsmart.Classes.DatabaseConnection;
import com.example.studentsmart.Classes.Grades;
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
import java.util.ResourceBundle;

public class ResultsController implements Initializable {
    @FXML private TableView<GradeRow> table;
    @FXML private TableColumn<GradeRow, String> subjectColumn;
    @FXML private TableColumn<GradeRow, String> grade1Column;
    @FXML private TableColumn<GradeRow, String> grade2Column;
    @FXML private TableColumn<GradeRow, String> grade3Column;
    @FXML private TableColumn<GradeRow, String> finalGradeColumn;

    private Grades grades;

    public static class GradeRow {
        private final String subject;
        private final String grade1;
        private final String grade2;
        private final String grade3;
        private final String finalGrade;

        public GradeRow(String subject, String grade1, String grade2, String grade3, String finalGrade) {
            this.subject = subject;
            this.grade1 = grade1;
            this.grade2 = grade2;
            this.grade3 = grade3;
            this.finalGrade = finalGrade;
        }

        public String getSubject() { return subject; }
        public String getGrade1() { return grade1; }
        public String getGrade2() { return grade2; }
        public String getGrade3() { return grade3; }
        public String getFinalGrade() { return finalGrade; }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            DatabaseConnection db = new DatabaseConnection();
            grades = db.getStudentGrades(DashboardController.student.getId());
            // Initialize table columns
            subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
            grade1Column.setCellValueFactory(new PropertyValueFactory<>("grade1"));
            grade2Column.setCellValueFactory(new PropertyValueFactory<>("grade2"));
            grade3Column.setCellValueFactory(new PropertyValueFactory<>("grade3"));
            finalGradeColumn.setCellValueFactory(new PropertyValueFactory<>("finalGrade"));
//            URL cssUrl = getClass().getResource("/tablestyle.css");
//            table.getStylesheets().add(cssUrl.toExternalForm());

            // Populate table with data
            populateGradesTable();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void populateGradesTable() {
        if (grades == null) return;

        ObservableList<GradeRow> gradeData = FXCollections.observableArrayList();

        gradeData.add(new GradeRow("Mathematics",
                grades.mathGrade1, grades.mathGrade2, grades.mathGrade3, grades.mathGradeFinal));
        gradeData.add(new GradeRow("Arabic",
                grades.arabGrade1, grades.arabGrade2, grades.arabGrade3, grades.arabGradeFinal));
        gradeData.add(new GradeRow("English",
                grades.engGrade1, grades.engGrade2, grades.engGrade3, grades.engGradeFinal));
        gradeData.add(new GradeRow("Science",
                grades.sciGrade1, grades.sciGrade2, grades.sciGrade3, grades.sciGradeFinal));
        gradeData.add(new GradeRow("French",
                grades.freGrade1, grades.freGrade2, grades.freGrade3, grades.freGradeFinal));
        gradeData.add(new GradeRow("Social Studies",
                grades.scoGrade1, grades.scoGrade2, grades.scoGrade3, grades.scoGradeFinal));

        table.setItems(gradeData);
    }

    public void handleHome(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/studentsmart/dashboard.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handlecourses(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/studentsmart/courses.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handlebscheduele(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/studentsmart/scheduele.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}