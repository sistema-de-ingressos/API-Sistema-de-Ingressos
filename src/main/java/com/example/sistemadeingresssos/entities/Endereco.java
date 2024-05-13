package com.example.sistemadeingresssos.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    @Id
    private Integer id;
    private String logradouro;
//    @NotEmpty(message = "Campo obrigatório!")
    private String cep;
//    @NotEmpty(message = "Campo obrigatório!")
    private String bairro;
//    @NotEmpty(message = "Campo obrigatório!")
    private String cidade;
//    @NotEmpty(message = "Campo obrigatório!")
    private String estado;
//    @NotEmpty(message = "Campo obrigatório")
    private Integer numero;
    private String complemento;

}
