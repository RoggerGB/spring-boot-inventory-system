package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entities.Almacen;
import com.example.entities.Categoria;

public interface AlmacenRepository extends JpaRepository<Almacen, Long> {
	List<Almacen> findByEstado(int e);
	@Query("SELECT count(a) FROM Almacen a  WHERE a.codigo=?1")
	int verificarExistencia(String codigo);
}
