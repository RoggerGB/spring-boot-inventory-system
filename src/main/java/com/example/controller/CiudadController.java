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

import com.example.entities.Ciudad;
import com.example.service.CiudadService;

@Controller
public class CiudadController {
	@Autowired
	CiudadService ciudadService;
	@GetMapping("/ciudad/nuevo")
	public String registrarCiudad(Model model) {
		model.addAttribute("ciudad", new Ciudad());
		return "ciudad/form";
	}
	@PostMapping("/ciudad/registrar")
	public String registrarCiudad1(@Validated @ModelAttribute Ciudad ciudad, BindingResult result, Model model) {		
		int rpta;
		if(result.hasErrors()) {
			return "ciudad/form";
		}
		rpta=ciudadService.registrarC(ciudad);
		if(rpta>0) 
		{
			model.addAttribute("mensaje", "Ya existe una ciudad con ese nombre");

		}else 
		{
			ciudadService.registrarC(ciudad);
			model.addAttribute("mensaje", "Se registro nueva ciudad");
			model.addAttribute("ciudad", new Ciudad());
		}
		return "ciudad/form";
	}
	@GetMapping("/ciudad/lista")
	public String listarCiudades(Model model) {
		model.addAttribute("ciudades",ciudadService.listarCiudades());
		return "ciudad/listaC";
	}
	@GetMapping("/ciudad/edit/{id}")
	public String editC(@PathVariable Long id, Model model) {
		Ciudad st = ciudadService.encontrarCiudad(id);

		model.addAttribute("ciudad", st);

		return "ciudad/update";
	}
	@GetMapping("/ciudad/delete/{id}")
	public String deleteLibro( Model model,@PathVariable Long id) {
		try {
			ciudadService.eliminarCiudad(id);

		} catch (Exception e) {
			model.addAttribute("mensaje", "La ciudad no se puede eliminar");

		}
		model.addAttribute("ciudades",ciudadService.listarCiudades());
		return "ciudad/listaC";
	}
	@PostMapping("/actualizar/{id}")
	public String updateLibro(@PathVariable Long id, @ModelAttribute("ciudad") Ciudad ciudad, Model model) {
		Ciudad st = ciudadService.encontrarCiudad(id);

		st.setId(id);
		st.setNombre(ciudad.getNombre());

		int rpta;
		rpta=ciudadService.registrarC(st);
		if(rpta>0) 
		{
			model.addAttribute("mensaje", "Ya existe una ciudad con ese nombre");

		}else 
		{
			ciudadService.registrarC(st);
			model.addAttribute("mensaje", "Se actualizo el nombre de la ciudad");
			model.addAttribute("ciudad", new Ciudad());
		}

		return "redirect:/ciudad/lista";

	}
}
