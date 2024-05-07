package com.example.sistemadeingresssos.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    @Id
    private long id;
    private String logradouro;
    private String cep;
    private String bairro;
    private String cidade;
    private String estado;
    private Integer numero;
    private String complemento;

}
