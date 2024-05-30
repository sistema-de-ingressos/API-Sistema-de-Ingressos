package com.example.sistemadeingresssos.rest.dtos;

import com.example.sistemadeingresssos.entities.Evento;
import com.example.sistemadeingresssos.enums.EventoStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record SalvarEventoDTO(

        @NotEmpty(message = "Campo nome é obrigatório")
        String nome,

        @NotEmpty(message = "Campo descrição é obrigatório")
        String descricao,

        @JsonFormat(pattern = "dd/MM/yyyy")
        @NotNull(message = "Campo data é obrigatório")
        LocalDate data,

        @NotNull(message = "Campo horário é obrigatório")
        @JsonFormat(pattern = "HH:mm")
        LocalTime horario,

        @NotEmpty(message = "Campo local é obrigatório")
        String local,

        @NotNull(message = "Campo lote atual é obrigatório")
        Integer loteAtual,

        @NotNull(message = "Campo valor atual é obrigatório")
        Double valorAtual,

        @NotNull(message = "Campo quantiadade máxima é obrigatório")
        Integer quantidadeMax,

        @NotNull(message = "Campo quantidade atual é obrigatório")
        Integer quantidadeAtual,

        @NotNull(message = "Campo status é obrigatório")
        @Enumerated(EnumType.STRING)
        EventoStatus status

) {
    public SalvarEventoDTO(Evento evento) {
        this(evento.getNome(), evento.getDescricao(), evento.getData(),
                evento.getHorario(), evento.getLocal(), evento.getLoteAtual(),
                evento.getValorAtual(), evento.getQuantidadeMax(), evento.getQuantidadeAtual(), evento.getStatus());
    }

}
