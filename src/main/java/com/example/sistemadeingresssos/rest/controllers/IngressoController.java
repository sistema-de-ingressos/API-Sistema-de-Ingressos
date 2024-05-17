package com.example.sistemadeingresssos.rest.controllers;

import com.example.sistemadeingresssos.entities.Cliente;
import com.example.sistemadeingresssos.entities.Ingresso;
import com.example.sistemadeingresssos.rest.dtos.CarrinhoIngressoDTO;
import com.example.sistemadeingresssos.rest.dtos.RetornarIngressoDTO;
import com.example.sistemadeingresssos.rest.dtos.SalvarIngressoDTO;
import com.example.sistemadeingresssos.services.EventoService;
import com.example.sistemadeingresssos.services.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/ingressos")
@Tag(name = "Compra de ingressos", description = "Endpoints que realizam a compra e busca de ingressos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class IngressoController {

    private TransacaoService service;

    public IngressoController(TransacaoService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Salva o ingresso no sistema", tags = {"Compra de ingressos"})
    public ResponseEntity save(@RequestBody @Valid SalvarIngressoDTO salvarIngressoDTO){
        RetornarIngressoDTO ingresso = service.save(salvarIngressoDTO);
        return ResponseEntity.ok().body(ingresso);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(hidden = true)
    public ResponseEntity delete(@PathVariable Integer id){
        Ingresso ingresso = service.findIngressoByID(id);
        service.delete(ingresso);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    @Operation(hidden = true)
    public ResponseEntity findById(@PathVariable Integer id){
        RetornarIngressoDTO ingresso = new RetornarIngressoDTO(service.findIngressoByID(id));
        return ResponseEntity.ok(ingresso);
    }

    @GetMapping(value = "/busca/{cpf}")
    @Operation(summary = "Busca o ingresso através do CPF do cliente", tags = {"Compra de ingressos"})
    public ResponseEntity<List<RetornarIngressoDTO>> findByCpf(@PathVariable String cpf){
        List<RetornarIngressoDTO> lista = service.findIngressoByCpf(cpf);
        return ResponseEntity.ok(lista);
    }

    @GetMapping(value = "/carrinho/{idDoIngresso}")
    @Operation(summary = "Retorna o carrinho de compra", tags = {"Compra de ingressos"})
    public ResponseEntity<CarrinhoIngressoDTO> carrinho(@PathVariable Integer idDoIngresso){
        CarrinhoIngressoDTO carrinhoIngressoDTO = service.carrinho(idDoIngresso);
        return ResponseEntity.ok(carrinhoIngressoDTO);
    }

}
