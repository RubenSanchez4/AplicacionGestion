package com.aplicacionRRHH.Controllers;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aplicacionRRHH.Dao.CandidatoDao;
import com.aplicacionRRHH.Dao.LocalidadDao;
import com.aplicacionRRHH.Dao.RolDao;
import com.aplicacionRRHH.Dao.UsuarioDao;
import com.aplicacionRRHH.modelos.Candidato;
import com.aplicacionRRHH.modelos.Convocatoria;
import com.aplicacionRRHH.modelos.Usuario;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class EmpleadosController {

	@Autowired
	private UsuarioDao daoUsuario;
	
	@Autowired
	private LocalidadDao daoLocalidad;
	
	@Autowired
	private CandidatoDao daoCandidato;
	
	@Autowired
	private RolDao daoRol;
	
	//private Usuario usuario;
	
	
	@GetMapping("/verEmpleado")
	public String inicio(Model model, HttpServletRequest request){
		
		// -- INICIO AUTENTICACIÓN
		Usuario usuario = InicioController.autenticar(request, "empleado");
		
		if(usuario == null) {
			return "redirect:/inicio";
		}else {
			model.addAttribute("usuario", usuario);
		}
		// -- FIN AUTENTICACIÓN
		
		
		model.addAttribute("usuario", daoUsuario.findUsuario().get(0));
		return "VerEmpleado";
	}
	
	@PostMapping("/actualizarEmpleado")
	public String actualizar(@Valid Usuario empleado, Model model, BindingResult result, HttpServletRequest request){
		
		// -- INICIO AUTENTICACIÓN
		Usuario usuario = InicioController.autenticar(request, "empleado");
		
		if(usuario == null) {
			return "redirect:/inicio";
		}else {
			model.addAttribute("usuario", usuario);
		}
		// -- FIN AUTENTICACIÓN
		
		
		if(result.hasErrors()) {
			return "VerEmpleado";
			
		}
		
		usuario.setRol(daoRol.findOne(3L));
		usuario.setCandidato(daoCandidato.findOne(1L));
		usuario.setLocalidad(daoLocalidad.findOne(1L));
		daoUsuario.save(empleado);
		return "redirect:/verEmpleado";
	}
	
}
