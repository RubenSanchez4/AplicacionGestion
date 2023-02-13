package com.aplicacionRRHH;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.aplicacionRRHH.Controllers.InicioController;
import com.aplicacionRRHH.modelos.Usuario;

@SpringBootTest
class AplicacionRrhhApplicationTests {
	
	
	@BeforeEach
	void initMetodoTest() {
	    System.out.println("Iniciando el Método");
	}
	
	/*@AfterEach
	void tearDown() {
	    System.out.println("Finalizando el Método de Prueba");

	}*/
	
	 @Test
	    @DisplayName("Probando la clase buscador controller")
	    void testBuscadorController() {
	
		 String nombre = "Javier";
	        Usuario  usuario = new Usuario();
	        usuario.setNombre("Javierrr");
	       String esperado = "Javier";
	        String real = usuario.getNombre();
	        assertNotNull(real, "el usuario no puede ser nulo");
	        assertEquals(esperado, real, "el nombre del usuario no es el que se esperaba");
	        assertTrue(real.equals("Javier"), "nombre usuario esperado debe ser igual a la real");
	           
	    
	// -- INICIO AUTENTICACIÓN
			//Usuario usuario = InicioController.autenticar(request, "gestor");
		  //Usuario usuario1 =new Usuario ("null");
			if(usuario == null) {
				//return "redirect:/inicio";
				System.out.println(false);
				System.out.println("El usuario introducido es nulo");
			}else {
				System.out.println("se ha introducido un usuario");
			}
				//model.addAttribute("usuario", usuario);
			}
			// -- FIN AUTENTICACIÓN

	 
	 @AfterEach
		void tearDown() {
		    System.out.println("Finalizando el Método de Prueba");

		}
	 

	@Test
	void contextLoads() {
	}

}
