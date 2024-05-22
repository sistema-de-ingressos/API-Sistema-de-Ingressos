package com.example.sistemadeingresssos.rest.dtos;

import com.example.sistemadeingresssos.entities.Cliente;

import java.time.LocalDate;

public record RetornarClienteDTO(
        String nome, LocalDate dataDeNascimento, EnderecoDTO endereco
) {
    public RetornarClienteDTO (Cliente cliente){
        this(cliente.getNome(), cliente.getDataDeNascimento(), new EnderecoDTO(cliente.getEndereco()));
    }
}
