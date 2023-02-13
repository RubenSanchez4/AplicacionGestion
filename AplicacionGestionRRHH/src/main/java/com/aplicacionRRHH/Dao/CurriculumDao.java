package com.aplicacionRRHH.Dao;

import java.time.LocalDate;
import java.util.List;

import com.aplicacionRRHH.modelos.Curriculum;

public interface CurriculumDao {
	public List<Curriculum> findCurriculum();

	public void save(Curriculum curriculum);

	public Curriculum findOne(Long id);

	public void delete(Long id);
	
	// PERSONALIZADOS

	public List<Curriculum> findCurriculumDesdeFecha(LocalDate fecha);
	
	public Long findLastCurriculumID();
}


