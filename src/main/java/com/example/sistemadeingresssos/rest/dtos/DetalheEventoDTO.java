package com.example.sistemadeingresssos.rest.dtos;

import com.example.sistemadeingresssos.entities.Evento;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record DetalheEventoDTO(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Integer id,

        @NotEmpty(message = "Campo nome é obrigatório")
        String nome,

        @NotEmpty(message = "Campo descrição é obrigatório")
        String descricao,

        @JsonFormat(pattern = "dd/MM/yyyy")
        @NotNull(message = "Campo data é obrigatório")
        LocalDate data,

        @NotNull(message = "Campo horário é obrigatório")
        LocalTime horario,

        @NotEmpty(message = "Campo local é obrigatório")
        String local,

        @NotNull(message = "Campo lote atual é obrigatório")
        Integer loteAtual,

        @NotNull(message = "Campo valor atual é obrigatório")
        Double valorAtual
) {
    public DetalheEventoDTO(Evento evento) {
        this(evento.getId(), evento.getNome(), evento.getDescricao(),
                evento.getData(), evento.getHorario(), evento.getLocal(),
                evento.getLoteAtual(), evento.getValorAtual());
    }
}
