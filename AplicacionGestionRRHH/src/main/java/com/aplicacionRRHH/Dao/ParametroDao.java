package com.aplicacionRRHH.Dao;

import java.util.List;

import com.aplicacionRRHH.modelos.Candidato;
import com.aplicacionRRHH.modelos.Parametro;

public interface ParametroDao {
	public List<Parametro> findParametro();

	public void save(Parametro parametro);

	public Parametro findOne(Long id);

	public void delete(Long id);
}


