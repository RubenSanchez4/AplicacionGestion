package com.aplicacionRRHH.Dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aplicacionRRHH.modelos.Localidad;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class LocalidadDaoImpl implements LocalidadDao{
	
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@SuppressWarnings("unchecked")
	public List<Localidad> findLocalidad() {
		List<Localidad> listLocalidad = em.createQuery("from Localidad").getResultList();
		return listLocalidad;
	}

	@Override
	@Transactional
	public void save(Localidad localidad) {
		if(localidad.getId() !=null && localidad.getId() > 0) {
			em.merge(localidad);
		}
		else {
		em.persist(localidad);
		}
	}

	@Override
	public Localidad findOne(Long id) {
		return em.find(Localidad.class, id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		em.remove(findOne(id));
	}

}
