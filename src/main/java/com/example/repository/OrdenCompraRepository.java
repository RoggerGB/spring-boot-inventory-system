package com.example.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entities.OrdenCompra;
import com.example.entities.OrdenCompraDetalle;

public interface OrdenCompraRepository extends JpaRepository<OrdenCompra, Long>{
	OrdenCompra findByCodigoContainingIgnoreCase(String codigo);
	@Query("SELECT count(p) FROM OrdenCompra p  WHERE UPPER(p.codigo)=UPPER(?1)")
	int verificarExistencia(String codigo);
	@Query("FROM OrdenCompra p  WHERE p.estado=?1")
	 List<OrdenCompra> findByEstado(int num);
}
