package com.example.studentsmart.Applications;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Scheduele extends Application {
    @Override
    public void start(Stage stage) throws Exception {
              FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("scheduele.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}
