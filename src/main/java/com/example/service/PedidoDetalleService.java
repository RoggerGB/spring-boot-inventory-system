package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.PedidoDetalle;
import com.example.entities.Producto;
import com.example.repository.PedidoDetalleRepository;

@Service
public class PedidoDetalleService {
	@Autowired
	PedidoDetalleRepository pedidoDetalleRepository;
	@Autowired
	ProductoService productoService;
	public List<PedidoDetalle> encontrarPedidoDetalle(Long id) 
	{
		return pedidoDetalleRepository.findByPedido(id);
	}
	public PedidoDetalle actualizar(PedidoDetalle c) 
	{
		return pedidoDetalleRepository.save(c);
	}
	public void resta(List<PedidoDetalle> lista) 
	{
		
			for(int i=0;i<lista.size();i++) 
			{
				Producto actualizar = productoService.encontrarProducto(lista.get(i).getProducto().getId());
				int cantidad = actualizar.getStock()-lista.get(i).getCantidad();
				actualizar.setStock(cantidad);
				actualizar.setId(actualizar.getId());
				actualizar.setDescripcion(actualizar.getDescripcion());
				actualizar.setSku(actualizar.getSku());
				actualizar.setUnidad(actualizar.getUnidad());
				actualizar.setAlmacen(actualizar.getAlmacen());
				actualizar.setCategoria(actualizar.getCategoria());
				actualizar.setFechaB(actualizar.getFechaB());
				actualizar.setFechaR(actualizar.getFechaR());
				productoService.actualizar(actualizar);
			}
		
		
	}
	public int verificarResta(List<PedidoDetalle> lista) 
	{
		int cc= 1;
		for(int i=0;i<lista.size();i++) 
		{
			Producto pp = productoService.encontrarProducto(lista.get(i).getProducto().getId());
			if(pp.getStock()-lista.get(i).getCantidad()<0) 
			{
				cc=0;
				break;
			}
		}
		return cc;
	}

}
