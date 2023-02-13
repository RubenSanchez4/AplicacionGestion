package com.aplicacionRRHH.modelos;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Scope("session")
public class Usuario implements Serializable{

	private static final long serialVersionUID = 5940502184821562650L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	Long Id;
	
	@NotEmpty
	String nombre;
	
	@NotEmpty
	String apellido1;
	
	@NotEmpty
	String apellido2;
	
	@NotEmpty
	String username;
	
	@NotEmpty
	String password;
	
	@NotEmpty
	String correo;
	
	@NotEmpty
	String telefono;
	
	@NotEmpty
	String direccion;
	
	@NotEmpty
	String dni;
	
	
	@NotEmpty
	String codigoPostal;
	
	@ManyToOne
    @JoinColumn(name="id_Rol", nullable=false)
    private Rol rol;
	
	@ManyToOne
    @JoinColumn(name="id_Localidad", nullable=false)
    private Localidad localidad;
	
	@ManyToOne
    @JoinColumn(name="id_Candidato", nullable=true)
    private Candidato candidato;
	
	public Usuario() {
		super();
	}

	public Usuario( Long id, @NotEmpty String nombre, @NotEmpty String apellido1, @NotEmpty String apellido2,
			@NotEmpty String username, @NotEmpty String password, @NotEmpty String correo, @NotEmpty String telefono,
			@NotEmpty String direccion,@NotEmpty String dni, @NotEmpty String codigoPostal, Rol rol, Localidad localidad, Candidato candidato) {
		super();
		Id = id;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.username = username;
		this.password = password;
		this.correo = correo;
		this.telefono = telefono;
		this.direccion = direccion;
		this.dni = dni;
		this.codigoPostal = codigoPostal;
		this.rol = rol;
		this.localidad = localidad;
		this.candidato = candidato;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public Candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}

	

	
	
	
	

}
