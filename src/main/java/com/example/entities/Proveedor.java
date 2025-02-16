package com.example.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@Table(name = "proveedores")
public class Proveedor{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 9, max = 9)
	@NotEmpty(message = "Ingresar el numero de contacto")
	@Column(name = "numero", nullable = false, length = 9)
	private String numero;
	@Size(min = 5, max = 100)
	@NotEmpty(message = "Ingresar la razon social del proveedor")
	@Column(name = "razon_social", nullable = false, length = 100)
	private String razon;
	@Size(min = 5, max = 100)
	@NotEmpty(message = "Ingresar la dirección del proveedor")
	@Column(name = "direccion", nullable = false, length = 100)
	private String direccion;
	@Size(min = 5, max = 100)
	@NotEmpty(message = "Ingresar el contacto del proveedor")
	@Column(name = "contacto", nullable = false, length = 100)
	private String contacto;
	@Size(min=15,max = 100)
	@NotEmpty(message = "Ingresar el correo del proveedor")
    @Email(message = "Email no valido")
	@Column(name = "correo", nullable = false, length = 100)
	private String correo;
	@Size(min = 11, max = 11)
	@NotEmpty(message = "Ingresar el RUC del proveedor")
	@Column(name = "ruc", nullable = false, length = 11)
	private String ruc;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}


	public String getRuc() {
		return ruc;
	}
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	public String getRazon() {
		return razon;
	}
	public void setRazon(String razon) {
		this.razon = razon;
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
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public Proveedor() {
		super();
	}
	public Proveedor(Long id,
			@Size(min = 9, max = 9) @NotEmpty(message = "Ingresar el numero de contacto") String numero,
			@Size(min = 5, max = 100) @NotEmpty(message = "Ingresar la razon social del proveedor") String razon,
			@Size(min = 5, max = 100) @NotEmpty(message = "Ingresar la dirección del proveedor") String direccion,
			@Size(min = 5, max = 100) @NotEmpty(message = "Ingresar el contacto del proveedor") String contacto,
			@Size(min = 15, max = 100) @NotEmpty(message = "Ingresar el correo del proveedor") @Email(message = "Email no valido") String correo,
			@Size(min = 11, max = 11) @NotEmpty(message = "Ingresar el RUC del proveedor") String ruc) {
		super();
		this.id = id;
		this.numero = numero;
		this.razon = razon;
		this.direccion = direccion;
		this.contacto = contacto;
		this.correo = correo;
		this.ruc = ruc;
	}



}
