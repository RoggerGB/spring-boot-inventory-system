package com.example.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entities.Ciudad;


public interface CiudadRepository extends JpaRepository<Ciudad, Long>{
	@Query("SELECT count(c) FROM Ciudad c  WHERE c.nombre=?1")
	int verificarExistencia(String nombre);
	List<Ciudad> findByNombreContainingIgnoreCase(String nombre);

}
