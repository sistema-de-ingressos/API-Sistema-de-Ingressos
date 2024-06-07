package com.example.sistemadeingresssos.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "EVENTO_IMAGEM")
public class EventoImagem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @NotNull(message = "Evento é obrigatório")
    @JoinColumn(name = "evento_id")
    private Evento evento;

    @NotEmpty(message = "Imagem deve possuir dado")
    @Lob
    @Column(name = "imagem", columnDefinition = "LONGBLOB")
    private byte[] base64;

    @NotEmpty(message = "Imagem deve possuir tipo")
    @Column(name = "content_type")
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
