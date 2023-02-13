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
public class CurriculumParametros implements Serializable{

	private static final long serialVersionUID = -2945673221267191557L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	Long Id;

	@NotNull
	Integer valoracion;
	
	@ManyToOne
    @JoinColumn(name="id_Curriculum", nullable=false)
    private Curriculum curriculum;
	
	@ManyToOne
    @JoinColumn(name="id_Parametro", nullable=false)
    private Parametro parametro;
	
	public CurriculumParametros() {
		super();
	}

	public CurriculumParametros(Long id, Curriculum curriculum, Parametro parametro, Integer valoracion) {
		super();
		Id = id;
		this.curriculum = curriculum;
		this.parametro = parametro;
		this.valoracion = valoracion;
	}


	public Long getId() {
		return Id;
	}


	public void setId(Long id) {
		Id = id;
	}


	public Curriculum getCurriculum() {
		return curriculum;
	}


	public void setCurriculum(Curriculum curriculum) {
		this.curriculum = curriculum;
	}
	
	
	
	public Parametro getParametro() {
		return parametro;
	}


	public void setParametro(Parametro parametro) {
		this.parametro = parametro;
	}

	
	public Integer getValoracion() {
		return valoracion;
	}


	public void setValoracion(Integer valoracion) {
		this.valoracion = valoracion;
	}



	
	

}