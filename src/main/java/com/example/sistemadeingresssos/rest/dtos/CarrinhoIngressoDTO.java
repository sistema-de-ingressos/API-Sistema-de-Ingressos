package com.example.sistemadeingresssos.rest.dtos;

import com.example.sistemadeingresssos.entities.Evento;
import com.example.sistemadeingresssos.entities.Ingresso;

public record CarrinhoIngressoDTO(String nomeDoEvento, Double valor, Double taxa, Double total) {
    public CarrinhoIngressoDTO(Ingresso ingresso) {
        this(ingresso.getEvento().getNome(), ingresso.getEvento().getValorAtual(), ingresso.getEvento().getTaxa(), ingresso.getTotal());
    }
}
