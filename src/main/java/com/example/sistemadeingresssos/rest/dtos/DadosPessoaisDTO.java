package com.example.sistemadeingresssos.rest.dtos;

import com.example.sistemadeingresssos.entities.Cliente;
import com.example.sistemadeingresssos.entities.Ingresso;

import java.time.LocalDate;
import java.util.List;

public record DadosPessoaisDTO(String nome, String cpf, LocalDate dataDeNascimento, List<Ingresso> lista) {

    DadosPessoaisDTO(Cliente cliente){
        this(cliente.getNome(), cliente.getCpf(), cliente.getDataDeNascimento(), cliente.getLista());
    }
}
