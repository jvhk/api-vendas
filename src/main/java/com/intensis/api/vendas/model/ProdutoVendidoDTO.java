package com.intensis.api.vendas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoVendidoDTO {

    private UUID uuid;
    private ProdutoDTO produtoVendido;
    private ClienteDTO cliente;

}
