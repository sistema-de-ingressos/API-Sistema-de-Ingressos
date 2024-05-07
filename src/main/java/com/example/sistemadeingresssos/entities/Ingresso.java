package com.example.sistemadeingresssos.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingresso {

    @Id
    private Long id;
    @OneToOne
    private Cliente clienteId;
    @OneToOne
    private Evento eventoId;
    private Integer loteComprado;
    private Double preco;
    private String formaDePagamento;

}
