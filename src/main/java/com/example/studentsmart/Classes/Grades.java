package com.example.studentsmart.Classes;
import java.util.List;
public class Grades {
    private String userId;
    private List<Integer> grades;
    public Grades(String userId){
        this.userId = userId;
    }
    public List<Integer> getGrades() {
        return grades;
    }
}