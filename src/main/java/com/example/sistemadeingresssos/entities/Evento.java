package com.example.sistemadeingresssos.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String descricao;
    private Date data;
    private String local;
    private Integer loteAtual;
    private Double valorAtual;
    private Integer quantidadeMax;
    private Integer quantidadeAtual;

    public Evento() {

    }

    public Evento(String nome, String descricao, Date data, String local, Integer loteAtual, Double valorAtual, Integer quantidadeMax, Integer quantidadeAtual) {
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
        this.local = local;
        this.loteAtual = loteAtual;
        this.valorAtual = valorAtual;
        this.quantidadeMax = quantidadeMax;
        this.quantidadeAtual = quantidadeAtual;
    }

}
