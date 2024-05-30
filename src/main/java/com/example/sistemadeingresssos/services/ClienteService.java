package com.example.sistemadeingresssos.services;

import com.example.sistemadeingresssos.entities.Cliente;
import com.example.sistemadeingresssos.repositories.ClienteRepository;
import com.example.sistemadeingresssos.rest.dtos.RetornarClienteDTO;
import com.example.sistemadeingresssos.rest.dtos.SalvarClienteDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public SalvarClienteDTO saveDTO(SalvarClienteDTO salvarClienteDTO) {
        Cliente cliente = new Cliente(salvarClienteDTO);
        Cliente clienteSalvo = repository.save(cliente);
        SalvarClienteDTO clienteSalvoDTO = new SalvarClienteDTO(clienteSalvo);

        return clienteSalvoDTO;
    }

    public SalvarClienteDTO save(Cliente cliente) {
        Cliente clienteSalvo = repository.save(cliente);
        SalvarClienteDTO clienteSalvoDTO = new SalvarClienteDTO(clienteSalvo);

        return clienteSalvoDTO;
    }

    public Cliente updateListaIngresso(Integer clienteId, Cliente clienteAtualizado) {
        Cliente clienteExistente = findByIdIngresso(clienteId);
        clienteExistente.setIngressos(clienteAtualizado.getIngressos());
        Cliente clienteAtualizado1 = repository.save(clienteExistente);

        return clienteAtualizado1;
    }

    public List<Cliente> findAll(){
        return repository.findAll();
    }

    public SalvarClienteDTO findById(Integer id){
        Optional<Cliente> clienteOptional = repository.findById(id);

        return clienteOptional.map(SalvarClienteDTO::new)
                .orElse(null);
    }

    public SalvarClienteDTO findByCpf(String cpf){
        Optional<Cliente> clienteOptional = repository.findByCpf(cpf);

        return clienteOptional.map(cliente -> new SalvarClienteDTO(cliente))
                .orElse(null);
    }

    public RetornarClienteDTO returnByCpf(String cpf){
        Optional<Cliente> clienteOptional = repository.findByCpf(cpf);

        return clienteOptional.map(cliente -> new RetornarClienteDTO(clienteOptional.get()))
                .orElse(null);
    }

    public Cliente findClienteByCpf(String cpf){
        Optional<Cliente> clienteOptional = repository.findByCpf(cpf);

        return clienteOptional.get();
    }

    public Cliente findByIdIngresso(Integer id){
        Optional<Cliente> cliente = repository.findById(id);

        return cliente.orElse(null);
    }

    public Boolean existsByCPF(String cpf) {
        return repository.existsByCpf(cpf);
    }

}

