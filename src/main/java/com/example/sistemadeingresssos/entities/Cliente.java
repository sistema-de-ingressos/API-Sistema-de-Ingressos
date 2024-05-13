package com.example.sistemadeingresssos.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @NotEmpty(message = "Campo obrigatório!")
    @CPF(message = "Informe um CPF válido.")
    private String cpf;
    @NotEmpty(message = "Campo obrigatório!")
    private String nome;
    @NotEmpty(message = "Campo obrigatório")
    private LocalDate dataDeNascimento;
    @OneToOne
    private Endereco enderecoId;
    @OneToMany
    private List<Ingresso> lista;

}
