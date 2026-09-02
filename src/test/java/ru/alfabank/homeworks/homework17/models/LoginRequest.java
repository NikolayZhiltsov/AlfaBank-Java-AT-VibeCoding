package ru.alfabank.homeworks.homework17.models;


import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
