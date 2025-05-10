package com.example.studentsmart.Controllers;

import com.example.studentsmart.Classes.Authentication;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;



    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML



    public void handleLogin(javafx.scene.input.MouseEvent event) {
        String username = usernameField.getText();
    String password = passwordField.getText();
    Authentication authentication=new Authentication();
    authentication.Login(username, password, event);
    }
}