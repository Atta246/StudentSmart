package com.example.studentsmart.Classes;

public class User {
    public String userId;
    public String name;
    public String email;
    public String password;

    public User() {}

    public User(String userId, String name, String email, String password) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(String name, String userId) {
    }

    // Getters
    public String getuserId() { return userId; }

    public String getName() { return name; }

    public String getEmail() { return email; }

    public String getPassword() { return password; }
}

