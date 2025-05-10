package com.example.studentsmart.Classes;

public class Classes {
    private String className;
    private String classId;
    private int classCapacity;
    public Classes(String className, String classId, int classCapacity) {
        this.className = className;
        this.classId = classId;
        this.classCapacity = classCapacity;
    }
    public String getClassName() {
        return className;
    }
    public String getClassId() {
        return classId;
    }
    public int getClassCapacity() {
        return classCapacity;
    }
    public void addClass(Classes classes) {}
}
