package com.example.sistemadeingresssos.rest.controllers;

import com.example.sistemadeingresssos.entities.Cliente;
import com.example.sistemadeingresssos.repositories.ClienteRepository;
import com.example.sistemadeingresssos.repositories.EnderecoRepository;
import com.example.sistemadeingresssos.rest.dtos.SalvarClienteDTO;
import com.example.sistemadeingresssos.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    private ClienteRepository repository;
    private EnderecoRepository enderecoRepository;
    private ClienteService service;

    public ClienteController(ClienteRepository repository, ClienteService service) {
        this.service = service;
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity salvarCliente(@RequestBody @Valid SalvarClienteDTO salvarClienteDTO) {

        SalvarClienteDTO salvarClienteDTO1 = service.save(salvarClienteDTO);

        return ResponseEntity.ok().body(salvarClienteDTO1);

    }

    // Retornar DTO
    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes(){
        List<Cliente> clientes = service.findAll();
        return ResponseEntity.ok().body(clientes);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity deletarCliente(@PathVariable Integer id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping(value = "/{cpf}")
    public ResponseEntity buscarClienteByCpf(@PathVariable String cpf){
        SalvarClienteDTO clienteBuscado = service.findByCpf(cpf);
        return ResponseEntity.ok().body(clienteBuscado);
    }
}
