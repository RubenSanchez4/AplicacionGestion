package com.aplicacionRRHH.Dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.springframework.stereotype.Repository;

import com.aplicacionRRHH.modelos.Candidato;
import com.aplicacionRRHH.modelos.Entrevista;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class CandidatoDaoImpl implements CandidatoDao{
	
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public DataSource getDataSource() {
		EntityManagerFactory entityManagerFactory = em.getEntityManagerFactory();
	    ConnectionProvider cp = ((SessionFactory) entityManagerFactory).getSessionFactoryOptions()
	            .getServiceRegistry()
	            .getService(ConnectionProvider.class);
	    return cp.unwrap(DataSource.class);
	    }
	
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Candidato> buscarCandidatos(Long idConvocatoria, Long idParametroOrden, Long idParametroFiltro) {
		
		//-----> (1) INICIO: OBTENER LISTA CANDIDATOS
		List<Candidato> listaCandidatos = em.createQuery("from Candidato").getResultList();
		System.out.println("--1-- Lista Candidatos al inicio: " + listaCandidatos.size());
		
		List<Entrevista> listaEntrevistas = em.createQuery("from Entrevista where convocatoria = " + idConvocatoria).getResultList();
		System.out.println("--2-- Lista Entrevistas: " + listaEntrevistas.size());
        

        List<Candidato> listaUsuariosEntrevista = new ArrayList<Candidato>();
        
        for(Entrevista entrevista : listaEntrevistas) {
        	listaUsuariosEntrevista.add(entrevista.getCandidato());
        }
		System.out.println("--3-- Lista UsuariosEntrevista: " + listaUsuariosEntrevista.size());

        listaCandidatos.removeAll(listaUsuariosEntrevista);
		System.out.println("--4-- Lista Candidatos al final: " + listaCandidatos.size());
		
		
		return listaCandidatos;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Candidato> findCandidato() {
		List<Candidato> listCandidato = em.createQuery("from Candidato").getResultList();
		return listCandidato;
	}

	@Override
	@Transactional
	public void save(Candidato candidato) {
		if(candidato.getId() !=null && candidato.getId() > 0) {
			em.merge(candidato);
		}
		else {
		em.persist(candidato);
		}
	}

	@Override
	public Candidato findOne(Long id) {
		return em.find(Candidato.class, id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		Candidato candidato = this.findOne(id);
		
		if(candidato.getCurriculum() != null) {
			 CurriculumDaoImpl daoCurriculum = new CurriculumDaoImpl();
			 daoCurriculum.delete(candidato.getCurriculum().getId());
		}
		em.remove(findOne(id));
	}

}
