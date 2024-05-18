package com.example.sistemadeingresssos.entities;

import com.example.sistemadeingresssos.rest.dtos.SalvarIngressoDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingresso {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull(message = "Cliente obrigatório")
    @ManyToOne(cascade = CascadeType.ALL)
    private Cliente cliente;

    @NotNull(message = "Evento obrigatório")
    @ManyToOne(cascade = CascadeType.ALL)
    private Evento evento;

    @NotNull(message = "Campo lote comprado obrigatório")
    private Integer loteComprado;

    private Double total;

    public Ingresso(SalvarIngressoDTO salvarIngressoDTO, Cliente cliente, Evento evento) {
        this.setCliente(cliente);
        this.setEvento(evento);
        this.setTotal(evento);
        this.loteComprado = evento.getLoteAtual();

    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        cliente.addIngresso(this);
    }

    public void setTotal(Evento evento) {
        this.total = evento.getValorAtual() + evento.getTaxa();
    }
}
