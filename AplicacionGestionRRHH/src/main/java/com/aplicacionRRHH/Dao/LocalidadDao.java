package com.aplicacionRRHH.Dao;

import java.util.List;

import com.aplicacionRRHH.modelos.Localidad;

public interface LocalidadDao {

	public List<Localidad> findLocalidad();

	public void save(Localidad localidad);

	public Localidad findOne(Long id);

	public void delete(Long id);
}
