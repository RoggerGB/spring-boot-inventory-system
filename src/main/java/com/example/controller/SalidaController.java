package com.example.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import com.example.entities.Pedido;
import com.example.entities.PedidoDetalle;
import com.example.entities.SalidaMercaderia;
import com.example.entities.Usuario;
import com.example.service.PedidoDetalleService;
import com.example.service.PedidoService;
import com.example.service.ProductoService;
import com.example.service.SalidaMercaderiaService;
import com.example.service.UsuarioService;

@Controller
public class SalidaController {
	@Autowired
	PedidoService pedidoService;
	@Autowired
	PedidoDetalleService pedidoDetalleService;
	@Autowired
	ProductoService productoService;
	@Autowired
	UsuarioService usuarioService;
	Usuario usuario = new Usuario();
	@Autowired
	SalidaMercaderiaService salidaMercaderiaService;
	Pedido pedidoNuevo = new Pedido();
	@GetMapping("/salida/pedido/{id}")
	public String nuevo(@PathVariable Long id,Model model){
		model.addAttribute("pedido",new Pedido());
		model.addAttribute("detalle",new PedidoDetalle());
		usuario = usuarioService.encontrarUsuario(id);
		return "salida/buscador";
	}
	@GetMapping("/salida/lista")
	public String listarSalidas(Model model) {
		model.addAttribute("salidas",salidaMercaderiaService.listaMercaderias());
		return "salida/listaS";
	}
	@PostMapping("/pedido/buscar")
	public String buscarPedido(Model model, @ModelAttribute Pedido pedido) {
		if(pedidoService.validarCantidad(pedido.getCorrelativo())>0) 
		{
			model.addAttribute("pedido",pedidoService.buscarPedidoCorrelativo(pedido.getCorrelativo()));
			Pedido nuevo = pedidoService.buscarPedidoCorrelativo(pedido.getCorrelativo());
			model.addAttribute("detalle",pedidoDetalleService.encontrarPedidoDetalle(nuevo.getId()));
			pedidoNuevo= pedidoService.buscarPedidoCorrelativo(pedido.getCorrelativo());
		}else 
		{
			model.addAttribute("pedido",new Pedido());
			model.addAttribute("detalle",new PedidoDetalle());
			model.addAttribute("mensaje", "El pedido de venta no existe");
			return "salida/buscador";

		}

		return "salida/buscador";
	}
	@GetMapping("/salida/registro")
	public String salida(Model model) {
		if(pedidoNuevo.getId()!=null) 
		{
			if(pedidoService.validarEstado(pedidoNuevo)==0)
			{
				if(pedidoDetalleService.verificarResta(pedidoDetalleService.encontrarPedidoDetalle(pedidoNuevo.getId()))==1) 
				{
					pedidoDetalleService.resta(pedidoDetalleService.encontrarPedidoDetalle(pedidoNuevo.getId()));
					pedidoService.cambiar(pedidoNuevo);
					model.addAttribute("mensajeExitoso", "Pedido de salida correcta");
					/*Guardar Salida*/
					LocalDate localDate = LocalDate.now();
					SalidaMercaderia salidaMercaderia = new SalidaMercaderia();
					salidaMercaderia.setFechaR(java.sql.Date.valueOf(localDate));
					salidaMercaderia.setPedido(pedidoNuevo);
					salidaMercaderia.setUsuario(usuario);
					salidaMercaderiaService.registrar(salidaMercaderia);
				}else 
				{
					model.addAttribute("mensaje", "No se encuentra stock disponible");

				}
			

			}else 
			{
				model.addAttribute("mensaje", "Esta venta ya fue ingresada anteriormente");

			}
			model.addAttribute("pedido",pedidoNuevo);
			model.addAttribute("detalle",pedidoDetalleService.encontrarPedidoDetalle(pedidoNuevo.getId()));
			
		}else 
		{
			model.addAttribute("mensaje", "Complete los recuadros");
			model.addAttribute("pedido",new Pedido());
			model.addAttribute("detalle",new PedidoDetalle());
		}

		return "salida/buscador";
	}
	
}
