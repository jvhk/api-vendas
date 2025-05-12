package com.intensis.api.vendas.controllers;

import com.intensis.api.vendas.model.ClienteDTO;
import com.intensis.api.vendas.model.ProdutoDTO;
import com.intensis.api.vendas.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    ProdutoService service;

    @PostMapping
    public ResponseEntity<Void> cadastrarProduto(@RequestBody ProdutoDTO dto){
        service.cadastrarProduto(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listarProdutos(){
        return new ResponseEntity<>(service.listarProdutos(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ProdutoDTO> editarProduto(@RequestBody ProdutoDTO dto){
        return new ResponseEntity<>(service.atualizarProduto(dto), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable(name = "id") UUID uuid) throws Exception {
        service.deletarProduto(uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
