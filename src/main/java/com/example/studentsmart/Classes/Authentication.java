package com.example.studentsmart.Classes;

import com.example.studentsmart.Controllers.DashboardController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.ResultSet;

public class Authentication {
    public boolean ValidatePassword(String password) {
        if (password.length() < 8) {
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
            }
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
        boolean notNum=false;
        for (int i = 0; i < phone.length(); i++) {
            if (phone.charAt(i)<'0' || phone.charAt(i)>'9') {
                notNum=true;
            }
        }
        return (!notNum);
    }
    public boolean ValidateName(String name) {
        boolean notChar=false;
        for (int i = 0; i < name.length(); i++) {
            if (!(Character.isLetter(name.charAt(i)))) {
                notChar=true;
                break;
            }
        }
        return (!notChar);
    }
    public boolean ValidateUsername(String username) {
        if ((username.charAt(0)<'a'||username.charAt(0)>'z')&&(username.charAt(0)<'A'||username.charAt(0)>'Z')&&(username.length()>1)) {
            return false;
        }
        return true;
    }
    public Boolean Login(String username, String password) {
        try{
            DatabaseConnection db = new DatabaseConnection();
            ResultSet rs=db.executeQuery("SELECT * FROM students WHERE studentUserName='"+username+"' AND studentPassword='"+password+"'");
            if (rs.next()){
                System.out.println("Successfully logged in");
                DashboardController.student=new Student(rs.getInt("studentId"),rs.getString("studentName"),rs.getInt("studentAge"),rs.getString("studentReligion"),rs.getString("studentUserName"),rs.getInt("classId"),rs.getString("className"));
                return true;
            }else if (db.executeQuery("SELECT * FROM teachers WHERE teacherUserName='"+username+"' AND teacherPassword='"+password+"'").next()){
                System.out.println("Successfully logged in");
            }else if (db.executeQuery("SELECT * FROM managers WHERE managerUserName='"+username+"' AND managerPassword='"+password+"'").next()){
                System.out.println("Successfully logged in");
            }else{
                System.out.println("Invalid username or password");
                return false;
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
