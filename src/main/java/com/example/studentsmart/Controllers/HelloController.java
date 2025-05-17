package com.example.studentsmart.Controllers;

import com.example.studentsmart.Classes.Authentication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    private Button loginButton;



    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML



    public void handleLogin(ActionEvent event) throws Exception {
        String username = usernameField.getText();
    String password = passwordField.getText();
    Authentication authentication=new Authentication();
    if(authentication.Login(username, password)){
        openPage op = new openPage(loginButton , "/com/example/studentsmart/dashboard.fxml");
        op.execute();

    }
    }
}