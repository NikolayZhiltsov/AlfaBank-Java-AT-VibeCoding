package ru.alfabank.homeworks.homework17.models;

import java.util.List;

import lombok.Data;

@Data
public class CarrinhoRequest {
    private List<ItemProduto> produtos;

    @Data
    public static class ItemProduto {
        private String idProduto;
        private Integer quantidade;
    }
}
