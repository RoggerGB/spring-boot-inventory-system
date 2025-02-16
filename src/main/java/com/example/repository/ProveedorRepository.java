package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entities.Categoria;
import com.example.entities.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long>{
	@Query("SELECT count(p) FROM Proveedor p  WHERE p.ruc=?1")
	int verificarExistencia(String ruc);
	List<Proveedor> findByRucContainingIgnoreCase(String ruc);

}
