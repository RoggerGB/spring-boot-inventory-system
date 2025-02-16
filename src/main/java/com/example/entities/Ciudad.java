package com.example.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "ciudades")
public class Ciudad {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "Debe ingresar nombre de la ciudad*")
	@Column(name = "nombre", nullable = false, length = 45)
	private String nombre;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Ciudad(Long id, @NotEmpty(message = "Debe ingresar nombre de la ciudad*") String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public Ciudad() {
	}
	
}
