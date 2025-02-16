package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.*;

public interface RolRepository extends JpaRepository<Rol, Long> {
	List<Rol> findByAuthority(String authority);
}
