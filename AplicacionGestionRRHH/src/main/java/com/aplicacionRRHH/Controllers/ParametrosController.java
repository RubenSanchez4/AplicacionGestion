package com.aplicacionRRHH.Controllers;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aplicacionRRHH.Dao.CandidatoDao;
import com.aplicacionRRHH.Dao.LocalidadDao;
import com.aplicacionRRHH.Dao.ParametroDao;
import com.aplicacionRRHH.modelos.Candidato;
import com.aplicacionRRHH.modelos.Convocatoria;
import com.aplicacionRRHH.modelos.Parametro;
import com.aplicacionRRHH.modelos.Usuario;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class ParametrosController {

	@Autowired
	private ParametroDao daoParametro;
	
	
	
	@GetMapping("/configParametros")
	public String inicio(Model model, HttpServletRequest request){
		
		// -- INICIO AUTENTICACIÓN
		Usuario usuario = InicioController.autenticar(request, "admin");
		
		if(usuario == null) {
			return "redirect:/inicio";
		}else {
			model.addAttribute("usuario", usuario);
		}
		// -- FIN AUTENTICACIÓN
		
		Parametro parametro = new Parametro();
		model.addAttribute("nuevoParametro", parametro);
		model.addAttribute("parametros", daoParametro.findParametro());
		return "ConfigParametros";
	}
	
	
	
	
	@PostMapping("/crearParametro")
	public String crearParametro(@Valid Parametro parametro, BindingResult result, Model model, HttpServletRequest request) {
		
		// -- INICIO AUTENTICACIÓN
		Usuario usuario = InicioController.autenticar(request, "admin");
		
		if(usuario == null) {
			return "redirect:/inicio";
		}else {
			model.addAttribute("usuario", usuario);
		}
		// -- FIN AUTENTICACIÓN
		
		if(result.hasErrors()) {
			return "ConfigParametros";
		}
		
		daoParametro.save(parametro);
		return "redirect:/configParametros";
	}
	
	@GetMapping(value="/eliminarParametro/{id}")
    public String eliminarParametro(@PathVariable(value="id") Long id) {
        if(id > 0) {
            daoParametro.delete(id);
        }
        return "redirect:/configParametros";
    }
}
