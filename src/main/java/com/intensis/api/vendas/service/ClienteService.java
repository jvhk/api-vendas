package com.intensis.api.vendas.service;

import com.intensis.api.vendas.entities.Cliente;
import com.intensis.api.vendas.exception.handlers.ResourceNotFoundException;
import com.intensis.api.vendas.model.ClienteDTO;
import com.intensis.api.vendas.model.mappers.ClienteMapper;
import com.intensis.api.vendas.repositories.ClienteRepository;
import com.intensis.api.vendas.util.CpfUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ClienteService {

    @Autowired
    private ClienteMapper mapper;

    @Autowired
    private ClienteRepository repository;

    public void cadastrarCliente(ClienteDTO dto){
        String cpfSemFormatacao = CpfUtil.removerMascara(dto.getCpf());
        if(!CpfUtil.validarCpf(cpfSemFormatacao)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF informado inválido!");
        }
        else if(dto != null){
            Cliente novoCliente = new Cliente();
            novoCliente.setNome(dto.getNome());
            novoCliente.setTelefone(dto.getTelefone());
            novoCliente.setEmail(dto.getEmail());
            novoCliente.setCpf(cpfSemFormatacao);
            repository.save(novoCliente);
        }
        else{
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro ao tentar salvar o cliente.");
        }
    }

    public List<ClienteDTO> listarClientes() {
        List<Cliente> listaDeClientes = repository.findAll();
        if(!listaDeClientes.isEmpty()){ //se a lista não estiver vazia, retorna a lista com a conversao usando mapStruct
            return mapper.toDTOList(listaDeClientes);
        }
        return new ArrayList<>(); //caso contrario retorna uma lista vazia por default
    }

    public void deletarCliente(UUID uuid) throws Exception {
        var cliente = repository.findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente de ID " + uuid + " não encontrado" ));

        repository.delete(cliente);
    }

    public ClienteDTO atualizarCliente(ClienteDTO dto){
        Cliente cliente = repository.findById(dto.getUuid())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente  " + dto.getNome() + " não encontrado" ));

        repository.save(mapper.toEntity(dto));
        return mapper.toDTO(cliente);

    }
}
