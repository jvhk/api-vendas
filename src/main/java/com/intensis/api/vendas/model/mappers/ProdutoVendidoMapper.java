package com.intensis.api.vendas.model.mappers;

import com.intensis.api.vendas.entities.Cliente;
import com.intensis.api.vendas.entities.ProdutoVendido;
import com.intensis.api.vendas.model.ClienteDTO;
import com.intensis.api.vendas.model.ProdutoVendidoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProdutoVendidoMapper {

    @Mapping(target = "produtoVendido", source = "produto")
    @Mapping(target = "cliente", source = "cliente")
    ProdutoVendidoDTO toDTO(ProdutoVendido produtoVendido);

    List<ProdutoVendidoDTO> toDTOList(List<ProdutoVendido> produtoVendidos);

    @Mapping(target = "produto", source = "produtoVendido")
    @Mapping(target = "cliente", source = "cliente")
    ProdutoVendido toEntity(ProdutoVendidoDTO dto);

}
