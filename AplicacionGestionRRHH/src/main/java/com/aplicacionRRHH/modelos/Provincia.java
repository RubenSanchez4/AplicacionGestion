package com.aplicacionRRHH.modelos;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

@Entity
public class Provincia implements Serializable{

	private static final long serialVersionUID = -7247621010609889471L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	Long Id;
	
	@NotNull
	String nombre;
	
	@OneToMany(mappedBy="provincia")
    private Set<Localidad> localidad;

	public Provincia() {
		super();
	}

	public Provincia( Long id, @NotNull String nombre, Set<Localidad> localidad) {
		super();
		Id = id;
		this.nombre = nombre;
		this.localidad = localidad;
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

	public Set<Localidad> getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Set<Localidad> localidad) {
		this.localidad = localidad;
	}



	

	
}
