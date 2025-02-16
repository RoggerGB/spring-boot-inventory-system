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
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "orden_compras")
public class OrdenCompra {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "proveedor_id", nullable = false)
	private Proveedor proveedor;
	@NotEmpty(message = "Ingrese el codigo de orden de compra")
	@Column(name = "codigo", nullable = false,length = 8)
	@Size(min = 8, max = 8)
	private String codigo;
	@Past(message = "Fecha de creacion no correcta")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_registro", nullable = false)
	private Date fechaR;
	@OneToMany(mappedBy = "ordenCompra", cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REMOVE}, fetch = FetchType.LAZY)
	private List<OrdenCompraDetalle> detalle;
	@Column(name = "estado")
	private int estado;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}


	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Date getFechaR() {
		return fechaR;
	}
	public void setFechaR(Date fechaR) {
		this.fechaR = fechaR;
	}

	public List<OrdenCompraDetalle> getDetalle() {
		return detalle;
	}
	public void setDetalle(List<OrdenCompraDetalle> detalle) {
		this.detalle = detalle;
	}
	public OrdenCompra() {
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public OrdenCompra(Long id, Proveedor proveedor,
			@NotEmpty(message = "Ingrese el codigo de compra") @Size(min = 8, max = 8) String codigo,
			@Past(message = "Fecha de creacion no correcta") Date fechaR, List<OrdenCompraDetalle> detalle,
			int estado) {
		super();
		this.id = id;
		this.proveedor = proveedor;
		this.codigo = codigo;
		this.fechaR = fechaR;
		this.detalle = detalle;
		this.estado = estado;
	}
	
	

	
}
