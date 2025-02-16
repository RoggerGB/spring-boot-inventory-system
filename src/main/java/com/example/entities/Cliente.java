package com.example.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "clientes")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "Ingrese la razon")
	@Column(name = "razon_social", nullable = false, length = 70)
	private String razonSocial;
	@NotEmpty(message = "Ingrese el ruc")
	@Column(name = "ruc", nullable = false, length = 70)
	private String ruc;
	@NotEmpty(message = "Ingrese la direccion")
	@Column(name = "direccion", nullable = false, length = 70)
	private String direccion;
	@NotEmpty(message = "Ingrese el contacto")
	@Column(name = "contacto", nullable = false, length = 70)
	private String contacto;
	@NotEmpty(message = "Ingrese telefono")
	@Column(name = "telefono", nullable = false, length = 70)
	private String telefono;
	@NotEmpty(message = "Ingrese correo")
	@Column(name = "correo", nullable = false, length = 70)
	private String correo;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getRuc() {
		return ruc;
	}
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getContacto() {
		return contacto;
	}
	public void setContacto(String contacto) {
		this.contacto = contacto;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public Cliente(Long id, @NotEmpty(message = "Ingrese la razon") String razonSocial,
			@NotEmpty(message = "Ingrese el ruc") String ruc,
			@NotEmpty(message = "Ingrese la direccion") String direccion,
			@NotEmpty(message = "Ingrese el contacto") String contacto,
			@NotEmpty(message = "Ingrese telefono") String telefono,
			@NotEmpty(message = "Ingrese correo") String correo) {
		super();
		this.id = id;
		this.razonSocial = razonSocial;
		this.ruc = ruc;
		this.direccion = direccion;
		this.contacto = contacto;
		this.telefono = telefono;
		this.correo = correo;
	}
	public Cliente() {
	}
	
}
