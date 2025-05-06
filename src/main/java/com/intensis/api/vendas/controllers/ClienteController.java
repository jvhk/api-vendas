package com.intensis.api.vendas.controllers;

import com.intensis.api.vendas.model.ClienteDTO;
import com.intensis.api.vendas.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    ClienteService service;

    @PostMapping
    public ResponseEntity<Void> cadastrarCliente(@RequestBody ClienteDTO dto){
        service.cadastrarCliente(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ClienteDTO>> listarClientes(){
        return new ResponseEntity<>(service.listarClientes(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ClienteDTO> editarCliente(@RequestBody ClienteDTO dto){
        return new ResponseEntity<>(service.atualizarCliente(dto), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> editarCliente(@PathVariable(name = "id") UUID uuid) throws Exception {
        service.deletarCliente(uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
