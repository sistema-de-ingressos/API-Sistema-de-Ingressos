package com.example.sistemadeingresssos.rest.dtos;

import com.example.sistemadeingresssos.entities.Evento;
import com.example.sistemadeingresssos.entities.EventoImagem;
import com.example.sistemadeingresssos.enums.EventoStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

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
        @JsonFormat(pattern = "HH:mm")
        LocalTime horario,

        @NotEmpty(message = "Campo local é obrigatório")
        String local,

        @NotNull(message = "Campo lote atual é obrigatório")
        Integer loteAtual,

        @NotNull(message = "Campo valor atual é obrigatório")
        Double valorAtual,

        @NotNull(message = "Campo status é obrigatório")
        @Enumerated(EnumType.STRING)
        EventoStatus status,

        List<byte[]> imagens
) {
    public DetalheEventoDTO(Evento evento) {
        this(evento.getId(), evento.getNome(), evento.getDescricao(),
                evento.getData(), evento.getHorario(), evento.getLocal(),
                evento.getLoteAtual(), evento.getValorAtual(),
                evento.getStatus(),getBase64Images(evento.getImagens()));
    }

    private static List<byte[]> getBase64Images(List<EventoImagem> imagens) {
        return imagens.stream()
                .map(imagem -> (imagem == null) ? null : imagem.getBase64())
                .collect(Collectors.toList());
    }

}

