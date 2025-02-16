package com.example.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class OrdenProducto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Producto producto;
	@NotNull
	@Positive
	private double precio;
	@NotNull
	@Positive
	@Min(value=1)
	private int cantidad;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public OrdenProducto() {
		super();
	}
	public OrdenProducto(Long id, Producto producto, double precio, int cantidad) {
		super();
		this.id = id;
		this.producto = producto;
		this.precio = precio;
		this.cantidad = cantidad;
	}
	
	
}
