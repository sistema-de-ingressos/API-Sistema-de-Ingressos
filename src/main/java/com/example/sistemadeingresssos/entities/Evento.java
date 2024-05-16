package com.example.sistemadeingresssos.entities;

import com.example.sistemadeingresssos.rest.dtos.RetornarIngressoDTO;
import com.example.sistemadeingresssos.rest.dtos.SalvarEventoDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    // Sempre utilizar setValorAtual para atualizar a taxa
    // Taxa deve ficar em evento já que depende do valor de evento, não tem conexão com ingresso
    private Double taxa;


    @NotNull(message = "Campo quantiadade máxima é obrigatório")
    private Integer quantidadeMax;

    @NotNull(message = "Campo quantidade atual é obrigatório")
    private Integer quantidadeAtual;

    @OneToOne
    private EventoImagem imagemPrincipal;

    @OneToMany(mappedBy = "evento")
    private List<EventoImagem> imagens = new ArrayList<>();

    public Evento(String nome, String descricao, LocalDate data, String local, Integer loteAtual, Double valorAtual, Integer quantidadeMax, Integer quantidadeAtual) {
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
        this.local = local;
        this.loteAtual = loteAtual;
        this.setValorAtual(valorAtual);
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
        this.setValorAtual(eventoDTO.valorAtual());
        this.quantidadeMax = eventoDTO.quantidadeMax();
        this.quantidadeAtual = eventoDTO.quantidadeAtual();
    }

    public Evento(RetornarIngressoDTO ingressoDTO) {
        this.nome = ingressoDTO.nomeDoEvento();
        this.data = ingressoDTO.dataDoEvento();
        this.setValorAtual(ingressoDTO.preco());
    }

    public void setValorAtual(Double valorAtual) {
        this.valorAtual = valorAtual;
        this.taxa = valorAtual * 0.1;
    }

    public void setImagens(EventoImagem eventoImagem) {
        this.imagens.add(eventoImagem);
    }
}
