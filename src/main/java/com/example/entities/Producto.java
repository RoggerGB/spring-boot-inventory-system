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
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name = "productos")
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	@NotEmpty(message = "Ingrese una descripcion")
	@Size(min=5,max=50)
	@Column(name = "descripcion", nullable = false)
	private String descripcion;
	
	@Column(name = "stock", nullable = false)
	int stock;
	@Column(name = "estado", nullable = false)
	int estado;
	@Size(min = 8, max = 8)
	@NotEmpty(message = "Ingresar el SKU del producto")
	@Column(name = "sku", nullable = false, length = 8)
	private String sku;
	@Size(min = 1, max = 5)
	@NotEmpty(message = "Ingresar la unidad de medida del producto")
	@Column(name = "unidad", nullable = false)
	private String unidad;

	@ManyToOne
	@JoinColumn(name = "categoria_id", nullable = false)
	Categoria categoria;
	@ManyToOne
	@JoinColumn(name = "almacen_id", nullable = false)
	Almacen almacen;
	@Past(message = "Fecha de registro no correcta")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_registro", nullable = false)
	private Date fechaR;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_baja", nullable = false)
	private Date fechaB;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_modificacion", nullable = false)
	private Date fechaM;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public Producto() {
	}
	public String getUnidad() {
		return unidad;
	}
	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
	public Almacen getAlmacen() {
		return almacen;
	}
	public void setAlmacen(Almacen almacen) {
		this.almacen = almacen;
	}
	public Date getFechaR() {
		return fechaR;
	}
	public void setFechaR(Date fechaR) {
		this.fechaR = fechaR;
	}
	public Date getFechaB() {
		return fechaB;
	}
	public void setFechaB(Date fechaB) {
		this.fechaB = fechaB;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public Date getFechaM() {
		return fechaM;
	}
	public void setFechaM(Date fechaM) {
		this.fechaM = fechaM;
	}
	public Producto(Long id, @NotEmpty(message = "Ingrese una descripcion") @Size(min = 5, max = 50) String descripcion,
			int stock, int estado,
			@Size(min = 8, max = 8) @NotEmpty(message = "Ingresar el SKU del producto") String sku,
			@Size(min = 1, max = 5) @NotEmpty(message = "Ingresar la unidad de medida del producto") String unidad,
			Categoria categoria, Almacen almacen, @Past(message = "Fecha de registro no correcta") Date fechaR,
			Date fechaB, Date fechaM) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.stock = stock;
		this.estado = estado;
		this.sku = sku;
		this.unidad = unidad;
		this.categoria = categoria;
		this.almacen = almacen;
		this.fechaR = fechaR;
		this.fechaB = fechaB;
		this.fechaM = fechaM;
	}



}
