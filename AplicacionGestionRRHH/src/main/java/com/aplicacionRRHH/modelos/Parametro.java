package com.aplicacionRRHH.modelos;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Parametro implements Serializable {

	private static final long serialVersionUID = -4943651289912393695L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	Long Id;

	@NotEmpty
	String nombre;

	public Parametro() {
		super();
	}

	public Parametro( Long id, @NotEmpty String nombre) {
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
