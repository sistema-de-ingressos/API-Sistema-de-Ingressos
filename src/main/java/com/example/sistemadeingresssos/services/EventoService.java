package com.example.sistemadeingresssos.services;

import com.example.sistemadeingresssos.entities.Evento;
import com.example.sistemadeingresssos.entities.EventoImagem;
import com.example.sistemadeingresssos.enums.EventoStatus;
import com.example.sistemadeingresssos.repositories.EventoImagemRepository;
import com.example.sistemadeingresssos.repositories.EventoRepository;
import com.example.sistemadeingresssos.rest.dtos.CarrinhoIngressoDTO;
import com.example.sistemadeingresssos.rest.dtos.ListagemEventoDTO;
import com.example.sistemadeingresssos.rest.dtos.SalvarEventoDTO;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventoService {

    private EventoRepository repository;
    private EventoImagemRepository imagemRepository;

    public EventoService(EventoRepository repository, EventoImagemRepository eventoImagemRepository) {
        this.repository = repository;
        this.imagemRepository = eventoImagemRepository;
    }

    public List<Evento> findAll(){
        return repository.findAll();
    }

    public Evento findById(Integer id){
        return repository.getReferenceById(id);
    }

    public Evento save(SalvarEventoDTO eventoDTO){
        return repository.save(new Evento(eventoDTO));
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public List<Evento> findAllByFiltro(String filtro){
        if (filtro == null || filtro.isEmpty()) {
            return findAll();
        } else {
            Evento eventoExemplo = new Evento();
            eventoExemplo.setNome(filtro);

            ExampleMatcher matcher = ExampleMatcher
                    .matching()
                    .withIgnoreCase()
                    .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

            Example<Evento> example = Example.of(eventoExemplo, matcher);
            return repository.findAll(example);
        }
    }

    public List<ListagemEventoDTO> buscarEventosFuturos() {
        LocalDate dataAtual = LocalDate.now();
        List<ListagemEventoDTO> list = findAll()
                .stream()
                .filter(evento -> evento.getData().isAfter(dataAtual))
                .sorted(Comparator.comparing(Evento::getData))
                .map(ListagemEventoDTO::new)// Ordena pela data em ordem crescente
                .collect(Collectors.toList());
        return list;
    }


    public List<ListagemEventoDTO> buscarEventosQuaseLotados() {
        List<ListagemEventoDTO> list = findAll()
                                    .stream()
                                    .filter(evento -> evento.getStatus() == EventoStatus.QUASE_LOTADO)
                                    .map(ListagemEventoDTO::new)
                                    .collect(Collectors.toList());
        return list;
    }

    public EventoImagem uploadImagem(MultipartFile multipartFile, Integer id){
        Evento evento = repository.getReferenceById(id);
        EventoImagem eventoImagem = new EventoImagem(multipartFile, evento);
        evento.setImagens(eventoImagem);
        evento.setImagemPrincipal(evento.getImagens().getFirst());

        repository.save(evento);
        return imagemRepository.save(eventoImagem);
    }

    public CarrinhoIngressoDTO carrinho(Integer idDoEvento){
        Evento evento = findById(idDoEvento);
        return new CarrinhoIngressoDTO(evento);
    }

}

