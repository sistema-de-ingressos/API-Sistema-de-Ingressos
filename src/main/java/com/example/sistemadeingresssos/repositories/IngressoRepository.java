package com.example.sistemadeingresssos.repositories;

import com.example.sistemadeingresssos.entities.Ingresso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngressoRepository extends JpaRepository<Ingresso, Long> {
}
