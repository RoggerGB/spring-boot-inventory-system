package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entities.OrdenCompraDetalle;

public interface OrdenCompraDetalleRepository extends JpaRepository<OrdenCompraDetalle, Long> {
	OrdenCompraDetalle findByOrdenCompra(Long id);
	@Query("FROM OrdenCompraDetalle o  WHERE o.ordenCompra.id=?1")
	 List<OrdenCompraDetalle> findByOrden(Long idOrden);
}
