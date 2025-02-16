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
@Table(name = "pedido_detalle")
public class PedidoDetalle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "pedido_id", nullable = false)
	private Pedido pedido;
	@ManyToOne
	@JoinColumn(name = "producto_id", nullable = false)
	private Producto producto;
	private double precio;
	@Min(1)
	@Column(name = "cantidad", nullable = false)
	int cantidad;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
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
	public PedidoDetalle() {
		this.producto=new Producto();

	}
	public PedidoDetalle(Long id, Pedido pedido, Producto producto, double precio, @Min(1) int cantidad) {
		super();
		this.id = id;
		this.pedido = pedido;
		this.producto = producto;
		this.precio = precio;
		this.cantidad = cantidad;
	}

	
}
