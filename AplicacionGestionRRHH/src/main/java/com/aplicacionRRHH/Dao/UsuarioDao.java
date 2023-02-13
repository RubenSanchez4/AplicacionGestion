package com.aplicacionRRHH.Dao;

import java.util.List;

import com.aplicacionRRHH.modelos.Candidato;
import com.aplicacionRRHH.modelos.Localidad;
import com.aplicacionRRHH.modelos.Provincia;
import com.aplicacionRRHH.modelos.Usuario;

public interface UsuarioDao {
	
	
	
	public List<Usuario> findUsuario();

	public void save(Usuario usuario);

	public Usuario findOne(Long id);

	public void delete(Long id);
	

//	----- MÉTODOS PERSONALIZADOS -----
	
	public Usuario login (String username, String password);
	public List<Usuario> getAllGestores();

}
