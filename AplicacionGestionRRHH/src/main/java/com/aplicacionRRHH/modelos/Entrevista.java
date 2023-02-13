package com.aplicacionRRHH.modelos;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Entrevista implements Serializable{

	private static final long serialVersionUID = 265347191751328858L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	Long Id;
	
	@NotNull
	LocalDate dia;
	
	@NotNull
	LocalTime hora;
	
	@NotEmpty
	String lugar;

	String observaciones;
	
	Boolean realizada;
	
	Boolean contratado;

	@ManyToOne
    @JoinColumn(name="id_convocatoria", nullable=false)
    private Convocatoria convocatoria;
	
	@ManyToOne
    @JoinColumn(name="id_candidato", nullable=false)
    private Candidato candidato;
	
	@ManyToOne
    @JoinColumn(name="id_entrevistador", nullable=false)
    private Usuario entrevistador;
	
	
	
	public Entrevista() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Entrevista(@NotNull Long id, @NotNull LocalDate dia, @NotNull LocalTime hora, @NotNull String lugar, Convocatoria convocatoria, Candidato candidato, Usuario entrevistador, String observaciones, Boolean realizada, Boolean contratado) {
		super();
		Id = id;
		this.dia = dia;
		this.hora = hora;
		this.lugar = lugar;
		this.convocatoria = convocatoria;
		this.candidato = candidato;
		this.entrevistador = entrevistador;
		this.observaciones = observaciones;
		this.realizada = realizada;
		this.contratado = contratado;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public LocalDate getDia() {
		return dia;
	}

	public void setDia(LocalDate dia) {
		this.dia = dia;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	public Convocatoria getConvocatoria() {
		return convocatoria;
	}

	public void setConvocatoria(Convocatoria convocatoria) {
		this.convocatoria = convocatoria;
	}
	
	public Candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}
	
	public Usuario getEntrevistador() {
		return entrevistador;
	}

	public void setEntrevistador(Usuario entrevistador) {
		this.entrevistador = entrevistador;
	}
	
	public Boolean getRealizada() {
		return realizada;
	}

	public void setRealizada(Boolean realizada) {
		this.realizada = realizada;
	}
	
	public Boolean getContratado() {
		return contratado;
	}

	public void setContratado(Boolean contratado) {
		this.contratado = contratado;
	}
	
	
	
	

}