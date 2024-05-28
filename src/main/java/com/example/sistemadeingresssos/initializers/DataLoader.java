package com.example.sistemadeingresssos.initializers;

import com.example.sistemadeingresssos.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"teste"})
public class DataLoader implements CommandLineRunner {
    @Autowired
    EventoService eventoService;
    @Override
    public void run(String... args) throws Exception {
//        for (int i = 1; i <= 15; i++) {
//            Evento evento;
//            if (i <= 8) {
//                evento = new Evento("Evento " + i, "Descrição do evento " + i, LocalDate.now(), LocalTime.now(), "Local do evento " + i, 100, 50.0, 200, 150, EventoStatus.QUASE_LOTADO);
//            } else {
//                evento = new Evento("Evento " + i, "Descrição do evento " + i, LocalDate.now(), LocalTime.now(), "Local do evento " + i, 100, 50.0, 200, 150, EventoStatus.DISPONIVEL);
//            }
//            eventoService.save(evento);
//        }
    }
}
