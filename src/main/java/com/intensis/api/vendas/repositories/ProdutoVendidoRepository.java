package com.intensis.api.vendas.repositories;

import com.intensis.api.vendas.entities.ProdutoVendido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ProdutoVendidoRepository extends JpaRepository<ProdutoVendido, UUID> {

    @Query(
            "SELECT pv FROM ProdutoVendido pv " +
                    "WHERE pv.venda.uuid = :idVenda"
    )
    List<ProdutoVendido> findProdutosVendidosByIdVenda(@Param("idVenda") UUID uuid);

    @Query(
            "SELECT pv FROM ProdutoVendido pv " +
                    "WHERE pv.cliente.uuid = :idCliente"
    )
    List<ProdutoVendido> findProdutosVendidosByIdCliente(@Param("idCliente") UUID uuidCliente);
}
