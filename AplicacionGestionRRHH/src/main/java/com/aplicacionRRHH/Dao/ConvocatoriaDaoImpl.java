package com.aplicacionRRHH.Dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aplicacionRRHH.modelos.Convocatoria;
import com.aplicacionRRHH.modelos.Entrevista;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class ConvocatoriaDaoImpl implements ConvocatoriaDao {

	@Autowired
	private EntrevistaDao daoEntrevista;
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@SuppressWarnings("unchecked")
	public List<Convocatoria> findConvocatoria() {
		List<Convocatoria> listaConvocatoria = em.createQuery("from Convocatoria").getResultList();
		return listaConvocatoria;
	}

	@Override
	@Transactional
	public void save(Convocatoria convocatoria) {

		if (convocatoria.getId() != null && convocatoria.getId() > 0) {
			em.merge(convocatoria);
		} else {
			em.persist(convocatoria);
		}

	}
	

	@Override
	public Convocatoria findOne(Long id) {
		return em.find(Convocatoria.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void delete(Long id) {
		
		for(Entrevista entrevista : em.find(Convocatoria.class, id).getEntrevistas()) {
			daoEntrevista.delete(entrevista.getId());
		}
		
		em.remove(findOne(id));
	}

}
