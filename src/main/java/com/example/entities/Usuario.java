package com.example.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@Table(name="usuarios")
@Inheritance(strategy = InheritanceType.JOINED)

public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="usu_codigo")
	private Long id;
	@NotEmpty(message = "Ingresar sus nombres")
	@Column(name = "nombres", nullable = false)
	@Size(min=5,max=10)
	private String nombres;
	@NotEmpty(message = "Ingresar sus apellidos")
	@Column(name = "apellidos", nullable = false)
	@Size(min=8,max=100)
	private String apellidos;
	private int edad;
	@Size(min = 8, max = 8)
	@NotEmpty(message = "Ingresar su dni")
	@Column(name = "dni", nullable = false, length = 8)
	private String dni;
	private String password;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "rol_id")
	private Rol rol;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	public Usuario(Long id, @NotEmpty(message = "Ingresar sus nombres") String nombres,
			@NotEmpty(message = "Ingresar sus apellidos") String apellidos, int edad,
			@Size(min = 8, max = 8) @NotEmpty(message = "Ingresar su dni") String dni, String password, Rol rol) {
		super();
		this.id = id;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.edad = edad;
		this.dni = dni;
		this.password = password;
		this.rol = rol;
	}
	public Usuario() {
		super();
	}
	
}
