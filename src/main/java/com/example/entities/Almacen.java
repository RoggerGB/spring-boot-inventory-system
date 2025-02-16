package com.example.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "almacenes")
public class Almacen {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "Ingrese una descripción")
	@Size(min=5,max=50)
	@Column(name = "descripcion", nullable = false)
	private String descripcion;
	@Size(min = 8, max = 8)
	@NotEmpty(message = "Ingresar el codigo del almacen")
	@Column(name = "codigo", nullable = false, length = 8)
	private String codigo;
	@NotEmpty(message = "Ingrese la dirección del almacen")
	@Size(min=5,max=100)
	@Column(name = "direccion", nullable = false)
	private String direccion;

	private int estado;
	@Past(message = "Fecha de creacion no correcta")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_creacion", nullable = false)
	private Date fechaC;
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
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public Date getFechaC() {
		return fechaC;
	}
	public void setFechaC(Date fechaC) {
		this.fechaC = fechaC;
	}
	public Date getFechaB() {
		return fechaB;
	}
	public void setFechaB(Date fechaB) {
		this.fechaB = fechaB;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Date getFechaM() {
		return fechaM;
	}
	public void setFechaM(Date fechaM) {
		this.fechaM = fechaM;
	}
	public Almacen() {
		super();
	}
	public Almacen(Long id, @NotEmpty(message = "Ingrese una descripción") @Size(min = 5, max = 50) String descripcion,
			@Size(min = 8, max = 8) @NotEmpty(message = "Ingresar el codigo del almacen") String codigo,
			@NotEmpty(message = "Ingrese la dirección del almacen") @Size(min = 5, max = 100) String direccion,
			@NotNull(message = "No puede ser nulo") int estado,
			@Past(message = "Fecha de creacion no correcta") Date fechaC, Date fechaB, Date fechaM) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.codigo = codigo;
		this.direccion = direccion;
		this.estado = estado;
		this.fechaC = fechaC;
		this.fechaB = fechaB;
		this.fechaM = fechaM;
	}
	
	
}
