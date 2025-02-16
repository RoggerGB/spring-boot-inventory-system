package com.example.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

@Entity
@Table(name = "orden_compra_detalle")
public class OrdenCompraDetalle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "orden_id", nullable = false)
	private OrdenCompra ordenCompra;
	@ManyToOne
	@JoinColumn(name = "producto_id", nullable = false)
	private Producto producto;

	private double costo;
	@Min(1)
	@Column(name = "cantidad", nullable = false)
	int cantidad;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public OrdenCompra getOrdenCompra() {
		return ordenCompra;
	}
	public void setOrdenCompra(OrdenCompra ordenCompra) {
		this.ordenCompra = ordenCompra;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public double getCosto() {
		return costo;
	}
	public void setCosto(double costo) {
		this.costo = costo;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public OrdenCompraDetalle(Long id, OrdenCompra ordenCompra, Producto producto, double costo, @Min(1) int cantidad) {
		super();
		this.id = id;
		this.ordenCompra = ordenCompra;
		this.producto = producto;
		this.costo = costo;
		this.cantidad = cantidad;
	}
	public OrdenCompraDetalle() {
		this.producto=new Producto();
	}
	
}
