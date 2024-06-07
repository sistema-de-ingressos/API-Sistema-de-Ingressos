package com.example.sistemadeingresssos.entities;

import com.example.sistemadeingresssos.rest.dtos.EnderecoDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ENDERECOS")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Campo obrigatório!")
    private String logradouro;
    @NotEmpty(message = "Campo obrigatório!")
    private String cep;
    @NotEmpty(message = "Campo obrigatório!")
    private String bairro;
    @NotEmpty(message = "Campo obrigatório!")
    private String cidade;
    @NotEmpty(message = "Campo obrigatório!")
    private String estado;
    @NotNull(message = "Campo obrigatório!")
    private Integer numero;
    private String complemento;

    public Endereco(EnderecoDTO enderecoDTO) {
        this.logradouro = enderecoDTO.logradouro();
        this.cep = enderecoDTO.cep();
        this.bairro = enderecoDTO.bairro();
        this.cidade = enderecoDTO.cidade();
        this.estado = enderecoDTO.estado();
        this.numero = enderecoDTO.numero();
        this.complemento = enderecoDTO.complemento();
    }

    public Endereco(String logradouro, Integer numero, String cidade, String estado) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
    }
}
