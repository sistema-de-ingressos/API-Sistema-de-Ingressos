package com.example.sistemadeingresssos.rest.dtos;

import com.example.sistemadeingresssos.entities.Evento;
import com.example.sistemadeingresssos.entities.Ingresso;

import java.time.LocalDate;
import java.util.UUID;

public record RetornarIngressoDTO(String nomeDoCliente, String nomeDoEvento, LocalDate dataDoEvento, Double preco, String url) {

    public RetornarIngressoDTO(Ingresso ingresso) {
        this(ingresso.getCliente().getNome(), ingresso.getEvento().getNome(), ingresso.getEvento().getData(), ingresso.getTotal(), ingresso.getUrl());
    }
}
