package com.example.sistemadeingresssos.rest.dtos;

import com.example.sistemadeingresssos.entities.Ingresso;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record RetornarIngressoDTO(Integer idDoEvento, String nomeDoEvento, @JsonFormat(pattern = "dd/MM/yyyy") LocalDate dataDoEvento, Double total, String url) {

    public RetornarIngressoDTO(Ingresso ingresso) {
        this(ingresso.getEvento().getId(), ingresso.getEvento().getNome(), ingresso.getEvento().getData(), ingresso.getTotal(), ingresso.getUrl());
    }

}
