package com.example.sistemadeingresssos.rest.dtos;

import com.example.sistemadeingresssos.entities.Evento;
import com.example.sistemadeingresssos.entities.Ingresso;

public record CarrinhoIngressoDTO(String nomeDoEvento, Double valor, Double taxa, Double total) {
    public CarrinhoIngressoDTO(Ingresso ingresso, Evento evento) {
        this(evento.getNome(), evento.getValorAtual(), ingresso.getTaxa(), ingresso.getTotal());
    }
}
