package com.example.sistemadeingresssos.initializers;

import com.example.sistemadeingresssos.entities.Evento;
import com.example.sistemadeingresssos.enums.EventoStatus;
import com.example.sistemadeingresssos.rest.dtos.SalvarEventoDTO;
import com.example.sistemadeingresssos.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
@Profile({"teste"})
public class DataLoader implements CommandLineRunner {
    @Autowired
    EventoService eventoService;
    @Override
    public void run(String... args) throws Exception {
        for (int i = 1; i <= 15; i++) {
            Evento evento;
            if (i <= 8) {
                evento = new Evento("Evento " + i, "Descrição do evento " + i, LocalDate.now(), LocalTime.now(), "Local do evento " + i, 100, 50.0, 200, 150, EventoStatus.QUASE_LOTADO);
            } else {
                evento = new Evento("Evento " + i, "Descrição do evento " + i, LocalDate.now(), LocalTime.now(), "Local do evento " + i, 100, 50.0, 200, 150, EventoStatus.DISPONIVEL);
            }
            eventoService.save(new SalvarEventoDTO(evento));
        }
    }
}
