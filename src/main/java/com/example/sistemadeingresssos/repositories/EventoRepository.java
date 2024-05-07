package com.example.sistemadeingresssos.repositories;

import com.example.sistemadeingresssos.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> {
}
