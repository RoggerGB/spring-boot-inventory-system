package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.Ciudad;
import com.example.repository.CiudadRepository;

@Service
public class CiudadService {
	@Autowired
	private CiudadRepository ciudadRepository;
	public List<Ciudad> listarCiudades(){
		return ciudadRepository.findAll();
	}
	public int registrarC(Ciudad c) 
	{
		int existe = ciudadRepository.verificarExistencia(c.getNombre());
		if(existe==0) 
		{
			if(buscar(c.getNombre()).size()==0) 
			{
				
				ciudadRepository.save(c);

			}else 
			{
				existe=1;
			}
		}
		return existe;
	}
	public void eliminarCiudad(Long id) 
	{
		ciudadRepository.deleteById(id);
	}
	public Ciudad encontrarCiudad(Long c) 
	{
		return ciudadRepository.findById(c).get();
	}
	private List<Ciudad> buscar(String nombre) 
	{
		return ciudadRepository.findByNombreContainingIgnoreCase(nombre);
	}
}
