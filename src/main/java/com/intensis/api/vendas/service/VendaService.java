package com.intensis.api.vendas.service;

import com.intensis.api.vendas.entities.Produto;
import com.intensis.api.vendas.entities.ProdutoVendido;
import com.intensis.api.vendas.entities.Venda;
import com.intensis.api.vendas.model.ProdutoVendidoDTO;
import com.intensis.api.vendas.model.VendaDTO;
import com.intensis.api.vendas.model.mappers.ProdutoVendidoMapper;
import com.intensis.api.vendas.repositories.ClienteRepository;
import com.intensis.api.vendas.repositories.ProdutoRepository;
import com.intensis.api.vendas.repositories.ProdutoVendidoRepository;
import com.intensis.api.vendas.repositories.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class VendaService {

    @Autowired
    VendaRepository vendaRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ProdutoVendidoRepository produtoVendidoRepository;

    @Autowired
    ProdutoVendidoMapper produtoVendidoMapper;

    public void venderProdutos(VendaDTO dto){
        Venda venda = new Venda();
        if(!dto.getProdutos().isEmpty()){
            List<ProdutoVendido> produtosVendidos = new ArrayList<>();
            for(UUID idProduto : dto.getProdutos()){
                ProdutoVendido produtoVendido = new ProdutoVendido();
                Produto produto = produtoRepository.findById(idProduto).orElseThrow();
                produtoVendido.setVenda(venda);
                produtoVendido.setProduto(produto);
                produtoVendido.setCliente(clienteRepository.findByCpf(dto.getCpfComprador()));
                produtosVendidos.add(produtoVendido);
            }
            venda.setItensVendidos(produtosVendidos);
            venda.setDataVenda(LocalDateTime.now());
            venda.setValorTotal(calculaValorDosProdutos(dto.getProdutos()));
            venda.setCliente(clienteRepository.findByCpf(dto.getCpfComprador()));
            vendaRepository.save(venda);
        }
    }

    public List<ProdutoVendidoDTO> listarVendas(){
        List<ProdutoVendido> produtoVendidos = produtoVendidoRepository.findAll();
        return  produtoVendidoMapper.toDTOList(produtoVendidos);
    }

    public List<ProdutoVendidoDTO> listarVendasPorCliente(UUID uuidCliente){
        List<ProdutoVendido> produtoVendidos = produtoVendidoRepository.findProdutosVendidosByIdCliente(uuidCliente);
        return  produtoVendidoMapper.toDTOList(produtoVendidos);
    }

    private BigDecimal calculaValorDosProdutos(List<UUID> produtos) {
        BigDecimal somaValores = BigDecimal.ZERO;
        for(UUID produto : produtos){
              Produto produtoEntity = produtoRepository.findById(produto).get();
              somaValores = somaValores.add(produtoEntity.getPreco());
        }
        return somaValores;
    }

}
