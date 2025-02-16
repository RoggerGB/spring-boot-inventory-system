package com.example.service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.OrdenCompra;
import com.example.repository.OrdenCompraRepository;

@Service
public class OrdenCompraService {
	@Autowired
	private OrdenCompraRepository ordenCompraRepository;
	public List<OrdenCompra> listarOrdenes()
	{
		return ordenCompraRepository.findAll();
	}
	public List<OrdenCompra> listarOrdenesNoIngresadas()
	{
		return ordenCompraRepository.findByEstado(0);
	}
	public Long registrarOrden(OrdenCompra o) 
	{
		ordenCompraRepository.save(o);
		return o.getId();
	}
	public void eliminarOrden(Long id) 
	{
		ordenCompraRepository.deleteById(id);
	}
	public OrdenCompra encontrarOrdenCompra(Long id) 
	{
		return ordenCompraRepository.findById(id).get();
	}
	public OrdenCompra buscarOrdenCompra(String correlativo) 
	{
		return ordenCompraRepository.findByCodigoContainingIgnoreCase(correlativo);
	}
	public int validarCantidad(String correlativo) 
	{
		return ordenCompraRepository.verificarExistencia(correlativo);
	}
	public void cambiar(OrdenCompra o) 
	{
		OrdenCompra nuevo = new OrdenCompra();
		nuevo.setCodigo(o.getCodigo());
		nuevo.setDetalle(o.getDetalle());
		nuevo.setEstado(1);
		nuevo.setFechaR(o.getFechaR());
		nuevo.setId(o.getId());
		nuevo.setProveedor(o.getProveedor());
		ordenCompraRepository.save(nuevo);
	}
	public int valiarEstado(OrdenCompra o) 
	{
		if(o.getEstado()==1) 
		{
			return 1;
		}else 
		{
			return 0;
		}
	}
	public String aleatorio() 
	{
	    String banco = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	    String cadena = "";
	    for (int x = 0; x < 8; x++) {
	        int indiceAleatorio = numeroAleatorioEnRango(0, banco.length() - 1);
	        char caracterAleatorio = banco.charAt(indiceAleatorio);
	        cadena += caracterAleatorio;
	    }
		return cadena;
	}
	public static int numeroAleatorioEnRango(int minimo, int maximo) {
	    return ThreadLocalRandom.current().nextInt(minimo, maximo + 1);
	}
}
