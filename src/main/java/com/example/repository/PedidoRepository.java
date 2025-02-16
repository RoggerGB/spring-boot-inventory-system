package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	Pedido findByCorrelativoContainingIgnoreCase(String correlativo);
	@Query("SELECT count(p) FROM Pedido p  WHERE UPPER(p.correlativo)=UPPER(?1)")
	int verificarExistencia(String correlativo);
	

}
