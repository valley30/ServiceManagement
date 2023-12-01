package com.repairs.service.model;
public class LoginRequest {

    private String username;
    private String password;

    // Konstruktor bezargumentowy
    public LoginRequest() {
    }

    // Gettery i settery
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
