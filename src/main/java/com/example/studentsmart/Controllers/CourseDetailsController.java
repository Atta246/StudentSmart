package com.example.studentsmart.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CourseDetailsController implements Initializable {
    public static String subjectId;
    public static String subjectName;
    @FXML
    private Label welcomeLabel;
    @FXML
    private Button homebtn;
    @FXML
    private Button week1;
    @FXML
    private Button week2;
    @FXML
    private Button week3;
    @FXML
    private Button week4;
    @FXML
    private Button week5;
    @FXML
    private Button week6;
    @FXML
    private Button week7;
    @FXML
    private Button week8;
    @FXML
    private Button week9;
    @FXML
    private Button week10;
    @FXML
    private Button week11;
    @FXML
    private Button week12;
    @FXML
    private Button week13;
    @FXML
    private Button week14;
    @FXML
    private Button week15;
    @FXML
    private Button week16;
    @FXML
    private Label assignmentLabel;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        welcomeLabel.setText("Welcome to "+subjectName+" Planet !");
        assignmentLabel.setText("To Download the Assignment for\"WEEK 1\" Click the Download Button");
    }

    public void handleHomebtn(ActionEvent actionEvent) throws IOException {
        openPage op = new openPage(homebtn,"/com/example/studentsmart/courses.fxml");
        op.execute();
    }

    public void handleWeekbtns(ActionEvent actionEvent) {
        Button btn = (Button) actionEvent.getSource();
        assignmentLabel.setText("To Download the Assignment for\""+ btn.getText()+"\" Click the Download Button");
    }
}
