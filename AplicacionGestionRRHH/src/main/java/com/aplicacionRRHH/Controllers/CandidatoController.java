package com.aplicacionRRHH.Controllers;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.aplicacionRRHH.Dao.CandidatoDao;
import com.aplicacionRRHH.Dao.CurriculumDao;
import com.aplicacionRRHH.Dao.CurriculumParametrosDao;
import com.aplicacionRRHH.Dao.LocalidadDao;
import com.aplicacionRRHH.Dao.ParametroDao;
import com.aplicacionRRHH.Estadisticas.GraficosEstadisticos;
import com.aplicacionRRHH.modelos.Candidato;
import com.aplicacionRRHH.modelos.Curriculum;
import com.aplicacionRRHH.modelos.CurriculumParametros;
import com.aplicacionRRHH.modelos.Parametro;
import com.aplicacionRRHH.modelos.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRSaver;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;

@Controller
public class CandidatoController {

	@Autowired
	private CandidatoDao daoCandidato;
	
	@Autowired
	private CurriculumDao daoCurriculum;
	
	@Autowired
	private LocalidadDao daoLocalidad;
	
	@Autowired
	private ParametroDao daoParametro;
	
	@Autowired
	private CurriculumParametrosDao daoCurriculumParametros;
	
	
	
	//----------------------------------------------------------------------------------------------
	//----------------------------------------- CANDIDATOS -----------------------------------------
	//----------------------------------------------------------------------------------------------
	
	@GetMapping("/candidatos")
	public String inicio(Model model, HttpServletRequest request){
		
		// -- INICIO AUTENTICACIÓN
		Usuario usuario = InicioController.autenticar(request, "gestor");
		
		if(usuario == null) {
			return "redirect:/inicio";
		}else {
			model.addAttribute("usuario", usuario);
		}
		// -- FIN AUTENTICACIÓN
		
		model.addAttribute("candidatos", daoCandidato.findCandidato());
		return "Candidatos";
	}
	
	@GetMapping("/candidatos/nuevo")
	public String nuevoCandidato(Map<String, Object> model, HttpServletRequest request){
		
		// -- INICIO AUTENTICACIÓN
		Usuario usuario = InicioController.autenticar(request, "gestor");
		
		if(usuario == null) {
			return "redirect:/inicio";
		}else {
			model.put("usuario", usuario);
			
		}
		// -- FIN AUTENTICACIÓN

		Candidato candidato = new Candidato();
		candidato.setLocalidad(daoLocalidad.findOne(3L));
		model.put("candidato", candidato);
		model.put("localidades", daoLocalidad.findLocalidad());
		return "NuevoCandidato";
	}
	
	@PostMapping("/candidatos/nuevo")
	public String crearCandidato(Candidato candidato, BindingResult result, Model model, HttpServletRequest request) {
		
		// -- INICIO AUTENTICACIÓN
		Usuario usuario = InicioController.autenticar(request, "gestor");
		
		if(usuario == null) {
			return "redirect:/inicio";
		}else {
			model.addAttribute("usuario", usuario);
		}
		// -- FIN AUTENTICACIÓN


		candidato.setLocalidad(daoLocalidad.findOne(3L));
		
		if(result.hasErrors()) {
			return "NuevoCandidato";
		}
		
		daoCandidato.save(candidato);
		return "redirect:/candidatos";
	}
	
	@GetMapping("candidato/ver/{id}")
	public String verCandidato(@PathVariable("id") long id, Map<String, Object> model, HttpServletRequest request){
		
		// -- INICIO AUTENTICACIÓN
		Usuario usuario = InicioController.autenticar(request, "gestor");
		
		if(usuario == null) {
			return "redirect:/inicio";
		}else {
			model.put("usuario", usuario);
		}
		// -- FIN AUTENTICACIÓN

		Candidato candidato = daoCandidato.findOne(id);
		model.put("candidato", candidato);
		model.put("localidades", daoLocalidad.findLocalidad());
		return "VerCandidato";
	}
	
	@PostMapping("/actualizar")
	public String actualizarCandidato(@Valid Candidato candidato, BindingResult result, Model model, HttpServletRequest request) {
		
		// -- INICIO AUTENTICACIÓN
		Usuario usuario = InicioController.autenticar(request, "gestor");
		
		if(usuario == null) {
			return "redirect:/inicio";
		}else {
			model.addAttribute("usuario", usuario);
		}
		// -- FIN AUTENTICACIÓN

		candidato.setLocalidad(daoLocalidad.findOne(3L));
		
		if(result.hasErrors()) {
			model.addAttribute("localidades", daoLocalidad.findLocalidad());
			return "VerCandidato";
		}
		
		daoCandidato.save(candidato);
		return "redirect:/candidatos";
	}
	
