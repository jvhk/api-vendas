package com.intensis.api.vendas.model.mappers;

import com.intensis.api.vendas.entities.Cliente;
import com.intensis.api.vendas.model.ClienteDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteDTO toDTO(Cliente cliente);

    List<ClienteDTO> toDTOList(List<Cliente> clienteList);

    Cliente toEntity(ClienteDTO dto);

}
