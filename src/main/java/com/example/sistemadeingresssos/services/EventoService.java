package com.example.sistemadeingresssos.services;

import com.example.sistemadeingresssos.entities.Evento;
import com.example.sistemadeingresssos.enums.EventoStatus;
import com.example.sistemadeingresssos.exceptions.EventoException;
import com.example.sistemadeingresssos.repositories.EventoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    private EventoRepository repository;

    public List<Evento> findAll(){
        return repository.findAll();
    }

    public Evento findById(Integer id){
        Optional<Evento> evento = repository.findById(id);
        return evento.get();
    }

    public Evento insert(Evento evento){
        return repository.save(evento);
    }
}

