package com.example.sistemadeingresssos.rest.controllers;

import com.example.sistemadeingresssos.rest.dtos.SalvarClienteDTO;
import com.example.sistemadeingresssos.services.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/salvar/cpf")
public class CpfController {

    private ClienteService service;

    @GetMapping(value = "/{cpf}")
    public ResponseEntity buscarCpf(@PathVariable String cpf){
        SalvarClienteDTO clienteProcurado = service.findByCpf(cpf);
        return ResponseEntity.ok().body(clienteProcurado);
    }
}
