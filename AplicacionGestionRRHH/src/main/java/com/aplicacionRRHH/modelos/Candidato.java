package com.aplicacionRRHH.modelos;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Candidato implements Serializable{

	private static final long serialVersionUID = -1695989566727688651L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	Long Id;
	
	//---------------- CLAVES FORANEAS
	
		//-----> 1: id_localidad
		

	
	@NotEmpty
	String nombre;

	@NotEmpty
	String apellido1;

	@NotEmpty
	String apellido2;
	
	@NotEmpty
	String correo;

	@NotEmpty
	String telefono;

	@NotEmpty
	String dni;

	@NotEmpty
	String direccion;

	@NotEmpty
	String codigo_Postal;
	
	@ManyToOne
    @JoinColumn(name="id_Localidad", nullable=false)
    private Localidad localidad;
	
	@OneToMany(mappedBy="candidato")
    private Set<Usuario> usuarios;

	@OneToOne(mappedBy="candidato", optional=true)
    private Curriculum curriculum;

	
	public Candidato() {
		super();
	}

	public Candidato(@NotNull Long id, @NotEmpty String nombre, @NotEmpty String apellido1, @NotEmpty String apellido2,
			@NotEmpty String correo, @NotEmpty String telefono, @NotEmpty String dni, @NotEmpty String direccion,
			@NotEmpty String codigo_Postal, Localidad localidad, Set<Usuario> usuarios, Curriculum curriculum) {
		super();
		Id = id;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.correo = correo;
		this.telefono = telefono;
		this.dni = dni;
		this.direccion = direccion;
		this.codigo_Postal = codigo_Postal;
		this.localidad = localidad;
		this.usuarios = usuarios;
		this.curriculum = curriculum;
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

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCodigo_Postal() {
		return codigo_Postal;
	}

	public void setCodigo_Postal(String codigo_Postal) {
		this.codigo_Postal = codigo_Postal;
	}

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Curriculum getCurriculum() {
		return curriculum;
	}

	public void setCurriculum(Curriculum curriculum) {
		this.curriculum = curriculum;
	}

	
	

}