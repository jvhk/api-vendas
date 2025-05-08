package com.intensis.api.vendas.service;

import com.intensis.api.vendas.entities.Produto;
import com.intensis.api.vendas.exception.handlers.ResourceNotFoundException;
import com.intensis.api.vendas.model.ProdutoDTO;
import com.intensis.api.vendas.model.mappers.ProdutoMapper;
import com.intensis.api.vendas.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private ProdutoMapper mapper;

    public void cadastrarProduto(ProdutoDTO dto){
        if(dto != null){
            Produto novoProduto = new Produto();
            novoProduto.setNome(dto.getNome());
            novoProduto.setDescricao(dto.getDescricao());
            novoProduto.setPreco(dto.getPreco());
            repository.save(novoProduto);
        }
    }

    public List<ProdutoDTO> listarProdutos() {
        List<Produto> listaDeProdutos = repository.findAll();
        if(!listaDeProdutos.isEmpty()){ //se a lista não estiver vazia, retorna a lista com a conversao usando mapStruct
            return mapper.toDTOList(listaDeProdutos);
        }
        return new ArrayList<>(); //caso contrario retorna uma lista vazia por default
    }

    public void deletarProduto(UUID uuid) throws Exception {
        var produto = repository.findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Produto de ID " + uuid + " não encontrado" ));

        repository.delete(produto);
    }

    public ProdutoDTO atualizarProduto(ProdutoDTO dto){
        Produto produto = repository.findById(dto.getUuid())
                .orElseThrow(() -> new ResourceNotFoundException("Produto  " + dto.getNome() + " não encontrado" ));

        repository.save(mapper.toEntity(dto));
        return mapper.toDTO(produto);

    }

}
