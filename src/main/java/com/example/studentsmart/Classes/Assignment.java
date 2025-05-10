package com.example.studentsmart.Classes;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Assignment {
    private String assignmentId;
    private String assignmentTitle;
    private String assignmentDescription;
    private String week;
    public Assignment() {}
    public Assignment(String assignmentId, String assignmentTitle, String assignmentDescription, String week) {
        this.assignmentId = assignmentId;
        this.assignmentTitle = assignmentTitle;
        this.assignmentDescription = assignmentDescription;
        this.week = week;
    }
    public String getAssignmentId() {
        return assignmentId;
    }
    public String getAssignmentTitle() {
        return assignmentTitle;
    }
    public String getAssignmentDescription() {
        return assignmentDescription;
    }
    public String getWeek() {
        return week;
    }
    public void addAssignment(String assignmentId, String assignmentTitle, String assignmentDescription, Date assignmentDeadline){
        try{
            DatabaseConnection db = new DatabaseConnection();
            Map<String, Object> map = new HashMap<>();
            map.put("assignmentId", assignmentId);
            map.put("assignmentTitle", assignmentTitle);
            map.put("assignmentDesc", assignmentDescription);
            map.put("assignmentDeadline", assignmentDeadline);
            db.insert("assignments", map);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void submitAssignment(String userId,String assignmentId){}
    public void removeAssignment(String assignmentId){}
}

