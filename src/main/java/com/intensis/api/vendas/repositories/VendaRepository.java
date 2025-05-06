package com.intensis.api.vendas.repositories;


import com.intensis.api.vendas.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VendaRepository extends JpaRepository<Cliente, UUID> {
}
