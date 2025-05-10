package com.example.studentsmart.Classes;

public class Student extends User{
    String classId;
    String className;

    public Student() {}

    public Student(String userId, String name, String email, String password, String classId, String className) {
        super(userId, name, email, password);
        this.classId = classId;
        this.className = className;
    }

    public void viewSchedule() {
        Schedule schedule = new Schedule(classId, className);
    }

    public String getGrades() { return getGrades(); }

    public void viewAttendance() {}

}
