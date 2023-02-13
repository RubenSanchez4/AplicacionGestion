package com.aplicacionRRHH.Dao;

import java.util.List;

import com.aplicacionRRHH.modelos.Rol;

public interface RolDao {
	public List<Rol> findRol();

	public void save(Rol rol);

	public Rol findOne(Long id);

	public void delete(Long id);
}


