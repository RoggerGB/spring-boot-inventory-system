package com.example.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entities.Categoria;
import com.example.repository.CategoriaRepository;

@Service
public class CategoriaService {
	private CategoriaRepository categoriaRepository;
	public CategoriaService(CategoriaRepository categoriaRepository) 
	{
		this.categoriaRepository=categoriaRepository;
	}
	public List<Categoria> listarCategoria() 
	{
		return categoriaRepository.findAll();
	}
	public int registrarCategoria(Categoria c) 
	{
		int existe = categoriaRepository.verificarExistencia(c.getDescripcion());
		if(existe==0) 
		{
			if(buscar(c.getDescripcion()).size()==0) 
			{
				c.setEstado(1);
				categoriaRepository.save(c);

			}else 
			{
				existe=1;
			}
		}
		return existe;
	}
	public void deleteCategoriaById(Long id) {
		categoriaRepository.deleteById(id);
	}
	public List<Categoria> buscar(String nombre) 
	{
		return categoriaRepository.findByDescripcionContainingIgnoreCase(nombre);
	}
	public Categoria encontrarCategoria(Long c) 
	{
		return categoriaRepository.findById(c).get();
	}
	public Categoria actualizar(Categoria c) 
	{
		return categoriaRepository.save(c);
	}
	public void dar_baja(Long id) 
	{
		LocalDate localDate = LocalDate.now();

		Categoria nCategoria=categoriaRepository.findById(id).get();
		nCategoria.setFechaB(java.sql.Date.valueOf(localDate));
		nCategoria.setEstado(0);
		categoriaRepository.save(nCategoria);
	}
	public List<Categoria> activo()
	{
		return categoriaRepository.findByEstado(1);
	}
	public List<Categoria> desactivo()
	{
		return categoriaRepository.findByEstado(0);
	}
	public void activar(Long id) 
	{
		LocalDate localDate = LocalDate.now();

		Categoria nCategoria=categoriaRepository.findById(id).get();
		nCategoria.setFechaB(java.sql.Date.valueOf(localDate));
		nCategoria.setEstado(1);
		categoriaRepository.save(nCategoria);
	}
	public void registrar(Categoria c) 
	{
		categoriaRepository.save(c);
	}
	public int verificar(Categoria cat) 
	{
		if(categoriaRepository.verificarCat(cat.getDescripcion())>0) 
		{
			return 1;
		}else 
		{
			return 0;
		}
	}
}
