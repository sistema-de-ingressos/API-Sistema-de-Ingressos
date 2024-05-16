package com.example.sistemadeingresssos.rest.controllers;

import com.example.sistemadeingresssos.entities.Evento;
import com.example.sistemadeingresssos.entities.EventoImagem;
import com.example.sistemadeingresssos.repositories.EventoRepository;
import com.example.sistemadeingresssos.rest.dtos.DetalheEventoDTO;
import com.example.sistemadeingresssos.rest.dtos.ListagemEventoDTO;
import com.example.sistemadeingresssos.rest.dtos.SalvarEventoDTO;
import com.example.sistemadeingresssos.services.EventoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/eventos")
public class EventoController {

    private final EventoService service;

    public EventoController(EventoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity criarEvento(@RequestBody @Valid SalvarEventoDTO evento) {
        Evento eventoSalvo = service.save(evento);
        return ResponseEntity.ok().body(new ListagemEventoDTO(eventoSalvo));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deletarEvento(@PathVariable Integer id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ListagemEventoDTO>> listarEventos() {
        List<ListagemEventoDTO> eventos = service.findAll().stream().map(ListagemEventoDTO::new).toList();
        return ResponseEntity.ok(eventos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity buscarById(@PathVariable Integer id) {
        Evento evento = service.findById(id);
        return ResponseEntity.ok().body(new DetalheEventoDTO(evento));
    }

    @GetMapping(value = "/buscar/{filtro}")
    public ResponseEntity buscarEventos(@PathVariable String filtro) {
        List<ListagemEventoDTO> list = service.findAllByFiltro(filtro).stream().map(ListagemEventoDTO::new).toList();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping(value = "/{id}/upload-imagem")
    public ResponseEntity<ListagemEventoDTO> uploadEventoImagem(@RequestParam("imagem") MultipartFile multipartFile, @PathVariable Integer id){
        EventoImagem eventoImagem = service.uploadImagem(multipartFile, id);
        return ResponseEntity.ok(new ListagemEventoDTO(eventoImagem.getEvento()));
    }

}
