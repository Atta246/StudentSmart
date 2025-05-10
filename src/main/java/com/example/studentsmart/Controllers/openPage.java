package com.example.studentsmart.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class openPage {
    Node btn;
    String fxmlFile;
    String username;

    public openPage(Node btn, String fxmlFile) {
        this.btn = btn;
        this.fxmlFile = fxmlFile;
        this.username = null;
    }

    public openPage(Node btn, String fxmlFile, String username) {
        this.btn = btn;
        this.fxmlFile = fxmlFile;
        this.username = username;
    }

    public void execute() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent newPage = loader.load();


        if (username != null) {
            Object controller = loader.getController();
            try {
                controller.getClass().getMethod("setUserName", String.class).invoke(controller, username);
            } catch (Exception e) {
                System.out.println("No setUserName method in controller: " + e.getMessage());
            }
        }

        Scene newScene = new Scene(newPage);
        Stage currentStage = (Stage) btn.getScene().getWindow();
        currentStage.setScene(newScene);
        currentStage.show();
    }
}
