package com.example.sistemadeingresssos.rest.dtos;

import com.example.sistemadeingresssos.entities.Evento;
import com.example.sistemadeingresssos.entities.Ingresso;
import com.fasterxml.jackson.annotation.JsonFormat;
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
        Integer quantidadeAtual

) {
    public SalvarEventoDTO(Evento evento) {
        this(evento.getNome(), evento.getDescricao(), evento.getData(),
                evento.getHorario(), evento.getLocal(), evento.getLoteAtual(),
                evento.getValorAtual(), evento.getQuantidadeMax(), evento.getQuantidadeAtual());
    }

}
