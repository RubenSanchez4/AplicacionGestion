package com.aplicacionRRHH.Dao;

import java.util.List;

import com.aplicacionRRHH.modelos.CurriculumParametros;
import com.aplicacionRRHH.modelos.Usuario;

public interface CurriculumParametrosDao {

	public List<CurriculumParametros> findCurriculumParametros();

	public void save(CurriculumParametros curriculumParametros);

	public CurriculumParametros findOne(Long id);

	public void delete(Long id);
	
	/*---- MÉTODOS PERSONALIZADOS -----*/
	
	public List<CurriculumParametros> findFromIDcurriculum (Long idCurriculum);
}
