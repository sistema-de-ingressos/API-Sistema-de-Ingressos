package com.example.sistemadeingresssos.rest.dtos;

import com.example.sistemadeingresssos.entities.Evento;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public record CarrinhoIngressoDTO(
        String nomeDoEvento, Double valor, Double taxa, Double total, String descricao,
        String local, @JsonFormat(pattern = "dd/MM/yyyy") LocalDate data, @JsonFormat(pattern = "HH:mm") LocalTime horario, byte[] imagemDestaque) {

    public CarrinhoIngressoDTO(Evento evento) {
        this(evento.getNome(), evento.getValorAtual(),
                evento.getTaxa(), (evento.getValorAtual() + evento.getTaxa()), evento.getDescricao(), evento.getLocal(),
                evento.getData(), evento.getHorario(), (evento.getImagemPrincipal() == null) ? null : evento.getImagemPrincipal().getBase64());
    }

}
