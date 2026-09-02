package ru.alfabank.homeworks.homework17.models;


import lombok.Data;

@Data
public class Produto {
    private String nome;
    private Integer preco;
    private String descricao;
    private Integer quantidade;
}
