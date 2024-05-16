package com.example.sistemadeingresssos.rest.dtos;

import com.example.sistemadeingresssos.entities.Evento;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.time.LocalDate;
import java.time.LocalTime;

public record ListagemEventoDTO(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Integer id,

        @NotEmpty(message = "Campo nome é obrigatório")
        String nome,

        @JsonFormat(pattern = "dd/MM/yyyy")
        @NotNull(message = "Campo data é obrigatório")
        LocalDate data,

        @NotNull(message = "Campo horário é obrigatório")
        LocalTime horario,

        @NotEmpty(message = "Campo local é obrigatório")
        String local,

        @NotNull(message = "Campo valor atual é obrigatório")
        Double valorAtual,

        byte[] imagemDestaque
) {
    public ListagemEventoDTO(Evento evento) {
        this(evento.getId(), evento.getNome(), evento.getData(), evento.getHorario(), evento.getLocal(), evento.getValorAtual(), (evento.getImagemPrincipal() == null) ? null : evento.getImagemPrincipal().getBase64());
    }
}
