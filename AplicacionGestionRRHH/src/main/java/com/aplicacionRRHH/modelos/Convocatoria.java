package com.aplicacionRRHH.modelos;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Convocatoria {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	Long Id;
	
	@NotEmpty
	@Column(name = "puesto_Trabajo")
	String puestoTrabajo;
	

	@NotNull
	@Column(name = "fecha_Inicio")
	LocalDate fechaInicio;
	
	@Column(name = "fecha_Fin")
	LocalDate fechaFin;
	
	@NotEmpty
	String lugar;

	@OneToMany(mappedBy="convocatoria")
    private Set<Entrevista> entrevistas;
	
	public Convocatoria() {
		super();
	}

	public Convocatoria(Long id, @NotEmpty String puestoTrabajo, @NotNull LocalDate fechaInicio, @NotNull LocalDate fechaFin, @NotEmpty String lugar, Set<Entrevista> entrevistas) {
		super();
		Id = id;
		this.puestoTrabajo = puestoTrabajo;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.lugar = lugar;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getPuestoTrabajo() {
		return puestoTrabajo;
	}

	public void setPuestoTrabajo(String puestoTrabajo) {
		this.puestoTrabajo = puestoTrabajo;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}
	
	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	
	public Set<Entrevista> getEntrevistas() {
		return entrevistas;
	}

	public void setEntrevistas(Set<Entrevista> entrevistas) {
		this.entrevistas = entrevistas;
	}
	
	
	
}
