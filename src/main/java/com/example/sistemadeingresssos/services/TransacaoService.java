package com.example.sistemadeingresssos.services;

import com.example.sistemadeingresssos.entities.Cliente;
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
public class TransacaoService {

    private IngressoRepository repository;
    private ClienteService clienteService;
    private EventoService eventoService;

    public TransacaoService(IngressoRepository repository, ClienteService service, EventoService eventoService) {
        this.repository = repository;
        this.clienteService = service;
        this.eventoService = eventoService;
    }

    public RetornarIngressoDTO save(SalvarIngressoDTO salvarIngressoDTO){
        Evento evento = eventoService.findById(salvarIngressoDTO.idEvento());

        Cliente cliente = new Cliente(salvarIngressoDTO);

        if (clienteService.existsByCPF(cliente.getCpf())) {
            cliente = clienteService.findClienteByCpf(cliente.getCpf());
            clienteService.save(new SalvarClienteDTO(cliente));
        }

        Ingresso ingresso = new Ingresso(salvarIngressoDTO, cliente, evento);
        repository.save(ingresso);

        return new RetornarIngressoDTO(ingresso);
    }

    public void delete(Ingresso ingresso){
        repository.delete(ingresso);
    }

    public Ingresso findIngressoByID(Integer id){
        Ingresso ingresso = repository.getReferenceById(id);
        return ingresso;
    }

    public List<RetornarIngressoDTO> findIngressoByCpf(String cpf){
        Cliente cliente = clienteService.findClienteByCpf(cpf);
        return cliente.getIngressos().stream().map(RetornarIngressoDTO::new).toList();
    }

    public List<Ingresso> listarIngressos(){
        return repository.findAll();
    }

    public CarrinhoIngressoDTO carrinho(Integer idDoIngresso){
        Ingresso ingresso = findIngressoByID(idDoIngresso);
        return new CarrinhoIngressoDTO(ingresso);
    }


}
