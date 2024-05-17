package com.example.sistemadeingresssos.rest.dtos;

import com.example.sistemadeingresssos.entities.Cliente;
import com.example.sistemadeingresssos.entities.Endereco;
import com.example.sistemadeingresssos.entities.Ingresso;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record SalvarIngressoDTO(

        @NotEmpty(message = "Campo obrigatório!")
        @CPF(message = "Informe um CPF válido.")
        String cpf,

        @NotEmpty(message = "Campo obrigatório!")
        String nome,

        @JsonFormat(pattern = "dd/MM/yyyy")
        @NotNull(message = "Campo obrigatório")
        LocalDate dataDeNascimento,

        @Valid
        @NotNull(message = "Campo obrigatório!")
        EnderecoDTO endereco,

        @NotNull(message = "Campo id evento obrigatório")
        Integer idEvento,

        Double total
        ) {

}
