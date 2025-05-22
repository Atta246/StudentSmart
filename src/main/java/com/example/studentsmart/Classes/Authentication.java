package com.example.studentsmart.Classes;

import com.example.studentsmart.Controllers.DashboardController;
import com.example.studentsmart.Controllers.openPage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.util.List;

public class Authentication {
    public boolean ValidatePassword(String password) {
        if (password.length() < 8) {
            Alert.showAlert("Weak Password", "Please Enter a Stronger Password","The Password Must Contain:\n* At least one capital letter\n* At least one digit\n* the length must be at least 8 characters");
            return false;
        }
        boolean num=false,a=false,A=false;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i))) {
                num = true;
            }else if (password.charAt(i)>='a' && password.charAt(i)<='z') {
                a = true;
            }else if (password.charAt(i)>='A' && password.charAt(i)<='Z') {
                A = true;
            }else if (password.charAt(i)==' '){
                Alert.showAlert("Error","Password Can not Contain white spaces");
                return false;
            }
        }
        if (!(num&&a&&A)){
            Alert.showAlert("Weak Password", "Please Enter a Stronger Password","The Password Must Contain:\n* At least one capital letter\n* At least one digit\n* the length must be at least 8 characters");
        }
        return (num&&a&&A);
    }
    public boolean ValidateEmail(String email) {
        boolean at=false,dot=false;
        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i)=='@')
                at=true;
            else if (email.charAt(i)=='.')
                dot=true;
        }
        return (at&&dot);
    }
    public boolean ValidatePhone(String phone) {
        if (!(phone.charAt(0)=='0'&&phone.charAt(1)=='1'&&(phone.charAt(2)=='0'||phone.charAt(2)=='1'||phone.charAt(2)=='5'||phone.charAt(2)=='2'))){
            return false;
        }
        if (phone.length()!=11) {
            return false;
        }
        for (int i = 0; i < phone.length(); i++) {
            if (phone.charAt(i)<'0' || phone.charAt(i)>'9') {
                return false;
            }
        }
        return true;
    }
    public boolean ValidateAge(String age) {
        for (int i = 0; i < age.length(); i++) {
            if (age.charAt(i)<'0' || age.charAt(i)>'9') {
                return false;
            }
        }
        if (Integer.parseInt(age)<0||Integer.parseInt(age)>30) {
            return false;
        }
        return true;
    }
    public boolean ValidateSubject(String subject) {
        try(DatabaseConnection db = new DatabaseConnection()){
            List<DatabaseConnection.Subject> subjects = db.getSubjects();
            for (DatabaseConnection.Subject s : subjects) {
                if (s.getSubjectName()==subject){
                    return true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return false;
    }
    public boolean ValidateName(String name) {
        boolean notChar=false;
        for (int i = 0; i < name.length(); i++) {
            if (!(Character.isLetter(name.charAt(i))||(i!=0&&name.charAt(i)==' '))) {
                notChar=true;
                break;
            }
        }
        return (!notChar);
    }


    public boolean ValidateStudentUsername(String username) {
        if ((username.charAt(0)<'a'||username.charAt(0)>'z')&&(username.charAt(0)<'A'||username.charAt(0)>'Z')&&(username.length()>2)) {
            Alert.showAlert("Error", "Invalid Username");
            return false;
        }
        try (DatabaseConnection db = new DatabaseConnection()){
            List<Student> teachers = db.getAllStudents();
            for (Student t : teachers) {
                System.out.println("username: "+t.getUsername()+"//// current:" + username);
                if (t.getUsername().matches(username)){
                    Alert.showAlert("Error","Username is already taken");
                    return false;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }



    public boolean Login(String username, String password) {
        try{
            DatabaseConnection db = new DatabaseConnection();
            ResultSet rs=db.executeQuery("SELECT * FROM students WHERE studentUserName='"+username+"' AND studentPassword='"+password+"'");
            if (rs.next()){
                System.out.println("Successfully logged in");
                DashboardController.student=new Student(
                        rs.getInt("studentId"),
                        rs.getString("studentName"),
                        rs.getInt("studentAge"),
                        rs.getString("studentReligion"),
                        rs.getString("studentUserName"),
                        rs.getInt("classId"),
                        rs.getString("className"),
                        rs.getString("studentGender"),
                        rs.getString("studentPhone"),
                        rs.getString("studentEmail"),
                        rs.getString("studentPassword")
                );
                return true;

            }else{
                Alert.showAlert("Login Failed", "Invalid Username or Password");
                return false;
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}


