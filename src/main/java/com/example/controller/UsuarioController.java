package com.example.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.entities.Usuario;
import com.example.service.RolService;
import com.example.service.UsuarioService;



@Controller
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private RolService rolService;

	@GetMapping("/admin/registro")
	public String registrarAd(Model model) 
	{
		model.addAttribute("usuario",new Usuario());
		model.addAttribute("roles", rolService.listarRoles());
		return "registroA";
	}
	@PostMapping("/admins")
	public String registrarAdmin(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result) 
	{
		try {
			if (result.hasErrors()) {
				return "registroA";
			}
			usuarioService.registrarUsuario(usuario);
			return "redirect:/admin/registro";
		} catch (Exception e) {
		}
		return "redirect:/admin/registro";
	}
}
