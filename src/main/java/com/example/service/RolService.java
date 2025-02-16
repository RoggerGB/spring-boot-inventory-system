package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.Rol;
import com.example.repository.RolRepository;

@Service
public class RolService {
	@Autowired
	private RolRepository rolRepository;
	public List<Rol> listarRoles(){
		return rolRepository.findAll();
	}
	public List<Rol> listarStudent(String n) 
	{
		return rolRepository.findByAuthority(n);
	}
}
