package com.intensis.api.vendas.controllers;

import com.intensis.api.vendas.model.ProdutoVendidoDTO;
import com.intensis.api.vendas.model.VendaDTO;
import com.intensis.api.vendas.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/venda")
public class VendaController {

    @Autowired
    VendaService service;

    @PostMapping
    public ResponseEntity<Void> venderProdutos(@RequestBody VendaDTO dto){
        service.venderProdutos(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoVendidoDTO>> listarTodasAsVendas(){
        return new ResponseEntity<List<ProdutoVendidoDTO>>(service.listarVendas(), HttpStatus.OK);
    }

    @GetMapping("{idCliente}")
    public ResponseEntity<List<ProdutoVendidoDTO>> listarVendasPorCliente(@PathVariable("idCliente") UUID idCliente){
        return new ResponseEntity<List<ProdutoVendidoDTO>>(service.listarVendasPorCliente(idCliente), HttpStatus.OK);
    }

}
