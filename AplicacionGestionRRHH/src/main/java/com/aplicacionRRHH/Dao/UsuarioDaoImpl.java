package com.aplicacionRRHH.Dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aplicacionRRHH.modelos.Candidato;
import com.aplicacionRRHH.modelos.Localidad;
import com.aplicacionRRHH.modelos.Provincia;
import com.aplicacionRRHH.modelos.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class UsuarioDaoImpl implements UsuarioDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	@SuppressWarnings("unchecked")
	public Usuario login(String username, String password) {
		
		List<Usuario> listUsuario = em.createQuery("from Usuario where username='"+username+"' and  password='" +password+ "' ").getResultList();
		if (listUsuario.size() == 0){
			return null;
		} 
		else if (listUsuario.size() == 1) {
			return listUsuario.get(0);
		}
		
		return null;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Usuario> getAllGestores() {
		
		List<Usuario> listaUsuarios = em.createQuery("from Usuario where rol = 2 ").getResultList();
		
		return listaUsuarios;
	}

	@Override
	@Transactional
	public void save(Usuario usuario) {
		if(usuario.getId() !=null && usuario.getId() > 0) {
			em.merge(usuario);
		}
		else {
		em.persist(usuario);
		}
	}

	@Override
	public Usuario findOne(Long id) {
		return em.find(Usuario.class, id);
	}
	

	@Override
	@Transactional
	public void delete(Long id) {
		em.remove(findOne(id));
	}
	
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Usuario> findUsuario() {
		List<Usuario> listUsuario = em.createQuery("from Usuario").getResultList();
		return listUsuario;
	}
	

}
