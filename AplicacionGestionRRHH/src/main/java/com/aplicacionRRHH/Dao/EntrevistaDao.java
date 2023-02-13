package com.aplicacionRRHH.Dao;

import java.util.List;

import com.aplicacionRRHH.modelos.Entrevista;

public interface EntrevistaDao {
	public List<Entrevista> findEntrevista();

	public void save(Entrevista entrevista);

	public Entrevista findOne(Long id);

	public void delete(Long id);
}


