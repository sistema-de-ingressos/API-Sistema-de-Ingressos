package com.example.sistemadeingresssos.rest.controllers;

import com.example.sistemadeingresssos.entities.Cliente;
import com.example.sistemadeingresssos.repositories.ClienteRepository;
import com.example.sistemadeingresssos.rest.dtos.SalvarClienteDTO;
import com.example.sistemadeingresssos.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
@Tag(name = "Cadastrar cliente", description = "Endpoints que salvam e buscam os clientes no sistema")
public class ClienteController {

    private ClienteRepository repository;
    private ClienteService service;

    public ClienteController(ClienteRepository repository, ClienteService service) {
        this.service = service;
        this.repository = repository;
    }

    @PostMapping
    @Operation(summary = "Salva o cliente no sistema", tags = {"Cadastrar cliente"})
    public ResponseEntity salvarCliente(@RequestBody @Valid SalvarClienteDTO salvarClienteDTO) {
        SalvarClienteDTO salvarClienteDTO1 = service.saveDTO(salvarClienteDTO);
        return ResponseEntity.ok().body(salvarClienteDTO1);

    }

    // Retornar DTO
    @GetMapping
    @Operation(hidden = true)
    public ResponseEntity<List<Cliente>> listarClientes(){
        List<Cliente> clientes = service.findAll();
        return ResponseEntity.ok().body(clientes);
    }


    @DeleteMapping(value = "/{id}")
    @Operation(hidden = true)
    public ResponseEntity deletarCliente(@PathVariable Integer id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping(value = "/{cpf}")
    @Operation(summary = "Busca cliente pelo CPF", tags = {"Cadastrar cliente"})
    public ResponseEntity buscarClienteByCpf(@PathVariable String cpf){
        SalvarClienteDTO clienteBuscado = service.findByCpf(cpf);
        return ResponseEntity.ok().body(clienteBuscado);
    }
}
