package com.example.sistemadeingresssos.entities;

import com.example.sistemadeingresssos.rest.dtos.SalvarIngressoDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingresso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "Campo id cliente obrigatório")
    private Cliente cliente;
    @NotNull(message = "Campo id evento obrigatório")
    private Evento evento;
    @NotNull(message = "Campo lote comprado obrigatório")
    private Integer loteComprado;
    private Double total;
    private Double taxa;

    public Ingresso(SalvarIngressoDTO salvarIngressoDTO, Cliente cliente, Evento evento) {
        this.cliente = new Cliente();
        this.evento = new Evento();
        this.total = salvarIngressoDTO.total();
        this.taxa = salvarIngressoDTO.taxa();
    }
}
