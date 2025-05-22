package com.example.studentsmart.Controllers;

import com.example.studentsmart.Classes.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    @FXML
    private Button assignmentbtn;
    String currentWeekSelection="Week 1";
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
        currentWeekSelection=btn.getText();
    }


    public static void openUrl(String pathFromDatabase) {
        try {
            URI uri;

            if (pathFromDatabase.startsWith("http://") || pathFromDatabase.startsWith("https://")) {
                // Handle as a normal web URL
                uri = new URI(pathFromDatabase);
            } else {
                // Handle as a file path
                File file = new File(pathFromDatabase);
                uri = file.toURI();
            }

            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(uri);
            } else {
                System.err.println("Desktop is not supported on this platform.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            // Optional: Show an alert to the user if needed
        }
    }

    public static void openPath(String subjectId, String week , int x) {
        // Sanitize column name
        String column = week.toLowerCase().replaceAll("\\s", "");
        String query;
       if (x==1) {
           query = "SELECT `" + column + "` FROM assignments WHERE subjectId = ?";
       }else{
            query = "SELECT `" + column + "` FROM lessons WHERE subjectId = ?";
       }
        try (DatabaseConnection db = new DatabaseConnection();
             PreparedStatement stmt = db.connection.prepareStatement(query)) {

            stmt.setString(1, subjectId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String url = rs.getString(1);

                if (url != null && !url.isEmpty()) {
//                    Desktop.getDesktop().browse(new URI(url));
                    openUrl(url);

                } else {
                    System.out.println("No URL found for this week.");
                }
            } else {
                System.out.println("No assignment found for subject: " + subjectId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void handleDownloadAssignments(ActionEvent actionEvent) {
        openPath(subjectName,currentWeekSelection,1);
    }

    public void handleLessonDownload(ActionEvent actionEvent) {
        openPath(subjectName,currentWeekSelection,2);
    }
}
