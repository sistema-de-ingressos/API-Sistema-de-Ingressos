package com.example.sistemadeingresssos.rest.dtos;

import com.example.sistemadeingresssos.entities.Endereco;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record EnderecoDTO(
        @NotEmpty(message = "Campo obrigatório!")
        String logradouro,
        @NotEmpty(message = "Campo obrigatório!")
        String cep,
        @NotEmpty(message = "Campo obrigatório!")
        String bairro,
        @NotEmpty(message = "Campo obrigatório!")
        String cidade,
        @NotEmpty(message = "Campo obrigatório!")
        String estado,
        @NotNull(message = "Campo obrigatório!")
        Integer numero,
        String complemento
        ) {
        public EnderecoDTO(Endereco endereco) {
            this(endereco.getLogradouro(), endereco.getCep(), endereco.getBairro(),
                    endereco.getCidade(), endereco.getEstado(), endereco.getNumero(), endereco.getComplemento());

        }
}
