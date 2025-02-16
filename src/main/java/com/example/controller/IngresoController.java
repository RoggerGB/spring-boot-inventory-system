package com.example.controller;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.entities.IngresoMercaderia;
import com.example.entities.OrdenCompra;
import com.example.entities.OrdenCompraDetalle;
import com.example.entities.Producto;
import com.example.entities.Usuario;
import com.example.service.IngresoMercaderiaService;
import com.example.service.OrdenCompraDetalleService;
import com.example.service.OrdenCompraService;
import com.example.service.ProductoService;
import com.example.service.UsuarioService;

@Controller
public class IngresoController {
	@Autowired
	OrdenCompraService ordenCompraService;
	@Autowired
	OrdenCompraDetalleService ordenCompraDetalleService;
	@Autowired
	ProductoService productoService;
	@Autowired
	IngresoMercaderiaService ingresoMercaderiaService;
	@Autowired
	UsuarioService usuarioService;
	Usuario usuario = new Usuario();
	OrdenCompra ordenNuevo = new OrdenCompra();
	@GetMapping("/ingreso/orden/{id}")
	public String nuevo(@PathVariable Long id,Model model){
		model.addAttribute("ordenCompra",new OrdenCompra());
		model.addAttribute("detalle",new OrdenCompraDetalle());
		model.addAttribute("ordenes",ordenCompraService.listarOrdenesNoIngresadas());

		usuario = usuarioService.encontrarUsuario(id);
		return "ingreso/buscador";
	}
	@GetMapping("/ingreso/lista")
	public String listarIngresos(Model model) {
		model.addAttribute("ingresos",ingresoMercaderiaService.listaMercaderias());
		return "ingreso/listaL";
	}
	@PostMapping("/orden/buscar")
	public String buscarOrden(Model model, @ModelAttribute OrdenCompra ordenCompra) {
	
			if(ordenCompraService.validarCantidad(ordenCompra.getCodigo())>0) 
			{
				/*Encontrar Orden*/
				model.addAttribute("ordenCompra", ordenCompraService.buscarOrdenCompra(ordenCompra.getCodigo()));
				OrdenCompra orden = ordenCompraService.buscarOrdenCompra(ordenCompra.getCodigo());
				ordenNuevo = ordenCompraService.buscarOrdenCompra(ordenCompra.getCodigo());

				model.addAttribute("detalle",ordenCompraDetalleService.encontrarOrdenCompraDetalle(orden.getId()));
				model.addAttribute("ordenes",ordenCompraService.listarOrdenesNoIngresadas());

			}else 
			{
				model.addAttribute("ordenCompra",new OrdenCompra());
				model.addAttribute("detalle",new OrdenCompraDetalle());
				model.addAttribute("ordenes",ordenCompraService.listarOrdenesNoIngresadas());

				model.addAttribute("mensaje", "La orden de compra no existe");
				System.out.println(ordenCompra.getCodigo());
				return "ingreso/buscador";
			}

		return "ingreso/buscador";
	}
	@GetMapping("/ingreso/registro")
	public String ingresar(Model model) {
		if(ordenNuevo.getId()!=null) 
		{
			if(ordenCompraService.valiarEstado(ordenNuevo)==0)
			{
				ordenCompraDetalleService.suma(ordenCompraDetalleService.encontrarOrdenCompraDetalle(ordenNuevo.getId()));
				ordenCompraService.cambiar(ordenNuevo);
				model.addAttribute("mensajeExitoso", "Orden ingresada correctamente");
				LocalDate localDate = LocalDate.now();
				IngresoMercaderia ingresoMercaderia = new IngresoMercaderia();
				ingresoMercaderia.setFechaR(java.sql.Date.valueOf(localDate));
				ingresoMercaderia.setOrdenCompra(ordenNuevo);
				ingresoMercaderia.setUsuario(usuario);
				ingresoMercaderiaService.registrar(ingresoMercaderia);
			}else 
			{
				model.addAttribute("mensaje", "Esta orden ya fue ingresada anteriormente");

			}
			model.addAttribute("ordenCompra",new OrdenCompra());
			model.addAttribute("detalle",new OrdenCompraDetalle());
			model.addAttribute("ordenes",ordenCompraService.listarOrdenesNoIngresadas());

		}else 
		{
			model.addAttribute("mensaje", "Complete los recuadros");
			model.addAttribute("ordenCompra", new OrdenCompra());
			model.addAttribute("detalle", new OrdenCompraDetalle());
			model.addAttribute("ordenes",ordenCompraService.listarOrdenesNoIngresadas());

		}

		return "ingreso/buscador";
	}
}
