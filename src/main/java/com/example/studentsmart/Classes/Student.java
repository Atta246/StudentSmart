package com.example.studentsmart.Classes;

public class Student extends User{
    int id;
    int classId;
    String className;
    String name;
    int age;
    String username;
    String religion;
    String gender;
    String phone;
    int absence;

    public Student() {}

    public Student(int id, String name, int age, String religion, String username, int classId, String className, String gender, String phone, String email, String password) {
        this.id = id;
        this.className = className;
        this.classId = classId;
        this.name = name;
        this.age = age;
        this.username = username;
        this.religion = religion;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.password = password;

    }
    public Student(int id, String name, int age, String religion, String username, int classId, String className, String gender, String phone, int studentAbsence) {
        this.id = id;
        this.className = className;
        this.classId = classId;
        this.name = name;
        this.age = age;
        this.username = username;
        this.religion = religion;
        this.gender = gender;
        this.phone = phone;
        this.absence = studentAbsence;
    }
    public int getClassId() {
        return classId;
    }
    public String getClassName() {
        return className;
    }
    public String getUsername(){
        return username;
    }
    public int getAge() {
        return age;
    }
    public String getReligion() {
        return this.religion;
    }
    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }
    public int getStudentAbsence() {
        return absence;
    }
    public String getPhone() {
        return phone;
    }
    public String getGender() {
        return gender;
    }
    public String getGrades() { return getGrades(); }

    public void viewAttendance() {}

}
