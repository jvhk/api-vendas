package com.intensis.api.vendas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO {

    private UUID uuid;
    private String nome;
    private String descricao;
    private BigDecimal preco;

}
