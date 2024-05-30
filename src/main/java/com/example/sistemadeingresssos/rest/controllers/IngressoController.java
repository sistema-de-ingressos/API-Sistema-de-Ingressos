package com.example.sistemadeingresssos.rest.controllers;

import com.example.sistemadeingresssos.entities.Ingresso;
import com.example.sistemadeingresssos.rest.dtos.CarrinhoIngressoDTO;
import com.example.sistemadeingresssos.rest.dtos.RetornarIngressoDTO;
import com.example.sistemadeingresssos.rest.dtos.RetornarIngressosClienteDTO;
import com.example.sistemadeingresssos.rest.dtos.SalvarIngressoDTO;
import com.example.sistemadeingresssos.services.QrCodeService;
import com.example.sistemadeingresssos.services.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/ingressos")
@Tag(name = "Compra de ingressos", description = "Endpoints que realizam a compra e busca de ingressos")
public class IngressoController {

    private TransacaoService service;
    private QrCodeService qrCodeService;

    public IngressoController(TransacaoService service, QrCodeService qrCodeService) {
        this.service = service;
        this.qrCodeService = qrCodeService;
    }

    @PostMapping
    @Operation(summary = "Salva o ingresso no sistema", tags = {"Compra de ingressos"})
    public ResponseEntity save(@RequestBody @Valid SalvarIngressoDTO salvarIngressoDTO){
        RetornarIngressoDTO ingresso = service.save(salvarIngressoDTO);
        return ResponseEntity.ok().body(ingresso);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(hidden = true)
    public ResponseEntity delete(@PathVariable UUID id){
        Ingresso ingresso = service.findIngressoByID(id);
        service.delete(ingresso);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    @Operation(hidden = true)
    public ResponseEntity findById(@PathVariable UUID id){
        RetornarIngressoDTO ingresso = new RetornarIngressoDTO(service.findIngressoByID(id));
        return ResponseEntity.ok(ingresso);
    }

    @GetMapping(value = "/busca/{cpf}")
    @Operation(summary = "Busca o ingresso através do CPF do cliente", tags = {"Compra de ingressos"})
    public ResponseEntity<RetornarIngressosClienteDTO> findByCpf(@PathVariable String cpf){
        RetornarIngressosClienteDTO lista = service.findIngressoByCpf(cpf);
        return ResponseEntity.ok(lista);
    }

    @GetMapping(value = "/qrcode/{idDoIngresso}", produces = MediaType.IMAGE_PNG_VALUE)
    @Operation(summary = "Retorna o QR Code do ingresso", tags = {"Compra de ingressos"})
    public ResponseEntity<byte[]> gerarQRCode(@PathVariable UUID idDoIngresso) {
        // Chamando o serviço para gerar o código QR com base no ID do ingresso
        byte[] qrCodeBytes = qrCodeService.gerarQRCodeBytesFromIngressoId(idDoIngresso);

        if (qrCodeBytes != null) {
            // Retornar a imagem QR code diretamente
            return ResponseEntity.ok().body(qrCodeBytes);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



}
