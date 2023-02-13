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
import jakarta.validation.constraints.NotNull;

@Entity
public class Localidad implements Serializable{

	private static final long serialVersionUID = -2945673221267191557L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	Long Id;
	
	@NotNull
	String nombre;
	
	@ManyToOne
    @JoinColumn(name="id_provincia", nullable=false)
    private Provincia provincia;
	
	@OneToMany(mappedBy="localidad")
    private Set<Usuario> usuarios;
	
	@OneToMany(mappedBy="localidad")
    private Set<Candidato> candidato;
	

	public Localidad() {
		super();
	}


	public Localidad( Long id, @NotNull String nombre, Provincia provincia, Set<Usuario> usuarios,
			Set<Candidato> candidato) {
		super();
		Id = id;
		this.nombre = nombre;
		this.provincia = provincia;
		this.usuarios = usuarios;
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


	public Provincia getProvincia() {
		return provincia;
	}


	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}


	public Set<Usuario> getUsuarios() {
		return usuarios;
	}


	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}


	public Set<Candidato> getCandidato() {
		return candidato;
	}


	public void setCandidato(Set<Candidato> candidato) {
		this.candidato = candidato;
	}


	@Override
	public String toString() {
		return "Localidad [Id=" + Id + ", nombre=" + nombre + ", provincia=" + provincia + ", usuarios=" + usuarios
				+ ", candidato=" + candidato + "]";
	}



	
	

}