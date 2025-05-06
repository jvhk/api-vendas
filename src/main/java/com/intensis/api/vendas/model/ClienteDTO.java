package com.intensis.api.vendas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {

    private UUID uuid;
    private String nome;
    private String email;
    private String telefone;

}
