package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.SalidaMercaderia;
import com.example.repository.SalidaMercaderiaRepository;

@Service
public class SalidaMercaderiaService {
	@Autowired
	SalidaMercaderiaRepository salidaMercaderiaRepository;
	public SalidaMercaderia registrar(SalidaMercaderia mercaderia) 
	{
		return salidaMercaderiaRepository.save(mercaderia);
	}
	public SalidaMercaderia buscarSalidaMercaderia(Long id)
	{
		return salidaMercaderiaRepository.findById(id).get();
	}
	public List<SalidaMercaderia> listaMercaderias()
	{
		return salidaMercaderiaRepository.findAll();
	}
}
