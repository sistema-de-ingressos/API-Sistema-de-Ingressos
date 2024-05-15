package com.example.sistemadeingresssos.rest.dtos;

import com.example.sistemadeingresssos.entities.Cliente;
import com.example.sistemadeingresssos.entities.Ingresso;

import java.util.List;

public record ListagemIngressoDTO(List<Ingresso> list) {
    public ListagemIngressoDTO(Cliente cliente) {
        this(cliente.getIngressos());
    }
}
