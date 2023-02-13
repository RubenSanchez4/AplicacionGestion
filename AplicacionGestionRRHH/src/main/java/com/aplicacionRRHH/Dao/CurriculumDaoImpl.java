package com.aplicacionRRHH.Dao;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.aplicacionRRHH.modelos.Curriculum;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class CurriculumDaoImpl implements CurriculumDao{
	
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@SuppressWarnings("unchecked")
	public List<Curriculum> findCurriculum() {
		List<Curriculum> listCurriculum = em.createQuery("from Curriculum").getResultList();
		return listCurriculum;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Curriculum> findCurriculumDesdeFecha(LocalDate fecha) {
		List<Curriculum> listCurriculum = em.createQuery("from Curriculum where fecha > '2023-01-02'").getResultList();
		return listCurriculum;
	}
	
	@SuppressWarnings("unchecked")
	public Long findLastCurriculumID() {
		List<Curriculum> listCurriculum = em.createQuery("FROM Curriculum").getResultList();
		if(listCurriculum.size() > 0) {
			System.out.println("Tamaño de la lista: " + listCurriculum.size());
			return listCurriculum.get(listCurriculum.size()-1).getId();
		}
		
		return 0L;
	}

	@Override
	@Transactional
	public void save(Curriculum curriculum) {
		if(curriculum.getId() !=null && curriculum.getId() > 0) {
			em.merge(curriculum);
		}
		else {
			em.persist(curriculum);
		}
	}

	@Override
	public Curriculum findOne(Long id) {
		return em.find(Curriculum.class, id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		em.remove(findOne(id));
	}

}
