package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entities.Rol;
import com.example.entities.Usuario;
import com.example.service.RolService;
import com.example.service.UsuarioService;



@Controller
@RequestMapping
public class LoginController {
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private RolService rolService;
	@GetMapping("/auth/registro")
	public String registroU(Model model) 
	{
		model.addAttribute("roles", rolService.listarRoles());
		model.addAttribute("usuario",new Usuario());
		return "registroA";
	}
	@PostMapping("/auth/registro")
	public String registro(@Valid @ModelAttribute Usuario usuario, BindingResult result, Model model) 
	{

		if(result.hasErrors() || usuarioService.validar(usuario.getDni())) 
		{
			model.addAttribute("mensaje","No se encontro el usuario, ingrese nuevamente");
			return "redirect:/auth/registro";
		}else 
		{
			List<Rol> nuevoList = rolService.listarStudent("ROLE_COLEGIO");
			usuario.setRol(nuevoList.get(0));
			model.addAttribute("usuario",usuarioService.registrarUsuario(usuario));
		}
		return "redirect:/auth/login";
	}
	@GetMapping("/auth/login")
	public String login(Model model) 
	{
		model.addAttribute("mensaje","No se encontro el usuario, ingrese nuevamente");

		model.addAttribute("usuario", new Usuario());
		return "inicio";
	}
	@PostMapping("/auth/login-post")
	public String ingreso(Model model) 
	{
		model.addAttribute("usuario", new Usuario());
		return "index";
	}
	@GetMapping("/auth/landing")
	public String landing(Model model) 
	{
		
		return "landing/landing_page";
	}
}
