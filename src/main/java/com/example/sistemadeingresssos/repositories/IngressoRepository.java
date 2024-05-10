package com.example.sistemadeingresssos.repositories;

import com.example.sistemadeingresssos.entities.Ingresso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngressoRepository extends JpaRepository<Ingresso, Integer> {
}
