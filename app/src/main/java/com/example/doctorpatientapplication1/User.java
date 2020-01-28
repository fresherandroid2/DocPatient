package com.example.doctorpatientapplication1;

public class User {

    public String role;
    public String email;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String email, String role) {
        this.role = role;
        this.email = email;
    }
}
