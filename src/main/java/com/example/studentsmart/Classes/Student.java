package com.example.studentsmart.Classes;

public class Student extends User{
    int id;
    int classId;
    String className;
    String name;
    int age;
    String username;
    String religion;

    public Student() {}

    public Student(int id, String name, int age, String religion, String username, int classId, String className) {
        this.id = id;
        this.className = className;
        this.classId = classId;
        this.name = name;
        this.age = age;
        this.username = username;
        this.religion = religion;

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
    public void viewSchedule() {
        Schedule schedule = new Schedule(classId, className);
    }

    public String getGrades() { return getGrades(); }

    public void viewAttendance() {}

}
