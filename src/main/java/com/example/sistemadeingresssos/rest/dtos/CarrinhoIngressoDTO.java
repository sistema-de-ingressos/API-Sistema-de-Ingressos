package com.example.sistemadeingresssos.rest.dtos;

import com.example.sistemadeingresssos.entities.Evento;
import com.example.sistemadeingresssos.entities.EventoImagem;
import com.example.sistemadeingresssos.entities.Ingresso;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public record CarrinhoIngressoDTO(
        String nomeDoEvento, Double valor, Double taxa, Double total, String descricao,
        String local, @JsonFormat(pattern = "dd/MM/yyyy") LocalDate data, LocalTime horario, byte[] imagemDestaque) {

    public CarrinhoIngressoDTO(Ingresso ingresso) {
        this(ingresso.getEvento().getNome(), ingresso.getEvento().getValorAtual(),
                ingresso.getEvento().getTaxa(), ingresso.getTotal(), ingresso.getEvento().getDescricao(), ingresso.getEvento().getLocal(),
                ingresso.getEvento().getData(), ingresso.getEvento().getHorario(), (ingresso.getEvento().getImagemPrincipal().getBase64()));
    }

}
