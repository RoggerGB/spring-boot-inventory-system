package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entities.Categoria;
import com.example.entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
	@Query("SELECT count(p) FROM Producto p  WHERE p.sku=?1")
	int verificarExistencia(String sku);
	List<Producto> findBySkuContainingIgnoreCase(String sku);
	List<Producto> findByEstado(int e);
}
