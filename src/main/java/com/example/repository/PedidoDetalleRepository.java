package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entities.PedidoDetalle;

public interface PedidoDetalleRepository extends JpaRepository<PedidoDetalle, Long>{
	@Query("FROM PedidoDetalle o  WHERE o.pedido.id=?1")
	 List<PedidoDetalle> findByPedido(Long id);
}
