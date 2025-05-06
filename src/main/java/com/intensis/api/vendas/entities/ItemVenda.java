package com.intensis.api.vendas.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "item_venda")
@Data
public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "id_produto", referencedColumnName = "uuid")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "id_venda", referencedColumnName = "uuid")
    private Venda venda;

    @Column(name = "preco_unitario")
    private BigDecimal precoUnitario;

    @Column(name = "quantidade")
    private int quantidade;

    @Column(name = "subtotal") //preco unitario * quantidade
    private BigDecimal subtotal;

    @PrePersist
    @PreUpdate
    private void calcularSubtotal() {
        if (precoUnitario != null && quantidade > 0) {
            this.subtotal = precoUnitario.multiply(BigDecimal.valueOf(quantidade));
        }
    }

}
