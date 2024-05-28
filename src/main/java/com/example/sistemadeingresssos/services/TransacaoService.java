package com.example.sistemadeingresssos.services;

import com.example.sistemadeingresssos.entities.Cliente;
import com.example.sistemadeingresssos.entities.Evento;
import com.example.sistemadeingresssos.entities.Ingresso;
import com.example.sistemadeingresssos.repositories.IngressoRepository;
import com.example.sistemadeingresssos.rest.dtos.CarrinhoIngressoDTO;
import com.example.sistemadeingresssos.rest.dtos.RetornarIngressoDTO;
import com.example.sistemadeingresssos.rest.dtos.RetornarIngressosClienteDTO;
import com.example.sistemadeingresssos.rest.dtos.SalvarIngressoDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransacaoService {

    private IngressoRepository repository;
    private ClienteService clienteService;
    private EventoService eventoService;
    private QrCodeService qrCodeService;

    public TransacaoService(IngressoRepository repository, ClienteService service, EventoService eventoService, QrCodeService qrCodeService) {
        this.repository = repository;
        this.clienteService = service;
        this.eventoService = eventoService;
        this.qrCodeService = qrCodeService;
    }

    public RetornarIngressoDTO save(SalvarIngressoDTO salvarIngressoDTO){
        SalvarIngressoDTO ingressoDTO = salvarIngressoDTO;
        Evento evento = eventoService.findById(salvarIngressoDTO.idEvento());

        Cliente cliente = new Cliente(ingressoDTO);

        if (clienteService.existsByCPF(cliente.getCpf())) {
            cliente = clienteService.findClienteByCpf(cliente.getCpf());
            clienteService.save(cliente);
        }

        Ingresso ingresso = new Ingresso(ingressoDTO, cliente, evento);
        repository.save(ingresso);

        String qrCodeUrl = qrCodeService.getUrlLink(ingresso.getId());
        ingresso.setUrl(qrCodeUrl);
        repository.save(ingresso);

        return new RetornarIngressoDTO(ingresso);
    }

    public void delete(Ingresso ingresso){
        repository.delete(ingresso);
    }

    public Ingresso findIngressoByID(UUID id){
        Ingresso ingresso = repository.getReferenceById(id);

        return ingresso;
    }

    public RetornarIngressosClienteDTO findIngressoByCpf(String cpf){
        Cliente cliente = clienteService.findClienteByCpf(cpf);

        return new RetornarIngressosClienteDTO(cliente);
    }

    public List<Ingresso> listarIngressos(){
        return repository.findAll();
    }

    public CarrinhoIngressoDTO carrinho(Integer idDoEvento){
        Evento evento = eventoService.findById(idDoEvento);
        return new CarrinhoIngressoDTO(evento);
    }


}