	@GetMapping(value="candidato/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id, Model model, HttpServletRequest request) {
		
		// -- INICIO AUTENTICACIÓN
		Usuario usuario = InicioController.autenticar(request, "gestor");
		
		if(usuario == null) {
			return "redirect:/inicio";
		}else {
			model.addAttribute("usuario", usuario);
		}
		// -- FIN AUTENTICACIÓN
		
		if(id > 0) {
			daoCandidato.delete(id);
		}
		return "redirect:/candidatos";
	}
	
	//----------------------------------------------------------------------------------------------
	//----------------------------------------- CURRICULUM -----------------------------------------
	//----------------------------------------------------------------------------------------------
	
	@GetMapping("/nuevoCurriculum/{id}")
	public String nuevoCurriculum(@PathVariable(value="id") Long id, Map<String, Object> model, HttpServletRequest request){
		
		// -- INICIO AUTENTICACIÓN
		Usuario usuario = InicioController.autenticar(request, "gestor");
		
		if(usuario == null) {
			return "redirect:/inicio";
		}else {
			model.put("usuario", usuario);
		}
		// -- FIN AUTENTICACIÓN

		Curriculum curriculum = new Curriculum();
		model.put("curriculum", curriculum);
		model.put("candidato", daoCandidato.findOne(id));
		model.put("parametros", daoParametro.findParametro());
		return "NuevoCurriculum";
	}
	
	@PostMapping("/nuevoCurriculum/{id}")
	public String crearCurriculum(@PathVariable(value="id") Long id, Curriculum curriculum, @RequestParam("file") MultipartFile file, @RequestParam("valoraciones") Integer[] valoraciones, Map<String, Object> model, HttpServletRequest request) {
		 System.out.println("ENTRANDO AL METODO");
		// -- INICIO AUTENTICACIÓN
		Usuario usuario = InicioController.autenticar(request, "gestor");
		
		if(usuario == null) {
			return "redirect:/inicio";
		}else {
			model.put("usuario", usuario);
		}
		// -- FIN AUTENTICACIÓN

		curriculum.setNombre(daoCandidato.findOne(id).getNombre() + "-cv.pdf");
		curriculum.setCandidato(daoCandidato.findOne(id));
		curriculum.setFecha(LocalDate.now());
		
		 
		/*if(file.getSize() > 37386) {
			
			model.put("curriculum", curriculum);
			model.put("candidato", daoCandidato.findOne(id));
			model.put("parametros", daoParametro.findParametro());
			model.put("errorArchivo","Demasiado grande, intentelo de nuevo con uno mas pequeño");
			return "NuevoCurriculum";
		}*/
		 System.out.println("EMPEZANDO A ESCRIBIR EL PDF");
			 String fileName = curriculum.getNombre();
				try {
					file.transferTo(new File("C:\\Curriculums\\" + fileName));
				} catch (IOException e) {
					e.printStackTrace();
				}
		 
				 System.out.println("PDF COMPLETADO"); 
		model.put("file", file);
		model.put("filesize", file.getSize());
 
 


		
		daoCurriculum.save(curriculum);
		
		 System.out.println("CURRICULM CREADO");
		
		Long lastCurriculumID = daoCurriculum.findLastCurriculumID();
		Curriculum lastCurriculum = daoCurriculum.findOne(lastCurriculumID);
		
		for (Parametro parametro : daoParametro.findParametro()) {
			CurriculumParametros curriculumParametro = new CurriculumParametros();

			curriculumParametro.setParametro(daoParametro.findOne(parametro.getId()));
			curriculumParametro.setCurriculum(lastCurriculum);
			curriculumParametro.setValoracion(5);
			
			daoCurriculumParametros.save(curriculumParametro);
		}
		
		return "redirect:/curriculum/" + curriculum.getId();
	}
	
	@GetMapping("curriculum/{id}")
	public String verCurriculum(@PathVariable("id") long id, Map<String, Object> model, HttpServletRequest request){
		
		// -- INICIO AUTENTICACIÓN
		Usuario usuario = InicioController.autenticar(request, "gestor");
		
		if(usuario == null) {
			return "redirect:/inicio";
		}else {
			model.put("usuario", usuario);
		}
		// -- FIN AUTENTICACIÓN

		Curriculum curriculum = daoCurriculum.findOne(id);
		
		model.put("curriculum", curriculum);
		model.put("candidato", curriculum.getCandidato());
		model.put("listaCurriculumParametros", daoCurriculumParametros.findFromIDcurriculum(id));
		return "VerCurriculum";
	}
	
