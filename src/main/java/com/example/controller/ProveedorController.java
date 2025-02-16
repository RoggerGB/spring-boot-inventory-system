package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.entities.Proveedor;
import com.example.service.CiudadService;
import com.example.service.ProveedorService;

@Controller
public class ProveedorController {
	@Autowired
	ProveedorService proveedorService;
	@Autowired
	CiudadService ciudadService;
	@GetMapping("/proveedor/nuevo")
	public String registrarP(Model model) {
		model.addAttribute("proveedor", new Proveedor());
		model.addAttribute("ciudades", ciudadService.listarCiudades());
		return "proveedor/form";
	}
	@PostMapping("/proveedor/registrar")
	public String registrarProveedor(@Validated @ModelAttribute Proveedor proveedor, BindingResult result, Model model) {		
		int rpta;
		if(result.hasErrors()) {
			model.addAttribute("ciudades", ciudadService.listarCiudades());
			return "proveedor/form";
		}
		rpta=proveedorService.registraProveedor(proveedor);
		if(rpta>0) 
		{
			model.addAttribute("ciudades", ciudadService.listarCiudades());
			model.addAttribute("mensaje", "Ya existe un proveedor con el mismo identificador");

		}else 
		{
			proveedorService.registraProveedor(proveedor);
			model.addAttribute("mensaje", "Se registro nuevo proveedor");
			model.addAttribute("proveedor", new Proveedor());
			
		}
		return "proveedor/form";
	}
	@PostMapping("/proveedor/registrar/cambiado")
	public String registrarProveedorS(@Validated @ModelAttribute Proveedor proveedor, BindingResult result, Model model) {		
		if(result.hasErrors()) {
			return "proveedor/form";
		}else 
		{
			proveedorService.registraProveedor(proveedor);
			model.addAttribute("mensaje", "Se registro nueva proveedor");
			model.addAttribute("proveedor", new Proveedor());
			
		}
	
		return "proveedor/form";
	}
	@GetMapping("/proveedor/lista")
	public String listarProveedores(Model model) {
		model.addAttribute("proveedores",proveedorService.listaP());
		return "proveedor/listaP";
	}
	@GetMapping("/proveedor/delete/{id}")
	public String deleteP(@PathVariable Long id) {
		proveedorService.eliminarProveedor(id);
		return "redirect:/proveedor/lista";
	}
	@GetMapping("/proveedor/edit/{id}")
	public String editP(@PathVariable Long id, Model model) {
		Proveedor st = proveedorService.buscarProveedor(id);

		model.addAttribute("ciudades", ciudadService.listarCiudades());

		model.addAttribute("proveedor", st);

		return "proveedor/update";
	}

	@PostMapping("/actualizar/proveedor/{id}")
	public String updatePro(@PathVariable Long id, @ModelAttribute("proveedor") Proveedor proveedor, Model model) {
		Proveedor st = proveedorService.buscarProveedor(id);

		st.setId(id);
		st.setRuc(proveedor.getRuc());
		st.setNumero(proveedor.getNumero());

		proveedorService.actualizar(st);
	

		return "redirect:/proveedor/lista";

	}
}
