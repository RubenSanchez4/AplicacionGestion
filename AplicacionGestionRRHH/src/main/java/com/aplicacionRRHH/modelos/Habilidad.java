package com.aplicacionRRHH.modelos;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Habilidad implements Serializable{

	private static final long serialVersionUID = -8056353905639103057L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	Long Id;
	
	@NotNull
	String nombre;

	public Habilidad() {
		super();
	}

	public Habilidad(@NotNull Long id, @NotNull String nombre) {
		super();
		Id = id;
		this.nombre = nombre;
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
	
	
}
