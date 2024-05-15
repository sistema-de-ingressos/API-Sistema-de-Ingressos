package com.example.sistemadeingresssos.entities;

import com.example.sistemadeingresssos.rest.dtos.RetornarIngressoDTO;
import com.example.sistemadeingresssos.rest.dtos.SalvarEventoDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Campo nome é obrigatório")
    private String nome;

    @NotEmpty(message = "Campo descrição é obrigatório")
    private String descricao;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "Campo data é obrigatório")
    private LocalDate data;

    @NotNull(message = "Campo horário é obrigatório")
    private LocalTime horario;

    @NotEmpty(message = "Campo local é obrigatório")
    private String local;

    @NotNull(message = "Campo lote atual é obrigatório")
    private Integer loteAtual;

    @NotNull(message = "Campo valor atual é obrigatório")
    private Double valorAtual;

    @NotNull(message = "Campo quantiadade máxima é obrigatório")
    private Integer quantidadeMax;

    @NotNull(message = "Campo quantidade atual é obrigatório")
    private Integer quantidadeAtual;

//    @OneToOne
//    private EventoImagem imagemPrincipal;
//
//    private List<EventoImagem> imagens = new ArrayList<>();

    public Evento() {

    }

    public Evento(String nome, String descricao, LocalDate data, String local, Integer loteAtual, Double valorAtual, Integer quantidadeMax, Integer quantidadeAtual) {
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
        this.local = local;
        this.loteAtual = loteAtual;
        this.valorAtual = valorAtual;
        this.quantidadeMax = quantidadeMax;
        this.quantidadeAtual = quantidadeAtual;
    }

    public Evento(SalvarEventoDTO eventoDTO) {
        this.nome = eventoDTO.nome();
        this.descricao = eventoDTO.descricao();
        this.data = eventoDTO.data();
        this.horario = eventoDTO.horario();
        this.local = eventoDTO.local();
        this.loteAtual = eventoDTO.loteAtual();
        this.valorAtual = eventoDTO.valorAtual();
        this.quantidadeMax = eventoDTO.quantidadeMax();
        this.quantidadeAtual = eventoDTO.quantidadeAtual();
    }

    public Evento(RetornarIngressoDTO ingressoDTO) {
        this.nome = ingressoDTO.nomeDoEvento();
        this.data = ingressoDTO.dataDoEvento();
        this.valorAtual = ingressoDTO.preco();
    }
}
