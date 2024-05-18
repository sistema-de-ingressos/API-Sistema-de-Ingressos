package com.example.sistemadeingresssos.repositories;

import com.example.sistemadeingresssos.entities.Ingresso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IngressoRepository extends JpaRepository<Ingresso, UUID> {
    Optional<Ingresso> findById(UUID id);
}

