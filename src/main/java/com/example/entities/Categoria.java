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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "categorias")
public class Categoria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	@NotEmpty(message = "Ingrese una descripción")
	@Size(min=5,max=50)
	@Column(name = "descripcion", nullable = false)
	private String descripcion;
	@Past(message = "Fecha de creacion no correcta")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
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
	@Column(name = "estado")
	private int estado;
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

	public Categoria() {
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

	public Categoria(Long id,
			@NotEmpty(message = "Ingrese una descripción") @Size(min = 5, max = 50) String descripcion,
			@Past(message = "Fecha de creacion no correcta") Date fechaC, Date fechaB, Date fechaM, int estado) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.fechaC = fechaC;
		this.fechaB = fechaB;
		this.fechaM = fechaM;
		this.estado = estado;
	}



	
}
