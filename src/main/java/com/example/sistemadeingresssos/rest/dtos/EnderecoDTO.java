package com.example.sistemadeingresssos.rest.dtos;

import com.example.sistemadeingresssos.entities.Endereco;

public record EnderecoDTO(String cep, String logradouro, String bairro, String cidade, String estado, Integer numero, String complemento) {
    public EnderecoDTO(Endereco endereco) {
        this(endereco.getCep(), endereco.getLogradouro(), endereco.getBairro(),
                endereco.getCidade(), endereco.getEstado(), endereco.getNumero(), endereco.getComplemento());
    }
}
