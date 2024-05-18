package com.example.sistemadeingresssos.rest.controllers;

import com.example.sistemadeingresssos.entities.Evento;
import com.example.sistemadeingresssos.entities.EventoImagem;
import com.example.sistemadeingresssos.enums.EventoStatus;
import com.example.sistemadeingresssos.repositories.EventoRepository;
import com.example.sistemadeingresssos.rest.dtos.DetalheEventoDTO;
import com.example.sistemadeingresssos.rest.dtos.ListagemEventoDTO;
import com.example.sistemadeingresssos.rest.dtos.SalvarEventoDTO;
import com.example.sistemadeingresssos.services.EventoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/eventos")
@Tag(name = "Criação e listagem de eventos", description = "Endpoints que realizam a criação de eventos, listagem e busca dos mesmos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EventoController {

    private final EventoService service;

    public EventoController(EventoService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Salva o evento no sistema", tags = {"Criação e listagem de eventos"})
    public ResponseEntity criarEvento(@RequestBody @Valid SalvarEventoDTO evento) {
        Evento eventoSalvo = service.save(evento);
        return ResponseEntity.ok().body(new ListagemEventoDTO(eventoSalvo));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deleta o evento do sistema", tags = {"Criação e listagem de eventos"})
    public ResponseEntity deletarEvento(@PathVariable Integer id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "Lista os eventos disponíveis recentes", tags = {"Criação e listagem de eventos"})
    public ResponseEntity<List<ListagemEventoDTO>> listarEventosRecentes() {
        LocalDate dataAtual = LocalDate.now();

        List<ListagemEventoDTO> eventosFuturos = service.findAll().stream()
                .filter(evento -> evento.getData().isAfter(dataAtual)) // Filtra eventos futuros
                .map(ListagemEventoDTO::new)
                .sorted(Comparator.comparing(ListagemEventoDTO::data))
                .collect(Collectors.toList());

        return ResponseEntity.ok(eventosFuturos);
    }

    @GetMapping("/quaseLotados")
    @Operation(summary = "Lista os eventos quase lotados", tags = {"Criação e listagem de eventos"})
    public ResponseEntity<List<ListagemEventoDTO>> listarEventosQuaseLotados() {
        List<ListagemEventoDTO> eventos = service.findAll().stream()
                .filter(evento -> evento.getStatus() == EventoStatus.QUASE_LOTADO)
                .map(ListagemEventoDTO::new)
                .toList();
        return ResponseEntity.ok(eventos);
    }


    @GetMapping(value = "/{id}")
    @Operation(hidden = true)
    public ResponseEntity buscarById(@PathVariable Integer id) {
        Evento evento = service.findById(id);
        return ResponseEntity.ok().body(new DetalheEventoDTO(evento));
    }

    @GetMapping(value = "/buscar/{filtro}")
    @Operation(summary = "Busca evento pelo nome", tags = {"Criação e listagem de eventos"})
    public ResponseEntity buscarEventos(@PathVariable String filtro) {
        List<ListagemEventoDTO> list = service.findAllByFiltro(filtro).stream().map(ListagemEventoDTO::new).toList();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping(value = "/{id}/upload-imagem")
    @Operation(summary = "Faz upload de imagem do evento", tags = {"Criação e listagem de eventos"})
    public ResponseEntity<ListagemEventoDTO> uploadEventoImagem(@RequestParam("imagem") MultipartFile multipartFile, @PathVariable Integer id) {
        if (!isImageFile(multipartFile)) {
            // Se não for uma imagem válida, retorne uma resposta de erro (status 400 Bad Request)
            return ResponseEntity.badRequest().build();
        }

        EventoImagem eventoImagem = service.uploadImagem(multipartFile, id);
        return ResponseEntity.ok(new ListagemEventoDTO(eventoImagem.getEvento()));
    }

    private boolean isImageFile(MultipartFile file) {
        String filename = file.getOriginalFilename();
        return filename != null && (filename.endsWith(".jpg") || filename.endsWith(".jpeg") || filename.endsWith(".png"));
    }

}
