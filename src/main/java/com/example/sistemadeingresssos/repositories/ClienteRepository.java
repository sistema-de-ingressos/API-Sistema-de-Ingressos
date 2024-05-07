package com.example.sistemadeingresssos.repositories;

import com.example.sistemadeingresssos.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
}
