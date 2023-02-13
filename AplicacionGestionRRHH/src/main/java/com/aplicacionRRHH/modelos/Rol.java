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
public class Rol implements Serializable{

	private static final long serialVersionUID = -219840383980255401L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	Long Id;
	
	@NotNull
	String nombre;
	
	@OneToMany(mappedBy="rol")
    private Set<Usuario> usuarios;

	public Rol() {
		super();
	}

	public Rol(@NotNull Long id, @NotNull String nombre) {
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
