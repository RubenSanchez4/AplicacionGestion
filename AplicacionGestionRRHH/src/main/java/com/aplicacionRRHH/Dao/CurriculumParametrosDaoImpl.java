package com.aplicacionRRHH.Dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aplicacionRRHH.modelos.CurriculumParametros;
import com.aplicacionRRHH.modelos.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class CurriculumParametrosDaoImpl implements CurriculumParametrosDao{
	
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@SuppressWarnings("unchecked")
	public List<CurriculumParametros> findCurriculumParametros() {
		List<CurriculumParametros> listCurriculumParametros = em.createQuery("from CurriculumParametros").getResultList();
		return listCurriculumParametros;
	}

	@Override
	@Transactional
	public void save(CurriculumParametros curriculumParametros) {
		if(curriculumParametros.getId() !=null && curriculumParametros.getId() > 0) {
			em.merge(curriculumParametros);
		}
		else {
		em.persist(curriculumParametros);
		}
	}

	@Override
	public CurriculumParametros findOne(Long id) {
		return em.find(CurriculumParametros.class, id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		em.remove(findOne(id));
	}
	
	
	/*---------------- MÉTODOS PERSONALIZADOS -------------------*/
	
	@Override
	@SuppressWarnings("unchecked")
	public List<CurriculumParametros> findFromIDcurriculum (Long idCurriculum) {
		
		List<CurriculumParametros> listaCurriculumParametros = em.createQuery("from CurriculumParametros where curriculum=" + idCurriculum).getResultList();
		if (listaCurriculumParametros.size() > 0){
			return listaCurriculumParametros;
		}
		
		return null;
	}

}
