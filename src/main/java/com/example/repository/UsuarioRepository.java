package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entities.Usuario;



public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	@Query("FROM Usuario u  WHERE u.dni=?1")
	 Usuario findByDni(String dni);
}
