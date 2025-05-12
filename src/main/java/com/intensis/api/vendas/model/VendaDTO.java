package com.intensis.api.vendas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendaDTO {

    private UUID uuid;
    private String cpfComprador;
    private List<UUID> produtos;

}
