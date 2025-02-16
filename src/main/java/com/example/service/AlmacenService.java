package com.example.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.Almacen;
import com.example.entities.Categoria;
import com.example.repository.AlmacenRepository;

@Service
public class AlmacenService {
	@Autowired
	AlmacenRepository almacenRepository;

	public List<Almacen> listarA() {
		return almacenRepository.findAll();
	}

	public String registrarA(Almacen a) {
		almacenRepository.save(a);
		return a.getCodigo();
	}
	public int registro(Almacen a)
	{
		int cantidad = almacenRepository.verificarExistencia(a.getCodigo());
		if(cantidad<=0) 
		{
			LocalDate localDate = LocalDate.now();
			a.setEstado(1);
			a.setFechaB(java.sql.Date.valueOf(localDate));
			a.setFechaC(java.sql.Date.valueOf(localDate));
			a.setFechaM(java.sql.Date.valueOf(localDate));
			almacenRepository.save(a);
			return 1;

		}else 
		{
			return 0;

		}
		
	}
	public void deleteA(Long a) 
	{
		almacenRepository.deleteById(a);
	}
	public Almacen encontrarAlmacen(Long id) 
	{
		return almacenRepository.findById(id).get();
	}
	public Almacen actualizarAlmacen(Almacen a) 
	{
		return almacenRepository.save(a);
	}
	public void dar_baja(Long id) 
	{		
		LocalDate localDate = LocalDate.now();

		Almacen nAlmacen = almacenRepository.findById(id).get();
		nAlmacen.setFechaB(java.sql.Date.valueOf(localDate));
		nAlmacen.setEstado(0);
		almacenRepository.save(nAlmacen);
	}
	public void activar(Long id) 
	{		

		Almacen nAlmacen = almacenRepository.findById(id).get();
		nAlmacen.setEstado(1);
		almacenRepository.save(nAlmacen);
	}
	public List<Almacen> activo()
	{
		return almacenRepository.findByEstado(1);
	}
	public List<Almacen> desactivo()
	{
		return almacenRepository.findByEstado(0);
	}
}
