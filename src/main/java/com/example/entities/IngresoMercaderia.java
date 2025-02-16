package com.example.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "ingreso_mercaderia")
public class IngresoMercaderia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "orden_id", nullable = false)
	private OrdenCompra ordenCompra;
	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;
	@Past(message = "Fecha de registro no correcta")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_registro", nullable = false)
	private Date fechaR;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Date getFechaR() {
		return fechaR;
	}
	public void setFechaR(Date fechaR) {
		this.fechaR = fechaR;
	}

	public OrdenCompra getOrdenCompra() {
		return ordenCompra;
	}
	public void setOrdenCompra(OrdenCompra ordenCompra) {
		this.ordenCompra = ordenCompra;
	}
	public IngresoMercaderia() {
		super();
	}
	public IngresoMercaderia(Long id, OrdenCompra ordenCompra, Usuario usuario,
			@Past(message = "Fecha de registro no correcta") Date fechaR) {
		super();
		this.id = id;
		this.ordenCompra = ordenCompra;
		this.usuario = usuario;
		this.fechaR = fechaR;
	}
	
}
