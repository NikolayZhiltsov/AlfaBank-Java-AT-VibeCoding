package ru.alfabank.homeworks.homework17.models;


import lombok.Data;

@Data
public class Usuario {
    private String nome;
    private String email;
    private String password;
    private String administrador;
}
