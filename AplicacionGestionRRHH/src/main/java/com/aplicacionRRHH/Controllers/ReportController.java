package com.aplicacionRRHH.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.aplicacionRRHH.Dao.CandidatoDao;

@Controller
public class ReportController {

	@Autowired
	private CandidatoDao daoCandidato;
	
	
	@GetMapping("/tabla")
	public String inicio(Model model){
		model.addAttribute("candidatos", daoCandidato.findCandidato());
		return "Tabla";
	}
	
	@GetMapping("/descargar")
	public String descargar(Model model){
		
		// Aquí cambios ---------------------------------
		System.out.println("Empezando a crear reporte");
		

		System.out.println("Reporte creado");
		// ---------------------------------
		
		model.addAttribute("candidatos", daoCandidato.findCandidato());
		return "redirect:/tabla";
	}
	
	
}