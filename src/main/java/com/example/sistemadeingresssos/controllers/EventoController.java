package com.example.sistemadeingresssos.controllers;

import com.example.sistemadeingresssos.entities.Evento;
import com.example.sistemadeingresssos.exceptions.EventoException;
import com.example.sistemadeingresssos.repositories.EventoRepository;
import com.example.sistemadeingresssos.services.EventoService;
import lombok.SneakyThrows;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/eventos")
public class EventoController {

    private EventoService service;
    private EventoRepository repository;

    public EventoController(EventoService service, EventoRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity save(@RequestBody Evento evento) {
        service.insert(evento);
        evento.setQuantidadeAtual(evento.getQuantidadeAtual() + 1);
        return ResponseEntity.ok().body(evento);
    }

    @GetMapping
    public ResponseEntity listarEventos() {
        List<Evento> eventos = service.findAll();
        return ResponseEntity.ok().body(eventos);
    }

    @GetMapping
    public ResponseEntity buscarEventos(Evento filtro) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filtro, matcher);
        List<Evento> list = repository.findAll(example);
        return ResponseEntity.ok().body(list);
    }

}
