package com.example.sistemadeingresssos.entities;

import com.example.sistemadeingresssos.rest.dtos.SalvarClienteDTO;
import com.example.sistemadeingresssos.rest.dtos.SalvarIngressoDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PESSOAS")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String cpf;

    @NotEmpty(message = "Campo nome obrigatório!")
    private String nome;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "Campo data de nascimento obrigatório")
    @Column(name = "data_de_nascimento")
    private LocalDate dataDeNascimento;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ingresso> ingressos = new ArrayList<>();


    public Cliente(SalvarClienteDTO salvarClienteDTO) {
        this.cpf = salvarClienteDTO.cpf();
        this.nome = salvarClienteDTO.nome();
        this.dataDeNascimento = salvarClienteDTO.dataDeNascimento();
        this.endereco = new Endereco(salvarClienteDTO.enderecoDTO());
    }

    public Cliente(SalvarIngressoDTO salvarIngressoDTO) {
        this.cpf = salvarIngressoDTO.cpf();
        this.nome = salvarIngressoDTO.nome();
        this.dataDeNascimento = salvarIngressoDTO.dataDeNascimento();
        this.endereco = new Endereco(salvarIngressoDTO.endereco());
    }


    public void addIngresso(Ingresso ingresso) {
        this.ingressos.add(ingresso);
    }
}
