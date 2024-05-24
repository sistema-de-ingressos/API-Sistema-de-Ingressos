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
@Table(name = "TRANSACOES_INGRESSO")
public class Ingresso {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @NotNull(message = "Cliente obrigatório")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id")
    private Cliente cliente;

    @NotNull(message = "Evento obrigatório")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "evento_id", referencedColumnName = "id")
    private Evento evento;

    @NotNull(message = "Campo lote comprado obrigatório")
    @Column(name = "lote_comprado")
    private Integer loteComprado;

    @Column(name = "valor_pago")
    private Double total;

    private String url;

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
