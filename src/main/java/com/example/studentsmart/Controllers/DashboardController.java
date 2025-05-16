package com.example.studentsmart.Controllers;

import com.example.studentsmart.Classes.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setText(student.getName());
        religion.setText(student.getReligion());
        username.setText(student.getUsername());
        age.setText(String.valueOf(student.getAge()));
        className.setText(student.getClassName());
        System.out.println(student.getReligion()+" "+student.getName());
    }

    @FXML
    private Button btnGrade;
    public static Student student;
    @FXML
    private TextField name;
    @FXML
    private TextField religion;
    @FXML
    private TextField username;
    @FXML
    private TextField age;
    @FXML
    private TextField className;



    public void handleCourses(MouseEvent event) throws IOException {
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/studentsmart/courses.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
    }

    public void handlegrade(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/studentsmart/results.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();

    }
}

    public void handleHome(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/studentsmart/hello-view.fxml"));
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
            e.printStackTrace();}
    }

    public void handlescheduele(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/studentsmart/scheduele.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();}
    }
}
