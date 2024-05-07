package com.example.sistemadeingresssos.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evento {

    @Id
    private Long id;
    private String nome;
    private Integer descricao;
    private Date data;
    private String local;
    private Integer loteAtual;
    private Double valorAtual;
    private Integer quantidadeMax;
    private Integer quantidadeAtual;

}
