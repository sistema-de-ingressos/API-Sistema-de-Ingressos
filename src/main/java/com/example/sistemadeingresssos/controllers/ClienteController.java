package com.example.sistemadeingresssos.controllers;

import com.example.sistemadeingresssos.entities.Cliente;
import com.example.sistemadeingresssos.repositories.ClienteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    private ClienteRepository repository;

    public ClienteController(ClienteRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity salvarCliente(@RequestBody Cliente cliente){
        Cliente cliente_salvo = repository.save(cliente);
        return ResponseEntity.ok(cliente_salvo);
    }


}
