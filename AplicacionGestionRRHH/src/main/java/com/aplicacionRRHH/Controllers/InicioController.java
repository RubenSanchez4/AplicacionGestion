package com.aplicacionRRHH.Controllers;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.time.LocalDate;
import java.time.LocalTime;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.aplicacionRRHH.Dao.UsuarioDao;
import com.aplicacionRRHH.Estadisticas.GraficosEstadisticos;
import com.aplicacionRRHH.modelos.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class InicioController {
	
	@Autowired
	private UsuarioDao dao;

	@GetMapping("/inicio")
	public String inicio(Model model, HttpServletRequest request) {
		
		Usuario usuario = InicioController.autenticar(request, "gestor");
		model.addAttribute("usuario", usuario);
	
		return "Inicio";
	}
	
	
	
	@PostMapping("/login")
	public String login(HttpServletRequest request, Model model, @RequestParam("username") String username, @RequestParam("password") String password) {
		
		Usuario usuario = dao.login(username, password);
	
		if(usuario == null) {
			model.addAttribute("error", "Login incorrecto");
		}else {
			request.getSession().setAttribute("usuario",usuario);
			model.addAttribute("usuario", usuario);
		}
		
		
		return "Inicio";
	}
	
	@GetMapping("/logout")
	public String cerrarSesion(HttpServletRequest request) {
		
		request.getSession().invalidate();
		return "redirect:/inicio";
	}
	@GetMapping(value="/getDescargaBarras")
    public void getDescargaBarras(HttpSession session,HttpServletResponse response) throws Exception {
        try {
        	String fileName = GraficosEstadisticos.crearGraficoBarras();
            
            
            File fileToDownload = new File(fileName);

            InputStream inputStream = new FileInputStream(fileToDownload);
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment; filename=grafica-de-barras.png"); 
            IOUtils.copy(inputStream, response.getOutputStream());
            response.flushBuffer();
            inputStream.close();
        } catch (Exception exception){
            System.out.println(exception.getMessage());
        }

    }
	@GetMapping(value="/getDescargaPastel")
    public void getdescargaPastel(HttpSession session,HttpServletResponse response) throws Exception {
        try {
        	String fileName = GraficosEstadisticos.crearGraficoPastel();
            
            
            File fileToDownload = new File(fileName);

            InputStream inputStream = new FileInputStream(fileToDownload);
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment; filename=grafica-de-pastel.png"); 
            IOUtils.copy(inputStream, response.getOutputStream());
            response.flushBuffer();
            inputStream.close();
        } catch (Exception exception){
            System.out.println(exception.getMessage());
        }

    }

	
	// -------------------------------------------------------------------------------------------------------------------------
	// -------------------------------------------------------------------------------------------------------------------------
	// ------------------------------ MÉTODO DE AUTENTICACIÓN DE USUARIO -------------------------------------------------------
	// -------------------------------------------------------------------------------------------------------------------------
	// -------------------------------------------------------------------------------------------------------------------------
	
	public static Usuario autenticar(HttpServletRequest request, String nombreRol) {
		
		System.out.println("Se intenta autenticar como " + nombreRol);
		if (request.getSession() == null) {
			return null;
		}
		
		Usuario usuario = (Usuario)request.getSession().getAttribute("usuario");
		
		if (usuario == null) {
			System.out.println("Pero no estás logueado");
			return null;
		}
		
		System.out.println("Rol del usuario: " + usuario.getRol().getNombre());
		
		if (usuario != null && usuario.getRol().getNombre().equals(nombreRol)) {
			System.out.println("Autenticado con éxito");
			return usuario;
		}else {
			System.out.println("Pero el usuario actual es " + usuario.getRol());
			return null;
		}
		
	}
	
}
