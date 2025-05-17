package com.example.studentsmart.Controllers;

import com.example.studentsmart.Classes.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CoursesController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            DatabaseConnection db = new DatabaseConnection();
            List<DatabaseConnection.Subject> list = db.getSubjects();
            btn1.setText(list.get(0).getSubjectName());
            btn2.setText(list.get(1).getSubjectName());
            btn3.setText(list.get(2).getSubjectName());
            btn4.setText(list.get(3).getSubjectName());
            btn5.setText(list.get(4).getSubjectName());
            btn6.setText(list.get(5).getSubjectName());
            name1.setText(list.get(0).getTeacherName());
            name2.setText(list.get(1).getTeacherName());
            name3.setText(list.get(2).getTeacherName());
            name4.setText(list.get(3).getTeacherName());
            name5.setText(list.get(4).getTeacherName());
            name6.setText(list.get(5).getTeacherName());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private Button btn4;
    @FXML
    private Button btn5;
    @FXML
    private Button btn6;
    @FXML
    private Label name1;
    @FXML
    private Label name2;
    @FXML
    private Label name3;
    @FXML
    private Label name4;
    @FXML
    private Label name5;
    @FXML
    private Label name6;

//    public void initialize(URL location, ResourceBundle resources) {
//        setupTableColumns();
//        URL cssUrl = getClass().getResource("/tablestyle.css");
//        if (cssUrl != null) {
//            employeeTable.getStylesheets().add(cssUrl.toExternalForm());
//        } else {
//            System.err.println("CSS file not found!");
//        }

    public void handleCourses(MouseEvent event) {

    }

    public void handleHome(ActionEvent actionEvent) {
        try {
            // Load the FXML file for the home page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/studentsmart/dashboard.fxml"));
            Parent root = loader.load();

            // Create a new scene with the loaded root node
            Scene scene = new Scene(root);

            // Get the current stage and set the new scene
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handlegrades(ActionEvent actionEvent) {
        try {
            // Load the FXML file for the grades page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/studentsmart/results.fxml"));
            Parent root = loader.load();

            // Create a new scene with the loaded root node
            Scene scene = new Scene(root);

            // Get the current stage and set the new scene
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handlescheduele(ActionEvent actionEvent) {
        try {
            // Load the FXML file for the schedule page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/studentsmart/scheduele.fxml"));
            Parent root = loader.load();

            // Create a new scene with the loaded root node
            Scene scene = new Scene(root);

            // Get the current stage and set the new scene
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleArabicbtn(ActionEvent actionEvent) throws IOException {
        CourseDetailsController.subjectId="Ara";
        CourseDetailsController.subjectName="Arabic";
        openPage op = new openPage(btn1,"/com/example/studentsmart/courseDetails.fxml");
        op.execute();
    }

    public void handleEnglishbtn(ActionEvent actionEvent) throws IOException {
        CourseDetailsController.subjectId="Eng";
        CourseDetailsController.subjectName="English";
        openPage op = new openPage(btn2,"/com/example/studentsmart/courseDetails.fxml");
        op.execute();
    }

    public void handleFrencbtn(ActionEvent actionEvent) throws IOException {
        CourseDetailsController.subjectId="Fre";
        CourseDetailsController.subjectName="French";
        openPage op = new openPage(btn3,"/com/example/studentsmart/courseDetails.fxml");
        op.execute();
    }

    public void handleMathbtn(ActionEvent actionEvent) throws IOException {
        CourseDetailsController.subjectId="Math";
        CourseDetailsController.subjectName="Mathematics";
        openPage op = new openPage(btn4,"/com/example/studentsmart/courseDetails.fxml");
        op.execute();
    }

    public void handleSciencebtn(ActionEvent actionEvent) throws IOException {
        CourseDetailsController.subjectId="Sci";
        CourseDetailsController.subjectName="Science";
        openPage op = new openPage(btn5,"/com/example/studentsmart/courseDetails.fxml");
        op.execute();
    }

    public void handleSocialbtn(ActionEvent actionEvent) throws IOException {
        CourseDetailsController.subjectId="Soc";
        CourseDetailsController.subjectName="Social Studies";
        System.out.println("lool");
        openPage op = new openPage(btn6,"/com/example/studentsmart/courseDetails.fxml");
        op.execute();
    }
}
