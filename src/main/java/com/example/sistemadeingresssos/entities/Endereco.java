package com.example.sistemadeingresssos.entities;

import com.example.sistemadeingresssos.rest.dtos.EnderecoDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String logradouro;
    @NotEmpty(message = "Campo obrigatório!")
    private String cep;
    @NotEmpty(message = "Campo obrigatório!")
    private String bairro;
    @NotEmpty(message = "Campo obrigatório!")
    private String cidade;
    @NotEmpty(message = "Campo obrigatório!")
    private String estado;
    @NotEmpty(message = "Campo obrigatório")
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
}
