package com.example.sistemadeingresssos.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    private String cpf;
    private String nome;
    private Date dataDeNascimento;
    @OneToOne
    private Endereco enderecoId;

}
