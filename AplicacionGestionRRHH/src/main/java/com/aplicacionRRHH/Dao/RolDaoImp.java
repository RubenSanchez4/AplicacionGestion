package com.aplicacionRRHH.Dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aplicacionRRHH.modelos.Rol;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class RolDaoImp implements RolDao{
	
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@SuppressWarnings("unchecked")
	public List<Rol> findRol() {
		List<Rol> listRol = em.createQuery("from Rol").getResultList();
		return listRol;
	}

	@Override
	@Transactional
	public void save(Rol rol) {
		if(rol.getId() !=null && rol.getId() > 0) {
			em.merge(rol);
		}
		else {
		em.persist(rol);
		}
	}

	@Override
	public Rol findOne(Long id) {
		return em.find(Rol.class, id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		em.remove(findOne(id));
	}

}
