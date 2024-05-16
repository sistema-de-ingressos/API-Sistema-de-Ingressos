package com.example.sistemadeingresssos.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventoImagem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @NotNull(message = "Evento é obrigatório")
    private Evento evento;

    @NotEmpty(message = "Imagem deve possuir dado")
    @Lob
    private byte[] base64;

    @NotEmpty(message = "Imagem deve possuir tipo")
    private String contentType;


    public EventoImagem(MultipartFile file, Evento evento) {
        try {
            this.base64 = file.getBytes();
            this.contentType = file.getContentType();
            this.setEvento(evento);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
        evento.setImagemPrincipal(this);
    }
}
