package com.example.sistemadeingresssos.services;

import com.example.sistemadeingresssos.entities.Cliente;
import com.example.sistemadeingresssos.entities.Endereco;
import com.example.sistemadeingresssos.entities.Evento;
import com.example.sistemadeingresssos.entities.Ingresso;
import com.example.sistemadeingresssos.repositories.IngressoRepository;
import com.example.sistemadeingresssos.rest.dtos.CarrinhoIngressoDTO;
import com.example.sistemadeingresssos.rest.dtos.RetornarIngressoDTO;
import com.example.sistemadeingresssos.rest.dtos.SalvarClienteDTO;
import com.example.sistemadeingresssos.rest.dtos.SalvarIngressoDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TrasacaoService {

    private IngressoRepository repository;
    private ClienteService service;
    private EventoService eventoService;

    public TrasacaoService(IngressoRepository repository, ClienteService service, EventoService eventoService) {
        this.repository = repository;
        this.service = service;
        this.eventoService = eventoService;
    }

    public SalvarIngressoDTO save(SalvarIngressoDTO salvarIngressoDTO, Cliente cliente1){
        Evento evento = eventoService.findById(salvarIngressoDTO.idEvento());

        Cliente cliente = service.findClienteByCpf(salvarIngressoDTO.cpf());
        if(!cliente.getCpf().equals(cliente1.getCpf())){
            service.save(new SalvarClienteDTO(cliente1));
        }

        Ingresso ingresso = new Ingresso(salvarIngressoDTO, cliente, evento);
        Ingresso ingressoSalvo = repository.save(ingresso);
        setTaxa(ingressoSalvo, evento);
        SalvarIngressoDTO ingressoSalvoDTO = new SalvarIngressoDTO(ingresso, cliente, cliente.getEndereco());
        updateIngresso(ingressoSalvoDTO.id(), ingressoSalvoDTO);
        service.addIngresso(ingressoSalvo, cliente);
        service.updateListaIngresso(cliente.getId(), cliente);

        return ingressoSalvoDTO;
    }

    public SalvarIngressoDTO updateIngresso(Integer ingressoId, SalvarIngressoDTO novoIngressoDTO) {
        Ingresso ingressoExistente = findIngressoByID(ingressoId);

        ingressoExistente.setTaxa(novoIngressoDTO.taxa());
        ingressoExistente.setTotal(novoIngressoDTO.total());

        Ingresso ingressoAtualizado = repository.save(ingressoExistente);

        return new SalvarIngressoDTO(ingressoAtualizado, ingressoAtualizado.getCliente(), ingressoAtualizado.getCliente().getEndereco());
    }

    public void delete(Ingresso ingresso){
        repository.delete(ingresso);
    }

    public Ingresso findIngressoByID(Integer id){
        Optional<Ingresso> ingresso = repository.findById(id);
        return ingresso.get();
    }

    public SalvarIngressoDTO findIngressoDTOByID(Integer id){
        Optional<SalvarIngressoDTO> ingresso = repository.findById(id).map(SalvarIngressoDTO::new);
        return ingresso.get();
    }

    public List<RetornarIngressoDTO> findIngressoByCpf(String cpf){
        Cliente cliente = service.findClienteByCpf(cpf);
        List<Ingresso> lista = cliente.getIngressos();
        List<RetornarIngressoDTO> listaRetornada = new ArrayList<>();

        for(Ingresso ingresso : lista){
            Evento evento = eventoService.findById(ingresso.getEvento().getId());
            RetornarIngressoDTO ingressoDTO = new RetornarIngressoDTO(evento.getNome(), evento.getData(),evento.getValorAtual());
            listaRetornada.add(ingressoDTO);
        }

        return listaRetornada;
    }

    public List<Ingresso> listarIngressos(){
        return repository.findAll();
    }

    public void setTaxa(Ingresso ingresso, Evento evento){
        Double taxa = evento.getValorAtual() * 0.1;

        ingresso.setTaxa(taxa);
        ingresso.setTotal(evento.getValorAtual() + taxa);
    }

    public CarrinhoIngressoDTO carrinho(Integer idDoIngresso){
        Ingresso ingresso = findIngressoByID(idDoIngresso);
        Evento evento = eventoService.findById(ingresso.getEvento().getId());
        CarrinhoIngressoDTO carrinhoIngressoDTO = new CarrinhoIngressoDTO(evento.getNome(), evento.getValorAtual(), ingresso.getTaxa(), ingresso.getTotal());
        return carrinhoIngressoDTO;
    }


}
