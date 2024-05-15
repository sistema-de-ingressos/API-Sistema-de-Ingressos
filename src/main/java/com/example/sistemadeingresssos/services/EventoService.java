package com.example.sistemadeingresssos.services;

import com.example.sistemadeingresssos.entities.Evento;
import com.example.sistemadeingresssos.repositories.EventoRepository;
import com.example.sistemadeingresssos.rest.dtos.SalvarEventoDTO;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {

    private EventoRepository repository;
//    private EventoImagemRepository imagemRepository;

    public EventoService(EventoRepository repository) {
        this.repository = repository;
    }

    public List<Evento> findAll(){
        return repository.findAll();
    }

    public Evento findById(Integer id){
        Evento evento = repository.getReferenceById(id);
        return evento;
    }

    public Evento save(SalvarEventoDTO eventoDTO){
        return repository.save(new Evento(eventoDTO));
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public List<Evento> findAllByFiltro(String filtro){
        Evento eventoExemplo = new Evento();
        eventoExemplo.setNome(filtro); // Preencha outros campos relevantes, se necessário

        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Evento> example = Example.of(eventoExemplo, matcher);
        return repository.findAll(example);
    }

//    public EventoImagem uploadImagem(MultipartFile multipartFile, Integer id){
//        Evento evento = repository.getReferenceById(id);
//        EventoImagem eventoImagem = new EventoImagem(multipartFile, evento);
//        return imagemRepository.save(eventoImagem);
//    }

}

