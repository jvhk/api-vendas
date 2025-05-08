package com.intensis.api.vendas.model.mappers;

import com.intensis.api.vendas.entities.Cliente;
import com.intensis.api.vendas.entities.Produto;
import com.intensis.api.vendas.model.ClienteDTO;
import com.intensis.api.vendas.model.ProdutoDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    ProdutoDTO toDTO(Produto produto);

    List<ProdutoDTO> toDTOList(List<Produto> produtos);

    Produto toEntity(ProdutoDTO dto);

}
