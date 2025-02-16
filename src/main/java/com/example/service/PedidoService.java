package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.Pedido;
import com.example.repository.PedidoRepository;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository pedidoRepository;
	public List<Pedido> listarPedidos()
	{
		return pedidoRepository.findAll();
	}
	public Pedido buscarPedidoCorrelativo(String correlativo) 
	{
		return pedidoRepository.findByCorrelativoContainingIgnoreCase(correlativo);
	}
	public Pedido buscarPedidoId(Long id) 
	{
		return pedidoRepository.findById(id).get();
	}
	public int validarCantidad(String correlativo) 
	{
		return pedidoRepository.verificarExistencia(correlativo);
	}
	public void cambiar(Pedido p) 
	{
		p.setEstado(0);
		
		pedidoRepository.save(p);
	}
	public int validarEstado(Pedido p) 
	{
		if(p.getEstado()==1) 
		{
			return 1;
		}else 
		{
			return 0;
		}
		
	}
}
