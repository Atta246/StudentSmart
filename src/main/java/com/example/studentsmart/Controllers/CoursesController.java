package com.example.studentsmart.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class CoursesController {
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
}
