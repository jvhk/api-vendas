package com.intensis.api.vendas.repositories;

import com.intensis.api.vendas.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

    @Query("select c from Cliente c WHERE c.cpf = :cpf ")
    Cliente findByCpf(@Param("cpf") String cpfComprador);

}
