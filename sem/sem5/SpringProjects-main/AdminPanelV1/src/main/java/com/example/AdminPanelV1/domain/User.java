package com.example.AdminPanelV1.domain;


import lombok.Data;

@Data
public class User {
    private int id;
    private String firstName;
    private int age;
    private String email;
    private String password;
    private boolean isLogin;
    private boolean isAdmin;
}
