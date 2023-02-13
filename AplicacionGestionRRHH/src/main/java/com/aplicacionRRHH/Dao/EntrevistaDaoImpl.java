package com.aplicacionRRHH.Dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aplicacionRRHH.modelos.Entrevista;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class EntrevistaDaoImpl implements EntrevistaDao{
	
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@SuppressWarnings("unchecked")
	public List<Entrevista> findEntrevista() {
		List<Entrevista> listEntrevista = em.createQuery("from Entrevista ORDER BY id").getResultList();
		return listEntrevista;
	}

	@Override
	@Transactional
	public void save(Entrevista entrevista) {
		if(entrevista.getId() !=null && entrevista.getId() > 0) {
			em.merge(entrevista);
		}
		else {
		em.persist(entrevista);
		}
	}

	@Override
	public Entrevista findOne(Long id) {
		return em.find(Entrevista.class, id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		em.remove(findOne(id));
	}

}
