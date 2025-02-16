package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.IngresoMercaderia;
import com.example.repository.IngresoMercaderiaRepository;

@Service
public class IngresoMercaderiaService {
	@Autowired
	IngresoMercaderiaRepository ingresoMercaderiaRepository;
	public IngresoMercaderia registrar(IngresoMercaderia mercaderia) 
	{
		return ingresoMercaderiaRepository.save(mercaderia);
	}
	public IngresoMercaderia buscarIngresoMercaderia(Long id)
	{
		return ingresoMercaderiaRepository.findById(id).get();
	}
	public List<IngresoMercaderia> listaMercaderias()
	{
		return ingresoMercaderiaRepository.findAll();
	}
}
