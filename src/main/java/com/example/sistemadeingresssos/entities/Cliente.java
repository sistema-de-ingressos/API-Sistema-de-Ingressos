package com.example.sistemadeingresssos.entities;

import com.example.sistemadeingresssos.rest.dtos.SalvarClienteDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    @NotEmpty(message = "Campo CPF obrigatório!")
    @CPF(message = "Informe um CPF válido.")
    private String cpf;

    @NotEmpty(message = "Campo nome obrigatório!")
    private String nome;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "Campo data de nascimento obrigatório")
    private LocalDate dataDeNascimento;

    @OneToOne
    private Endereco endereco;

    private List<Ingresso> ingressos;

    public Cliente(SalvarClienteDTO salvarClienteDTO) {
        this.cpf = salvarClienteDTO.cpf();
        this.nome = salvarClienteDTO.nome();
        this.dataDeNascimento = salvarClienteDTO.dataDeNascimento();
        this.endereco = new Endereco(salvarClienteDTO.enderecoDTO());
    }
}
