package com.example.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.entities.Categoria;
import com.example.entities.OrdenCompra;
import com.example.entities.OrdenCompraDetalle;
import com.example.entities.OrdenProducto;
import com.example.entities.Producto;
import com.example.entities.Proveedor;
import com.example.service.ClienteService;
import com.example.service.OrdenCompraDetalleService;
import com.example.service.OrdenCompraService;
import com.example.service.PedidoService;
import com.example.service.ProductoService;
import com.example.service.ProveedorService;

@Controller
public class OrdenCompraController {
	@Autowired
	OrdenCompraService ordenCompraService;
	@Autowired
	PedidoService pedidoService;
	@Autowired
	OrdenCompraDetalleService ordenCompraDetalleService;
	@Autowired
	ProductoService productoService;
	@Autowired
	ProveedorService proveedorService;
	@Autowired
	ClienteService clienteService;
	List<OrdenProducto> listaOrdenesProductos = new ArrayList<>();

	@GetMapping("/orden/nuevo")
	public String registrarOrden(Model model) {
		OrdenProducto oProducto = new OrdenProducto();
		model.addAttribute("ordenCompra", new OrdenCompra());
		model.addAttribute("proveedores", proveedorService.listaP());
		model.addAttribute("productos", productoService.listarProductos());
		model.addAttribute("ordenpp", oProducto);
		model.addAttribute("lista", listaOrdenesProductos);
		System.out.println(ordenCompraService.aleatorio());
		return "orden/form";
	}

	@GetMapping("/orden/lista")
	public String listarOrdenes(Model model) {
		model.addAttribute("ordenes", ordenCompraService.listarOrdenes());
		return "orden/listaO";
	}

	@GetMapping("/pedido/lista")
	public String listarPedidos(Model model) {
		model.addAttribute("pedidos", pedidoService.listarPedidos());
		return "pedido/listaP";
	}

	@GetMapping("/cliente/lista")
	public String listarC(Model model) {
		model.addAttribute("clientes", clienteService.listarClientes());
		return "cliente/listaC";
	}

	@PostMapping("/orden/registrar")
	public String registrarOrdenes(@Validated @ModelAttribute OrdenCompra orden, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("ordenCompra", orden);
			model.addAttribute("proveedores", proveedorService.listaP());
			model.addAttribute("productos", productoService.listarProductos());
			model.addAttribute("ordenpp", new OrdenProducto());
			model.addAttribute("lista", listaOrdenesProductos);
			return "orden/form";

		} else {
			if (listaOrdenesProductos.size() > 0) {
				if (ordenCompraService.validarCantidad(orden.getCodigo()) == 0) {
					// orden
					LocalDate localDate = LocalDate.now();
					orden.setFechaR(java.sql.Date.valueOf(localDate));
					ordenCompraService.registrarOrden(orden);
					// detalle orden
					ordenCompraDetalleService.registro(listaOrdenesProductos, orden);
					//
					// no se
					model.addAttribute("ordenpp", new OrdenProducto());
					// msj
					model.addAttribute("mensaje", "Se registro nueva orden");
					model.addAttribute("proveedores", proveedorService.listaP());
					model.addAttribute("ordenCompra", new OrdenCompra());
					model.addAttribute("productos", productoService.listarProductos());

					listaOrdenesProductos = new ArrayList<>();

					return "orden/form";
				} else {
					model.addAttribute("mensaje", "El codigo ya esta en uso");
					model.addAttribute("ordenCompra", new OrdenCompra());
					model.addAttribute("proveedores", proveedorService.listaP());
					model.addAttribute("productos", productoService.listarProductos());
					model.addAttribute("ordenpp", new OrdenProducto());
					model.addAttribute("lista", listaOrdenesProductos);
					return "orden/form";
				}

			} else {
				model.addAttribute("mensaje", "Debe tener al menos un producto en la lista");
				model.addAttribute("ordenCompra", new OrdenCompra());
				model.addAttribute("proveedores", proveedorService.listaP());
				model.addAttribute("productos", productoService.listarProductos());
				model.addAttribute("ordenpp", new OrdenProducto());
				model.addAttribute("lista", listaOrdenesProductos);
				return "orden/form";
			}

		}
	}
	@PostMapping("/agregar/nuevo")
	public String registrarDetalles(@Validated @ModelAttribute OrdenProducto ordenpp, BindingResult result,
			Model model) {
		if (ordenpp.getCantidad() > 0 && ordenpp.getPrecio() > 0) {
			ordenpp.setId(Long.valueOf(listaOrdenesProductos.size()));
			listaOrdenesProductos.add(ordenpp);
			return "redirect:/orden/nuevo";

		} else {
			model.addAttribute("mensaje", "Debe ingresar los datos correctos");
			model.addAttribute("ordenCompra", new OrdenCompra());
			model.addAttribute("proveedores", proveedorService.listaP());
			model.addAttribute("productos", productoService.listarProductos());
			model.addAttribute("ordenpp", new OrdenProducto());
			model.addAttribute("lista", listaOrdenesProductos);
			return "orden/form";
		}
	}

	@GetMapping("/ordenes/delete/{id}")
	public String deleteOD(Model model, @PathVariable Long id) {
		eliminar(Math.toIntExact(id));
		model.addAttribute("ordenCompra", new OrdenCompra());
		model.addAttribute("proveedores", proveedorService.listaP());
		model.addAttribute("productos", productoService.listarProductos());
		model.addAttribute("ordenpp", new OrdenProducto());
		model.addAttribute("lista", listaOrdenesProductos);
		return "orden/form";
	}

	public void eliminar(int id) {
		List<OrdenProducto> ordenProductos = new ArrayList<>();

		for (int i = 0; i < listaOrdenesProductos.size(); i++) {
			if (listaOrdenesProductos.get(i).getId() != id) {
				ordenProductos.add(listaOrdenesProductos.get(i));
			}
		}
		listaOrdenesProductos = ordenProductos;
	}
	/*
	 * @PostMapping("/ordenes/edit/post/{id}") public String
	 * updateLibro(@PathVariable Long id, @ModelAttribute("ordenCompraC")
	 * OrdenCompra ordenCompra, Model model) {
	 * 
	 * /*Categoria st = categoriaService.encontrarCategoria(id);
	 * 
	 * st.setId(id); st.setDescripcion(categoria.getDescripcion());
	 * st.setFechaC(categoria.getFechaC()); st.setFechaB(categoria.getFechaB());
	 * st.setFechaM(categoria.getFechaM()); int rpta;
	 * rpta=categoriaService.registrarCategoria(st); if(rpta>0) {
	 * model.addAttribute("mensaje", "Ya existe una categoria con esa descripcion");
	 * 
	 * }else { categoriaService.registrarCategoria(st);
	 * model.addAttribute("mensaje", "Se actualizo los datos de la categoria");
	 * model.addAttribute("categoria", new Categoria()); }
	 * 
	 * return "redirect:/categoria/lista";
	 * 
	 * }
	 * 
	 * @GetMapping("/ordenes/edit/{id}") public String editCFF(@PathVariable Long
	 * id, Model model) { OrdenProducto od = new OrdenProducto(); for(int
	 * i=0;i<listaOrdenesProductos.size();i++) {
	 * if(listaOrdenesProductos.get(i).getId()==id) {
	 * od=listaOrdenesProductos.get(i); } }
	 * 
	 * model.addAttribute("ordenpp", od);
	 * 
	 * return ""; }
	 */
}
