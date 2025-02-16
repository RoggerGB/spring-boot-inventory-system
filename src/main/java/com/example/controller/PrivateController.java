package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entities.Usuario;
import com.example.repository.UsuarioRepository;
import com.example.service.OrdenCompraService;
import com.example.service.PedidoService;
import com.example.service.ProductoService;
import com.example.service.ProveedorService;

@Controller
@RequestMapping("/private")
public class PrivateController {
	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	ProductoService productoService;
	@Autowired
	ProveedorService proveedorService;
	@Autowired
	OrdenCompraService ordenCompraService;
	@Autowired
	PedidoService pedidoService;
	@GetMapping("/index")
	public String indx(Authentication auth, HttpSession session, Model model) {
		String username = auth.getName();
		if (session.getAttribute("usuario") == null) {
			Usuario user = usuarioRepository.findByDni(username);
			user.setPassword(null);
			session.setAttribute("usuario", user);
		} 
		model.addAttribute("productos",productoService.listarProductos());
		model.addAttribute("proveedores",proveedorService.listaP());
		model.addAttribute("pedidos",pedidoService.listarPedidos());

		model.addAttribute("ordenes",ordenCompraService.listarOrdenes());

		return "index";
	}
}
