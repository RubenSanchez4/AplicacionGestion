package com.aplicacionRRHH.Dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aplicacionRRHH.modelos.Parametro;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class ParametroDaoImpl implements ParametroDao{
	
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@SuppressWarnings("unchecked")
	public List<Parametro> findParametro() {
		List<Parametro> listParametro = em.createQuery("from Parametro").getResultList();
		return listParametro;
	}

	@Override
	@Transactional
	public void save(Parametro parametro) {
		if(parametro.getId() !=null && parametro.getId() > 0) {
			em.merge(parametro);
		}
		else {
		em.persist(parametro);
		}
	}

	@Override
	public Parametro findOne(Long id) {
		return em.find(Parametro.class, id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		em.remove(findOne(id));
	}

}
