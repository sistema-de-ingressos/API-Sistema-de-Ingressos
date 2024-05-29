package com.example.sistemadeingresssos.rest.dtos;

import com.example.sistemadeingresssos.entities.Cliente;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record SalvarClienteDTO(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Integer id,

        @NotEmpty(message = "Campo obrigatório!")
        @CPF(message = "Informe um CPF válido.")
        String cpf,

        @NotEmpty(message = "Campo obrigatório!")
        String nome,

        @JsonFormat(pattern = "dd/MM/yyyy")
        @NotNull(message = "Campo obrigatório")
        LocalDate dataDeNascimento,

        EnderecoDTO endereco
) {
    public SalvarClienteDTO(Cliente cliente) {
        this(cliente.getId(), cliente.getCpf(), cliente.getNome(), cliente.getDataDeNascimento(), new EnderecoDTO(cliente.getEndereco()));
    }

}
