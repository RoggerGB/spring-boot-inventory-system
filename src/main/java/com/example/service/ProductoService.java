package com.example.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.Producto;
import com.example.repository.ProductoRepository;

@Service
public class ProductoService {
	@Autowired
	ProductoRepository productoRepository;

	public ProductoService(ProductoRepository productoRepository) {
		this.productoRepository = productoRepository;
	}
	public List<Producto> listarProductos()
	{
		return productoRepository.findAll();
	}
	public int registrarProducto(Producto p) 
	{
		int existe = productoRepository.verificarExistencia(p.getSku());
		if(existe==0) 
		{
			if(buscar(p.getSku()).size()==0) 
			{
				productoRepository.save(p);

			}else 
			{
				existe = 1;
			}
		}
		return existe;
	}
	public void eliminarProducto(Long id) 
	{
		productoRepository.deleteById(id);
	}
	public Producto encontrarProducto(Long c) 
	{
		return productoRepository.findById(c).get();
	}
	private List<Producto> buscar(String sku) 
	{
		return productoRepository.findBySkuContainingIgnoreCase(sku);
	}
	public Producto actualizar(Producto c) 
	{
		return productoRepository.save(c);
	}
	public void dar_baja(Long id) 
	{		
		LocalDate localDate = LocalDate.now();

		Producto p =productoRepository.findById(id).get();
		p.setEstado(0);
		p.setFechaB(java.sql.Date.valueOf(localDate));
		productoRepository.save(p);
	}
	public void activar(Long id) 
	{		

		Producto p =productoRepository.findById(id).get();
		p.setEstado(1);
		productoRepository.save(p);
	}
	public List<Producto> activado()
	{	
		return productoRepository.findByEstado(1);
	}
	public List<Producto> desactivado()
	{	
		return productoRepository.findByEstado(0);
	}
	public boolean buscarXSku(Long p) 
	{
		Producto cc = encontrarProducto(p);
		int existe = productoRepository.verificarExistencia(cc.getSku());
		if(existe>0) 
		{
			return true;
		}else 
		{
			return false;
		}
	}
}
