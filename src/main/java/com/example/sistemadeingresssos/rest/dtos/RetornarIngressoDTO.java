package com.example.sistemadeingresssos.rest.dtos;

import com.example.sistemadeingresssos.entities.Evento;
import com.example.sistemadeingresssos.entities.Ingresso;

import java.time.LocalDate;
import java.util.UUID;

public record RetornarIngressoDTO(String nomeDoEvento, LocalDate dataDoEvento, Double preco, UUID id) {

    public RetornarIngressoDTO(Ingresso ingresso) {
        this(ingresso.getEvento().getNome(), ingresso.getEvento().getData(), ingresso.getTotal(), ingresso.getId());
    }
}
