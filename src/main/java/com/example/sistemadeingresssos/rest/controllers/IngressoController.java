package com.example.sistemadeingresssos.rest.controllers;

import com.example.sistemadeingresssos.entities.Cliente;
import com.example.sistemadeingresssos.entities.Ingresso;
import com.example.sistemadeingresssos.rest.dtos.CarrinhoIngressoDTO;
import com.example.sistemadeingresssos.rest.dtos.RetornarIngressoDTO;
import com.example.sistemadeingresssos.rest.dtos.SalvarIngressoDTO;
import com.example.sistemadeingresssos.services.EventoService;
import com.example.sistemadeingresssos.services.TrasacaoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/ingressos")
public class IngressoController {

    private TrasacaoService service;
    private EventoService eventoService;

    public IngressoController(TrasacaoService service, EventoService eventoService) {
        this.service = service;
        this.eventoService = eventoService;
    }

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid SalvarIngressoDTO salvarIngressoDTO, Cliente cliente){
        SalvarIngressoDTO ingresso = service.save(salvarIngressoDTO, cliente);
        return ResponseEntity.ok().body(ingresso);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        Ingresso ingresso = service.findIngressoByID(id);
        service.delete(ingresso);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable Integer id){
        SalvarIngressoDTO ingresso = service.findIngressoDTOByID(id);
        return ResponseEntity.ok(ingresso);
    }

    @GetMapping(value = "/busca/{cpf}")
    public ResponseEntity<List<RetornarIngressoDTO>> findByCpf(@PathVariable String cpf){
        List<RetornarIngressoDTO> lista = service.findIngressoByCpf(cpf);
        return ResponseEntity.ok(lista);
    }

    @GetMapping(value = "/carrinho/{idDoIngresso}")
    public ResponseEntity<CarrinhoIngressoDTO> carrinho(@PathVariable Integer idDoIngresso){
        CarrinhoIngressoDTO carrinhoIngressoDTO = service.carrinho(idDoIngresso);
        return ResponseEntity.ok(carrinhoIngressoDTO);
    }

}
