package com.example.sistemadeingresssos.repositories;

import com.example.sistemadeingresssos.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
