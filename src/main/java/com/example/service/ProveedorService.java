package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.Proveedor;
import com.example.repository.ProveedorRepository;

@Service
public class ProveedorService {
	@Autowired
	ProveedorRepository proveedorRepository;
	public List<Proveedor> listaP()
	{
		return proveedorRepository.findAll();
	}
	public int registraProveedor(Proveedor p) 
	{
		int existe = proveedorRepository.verificarExistencia(p.getRuc());
		if(existe==0) 
		{
			if(proveedorRepository.findByRucContainingIgnoreCase(p.getRuc()).size()==0) 
			{
				proveedorRepository.save(p);

			}else 
			{
				existe=1;
			}
		}
		return existe;
	}
	public void eliminarProveedor(Long id) 
	{
		proveedorRepository.deleteById(id);
	}
	public Proveedor buscarProveedor(Long id) 
	{
		return proveedorRepository.findById(id).get();
	}
	public Proveedor actualizar(Proveedor p) 
	{
		return proveedorRepository.save(p);
	}
	public Proveedor registrarProv(Proveedor p) 
	{
		proveedorRepository.save(p);
		return p;
	}
}