	@PostMapping("curriculum/{id}/actualizar")
	public String actualizarCurriculum(@Valid Candidato candidato, BindingResult result, Model model, HttpServletRequest request) {
		
		// -- INICIO AUTENTICACIÓN
		Usuario usuario = InicioController.autenticar(request, "gestor");
		
		if(usuario == null) {
			return "redirect:/inicio";
		}else {
			model.addAttribute("usuario", usuario);
		}
		// -- FIN AUTENTICACIÓN
		

		candidato.setLocalidad(daoLocalidad.findOne(3L));
		
		if(result.hasErrors()) {
			return "VerCandidato";
		}
		
		daoCandidato.save(candidato);
		return "redirect:/candidatos";
	}
	
	@GetMapping(value="/eliminarCurriculum/{id}")
    public String eliminarCurriculum(@PathVariable(value="id") Long id, Model model, HttpServletRequest request) {
		
		// -- INICIO AUTENTICACIÓN
		Usuario usuario = InicioController.autenticar(request, "gestor");
		
		if(usuario == null) {
			return "redirect:/inicio";
		}else {
			model.addAttribute("usuario", usuario);
		}
		// -- FIN AUTENTICACIÓN


        if(id > 0) {
        	
        	for (CurriculumParametros currParr : daoCandidato.findOne(id).getCurriculum().getCurriculumParametros()) {
				daoCurriculumParametros.delete(currParr.getId());
			}
			
            daoCurriculum.delete(daoCandidato.findOne(id).getCurriculum().getId());
        }
        return "redirect:/candidatos";
    }
	
	
	
	 @GetMapping(value="/curriculum/descargar/{id}")
	 public void getLogFile(@PathVariable(value="id") Long id, HttpSession session,HttpServletResponse response) throws Exception {
	        try {
	            String fileName=daoCurriculum.findOne(id).getNombre();
	            String filePathToBeServed = "C:\\Curriculums\\";
	            File fileToDownload = new File(filePathToBeServed+fileName);

	            InputStream inputStream = new FileInputStream(fileToDownload);
	            response.setContentType("application/force-download");
	            response.setHeader("Content-Disposition", "attachment; filename=" + fileName); 
	            IOUtils.copy(inputStream, response.getOutputStream());
	            response.flushBuffer();
	            inputStream.close();
	        } catch (Exception exception){
	            System.out.println(exception.getMessage());
	        }

	    }
	 
	 /*
	 @Bean
	 public DataSource dataSource() {
	     return new EmbeddedDatabaseBuilder()
	       .setType(EmbeddedDatabaseType.HSQL)
	       .addScript("classpath:dbo-schema.sql")
	       .build();
	 }
	 */
	 
	@GetMapping(value="/report")
	 public void reporte(HttpServletResponse response){
			
			try {
				System.out.println("--1--");
				InputStream employeeReportStream = getClass().getResourceAsStream("C:\\\\Curriculums\\\\template.jrxml");
				
				System.out.println("--2--");
				File file = new File("C:\\Curriculums\\template.jrxml");
				
				System.out.println("--3--");
				JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
				
				//JRSaver.saveObject(jasperReport, "template.jasper");
				
				System.out.println("--4--");
				DataSource dataSource = daoCandidato.getDataSource();
				

				Map<String, Object> parameters = new HashMap<>();
				parameters.put("titulo", "Candidatos");
				parameters.put("dato", "Campo");
				//parameters.put("nombreVariable", "apellido2");
				//parameters.put("valorVariable", "aaa");
				
				System.out.println("--5--");
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource.getConnection());
				
				System.out.println("--6--");
				JRPdfExporter exporter = new JRPdfExporter();

				exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("candidatosReport.pdf"));

				SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
				reportConfig.setSizePageToContent(true);
				reportConfig.setForceLineBreakPolicy(false);

				SimplePdfExporterConfiguration exportConfig = new SimplePdfExporterConfiguration();
				exportConfig.setMetadataAuthor("agmsoft");
				exportConfig.setEncrypted(true);
				exportConfig.setAllowedPermissionsHint("PRINTING");

				exporter.setConfiguration(reportConfig);
				exporter.setConfiguration(exportConfig);
				System.out.println("--7--");
				exporter.exportReport();
				System.out.println("--8--");
				JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Curriculums\\report.pdf");
				
				System.out.println("-- PDF CREADO CON ÉXITO--");
				
				try {
		            File fileToDownload = new File("C:\\Curriculums\\report.pdf");
		            InputStream inputStream = new FileInputStream(fileToDownload);
		            response.setContentType("application/force-download");
		            response.setHeader("Content-Disposition", "attachment; filename=report-downloaded.pdf"); 
		            IOUtils.copy(inputStream, response.getOutputStream());
		            response.flushBuffer();
		            inputStream.close();
		        } catch (Exception exception){
		            System.out.println(exception.getMessage());
		        }
				
			} catch (JRException e) { e.printStackTrace(); } catch (SQLException e) {
				e.printStackTrace();
			}
	    }
	
}