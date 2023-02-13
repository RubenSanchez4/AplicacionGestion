package com.aplicacionRRHH.Controllers;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.aplicacionRRHH.Dao.CandidatoDao;
import com.aplicacionRRHH.Dao.LocalidadDao;
import com.aplicacionRRHH.modelos.Candidato;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class OldCandidatoController {

	@Autowired
	private CandidatoDao daoCandidato;
	
	@Autowired
	private LocalidadDao daoLocalidad;
	
	//----------------------------------------------------------------------------------------------
	//----------------------------------------- CANDIDATOS -----------------------------------------
	//----------------------------------------------------------------------------------------------
	
	@GetMapping("/candidatos2")
	public String inicio(Model model, HttpServletRequest request){
		
		
		model.addAttribute("candidatos", daoCandidato.findCandidato());
		return "Candidatos2";
	}
	
	@GetMapping("/candidatos2/nuevo")
	public String nuevoCandidato(Map<String, Object> model, HttpServletRequest request){
		

		Candidato candidato = new Candidato();
		candidato.setLocalidad(daoLocalidad.findOne(3L));
		model.put("candidato", candidato);
		model.put("localidades", daoLocalidad.findLocalidad());
		return "NuevoCandidato";
	}
	
	@PostMapping("/candidatos2/nuevo")
	public String crearCandidato(Candidato candidato, BindingResult result, Model model, HttpServletRequest request) {

		candidato.setLocalidad(daoLocalidad.findOne(3L));
		
		if(result.hasErrors()) {
			return "NuevoCandidato";
		}
		
		daoCandidato.save(candidato);
		return "redirect:/candidatos";
	}
	
	@GetMapping("candidato2/ver/{id}")
	public String verCandidato(@PathVariable("id") long id, Map<String, Object> model, HttpServletRequest request){
		

		Candidato candidato = daoCandidato.findOne(id);
		model.put("candidato", candidato);
		model.put("localidades", daoLocalidad.findLocalidad());
		return "VerCandidato2";
	}
	
	@PostMapping("/actualizar2")
	public String actualizarCandidato(@Valid Candidato candidato, BindingResult result, Model model, HttpServletRequest request) {
		

		candidato.setLocalidad(daoLocalidad.findOne(3L));
		
		if(result.hasErrors()) {
			model.addAttribute("localidades", daoLocalidad.findLocalidad());
			return "VerCandidato";
		}
		
		daoCandidato.save(candidato);
		return "redirect:/candidatos";
	}
	
	@GetMapping(value="candidato2/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id, Model model, HttpServletRequest request) {
		
		
		if(id > 0) {
			daoCandidato.delete(id);
		}
		return "redirect:/candidatos";
	}
	
}