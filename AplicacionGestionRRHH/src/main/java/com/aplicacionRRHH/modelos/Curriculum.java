package com.aplicacionRRHH.modelos;

import java.io.Serializable;
import java.time.LocalDate;
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
import jakarta.validation.constraints.NotNull;

@Entity
public class Curriculum implements Serializable{

	private static final long serialVersionUID = -4851170669989674220L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	Long Id;
	
	@NotNull
	String nombre;

	@NotNull
	@Column(name = "fecha")
	LocalDate fecha;
	
	@OneToOne
    @JoinColumn(name="id_Candidato", nullable=false)
    private Candidato candidato;
	
	
	@OneToMany(mappedBy="curriculum")
    private Set<CurriculumParametros> curriculumParametros;
	
	public Curriculum() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Curriculum(@NotNull Long id, @NotNull  String nombre, Candidato candidato, Set<CurriculumParametros> curriculumParametros, LocalDate fecha) {
		super();
		Id = id;
		this.nombre = nombre;
		this.candidato = candidato;
		this.curriculumParametros = curriculumParametros;
		this.fecha = fecha;
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
	
	public Candidato getCandidato() {
		return candidato;
	}


	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}
	
	public Set<CurriculumParametros> getCurriculumParametros() {
		return curriculumParametros;
	}

	public void setCurriculumParametros(Set<CurriculumParametros> curriculumParametros) {
		this.curriculumParametros = curriculumParametros;
	}
	
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}

	
	

}