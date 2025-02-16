package com.example.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name = "pedidos")
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;
	@NotEmpty(message = "Ingrese la serie")
	@Column(name = "serie", nullable = false, length = 70)
	private String serie;
	@NotEmpty(message = "Ingrese el correlativo")
	@Column(name = "correlativo", nullable = false, length = 70)
	private String correlativo;
	@Past(message = "Fecha de creacion no correcta")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_registro", nullable = false)
	private Date fechaR;
	@OneToMany(mappedBy = "pedido", cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REMOVE}, fetch = FetchType.LAZY)
	private List<PedidoDetalle> detalle;
	@Column(name = "estado")
	private int estado;
	
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Pedido(Long id, Cliente cliente, @NotEmpty(message = "Ingrese la serie") String serie,
			@NotEmpty(message = "Ingrese el correlativo") String correlativo,
			@Past(message = "Fecha de creacion no correcta") Date fechaR, List<PedidoDetalle> detalle, int estado) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.serie = serie;
		this.correlativo = correlativo;
		this.fechaR = fechaR;
		this.detalle = detalle;
		this.estado = estado;
	}
	public List<PedidoDetalle> getDetalle() {
		return detalle;
	}
	public void setDetalle(List<PedidoDetalle> detalle) {
		this.detalle = detalle;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getCorrelativo() {
		return correlativo;
	}
	public void setCorrelativo(String correlativo) {
		this.correlativo = correlativo;
	}
	public Date getFechaR() {
		return fechaR;
	}
	public void setFechaR(Date fechaR) {
		this.fechaR = fechaR;
	}
	public Pedido() {
	}
	
}
