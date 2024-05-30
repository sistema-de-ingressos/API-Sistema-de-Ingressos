package com.example.sistemadeingresssos.rest.dtos;

import com.example.sistemadeingresssos.entities.Cliente;

import java.util.List;

public record RetornarIngressosClienteDTO(String nome, List<RetornarIngressoDTO> ingressos) {
    public RetornarIngressosClienteDTO(Cliente cliente) {
        this(cliente.getNome(), cliente.getIngressos().stream().map(RetornarIngressoDTO::new).toList());
    }
}
