package com.aplicacionRRHH.Estadisticas;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GraficosEstadisticos {

	/*public static void main(String[] args) {
		
		graficoDeBarras();
		

	}*/
	private static JFreeChart graficoDePastel() {
		DefaultPieDataset dataset= new DefaultPieDataset();
	       dataset.setValue("javaScript", 200);
	       dataset.setValue("java", 16);
	       dataset.setValue("TypeScript", 13);
	       dataset.setValue("python", 18);
	       dataset.setValue("angular", 11);
	       dataset.setValue("C#", 4);
	       dataset.setValue("otro", 8);

	       JFreeChart grafica = ChartFactory.createPieChart("Lenguajes de Programación", dataset, true, true, true);

		return grafica;
	}

	private static JFreeChart graficoDeBarras() {
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		final String Lenguaje1 = "Java";
		final String Lenguaje2 = "Python";
		final String Lenguaje3 = "Angular";

		data.addValue(350, Lenguaje1, "Lenguajes");
		data.addValue(28, Lenguaje2, "Lenguajes");
		data.addValue(23, Lenguaje3, "Lenguajes");

		JFreeChart grafica = ChartFactory.createBarChart3D("Lenguajes de Programación", "Lenguajes", "Frecuencia", data,
				PlotOrientation.VERTICAL, true, true, true);

		/*ChartPanel contenedor = new ChartPanel(grafica);
		JFrame ventana = new JFrame("Graficas en Java");
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setSize(400, 300);
		ventana.setVisible(true);
		ventana.setLocationRelativeTo(null);
		ventana.add(contenedor);*/
		
		return grafica;
	}
	
	public static String crearGraficoBarras() {
		
		JFreeChart grafica = graficoDeBarras();
		
		BufferedImage objBufferedImage=grafica.createBufferedImage(600,800);
		ByteArrayOutputStream bas = new ByteArrayOutputStream();
		        try {
		            ImageIO.write(objBufferedImage, "png", bas);
		        } catch (IOException e) {
		            e.printStackTrace();
		        }

		byte[] byteArray=bas.toByteArray();
		
		
		
		InputStream in = new ByteArrayInputStream(byteArray);
		BufferedImage image;
		String fichero="C:\\GraficasProyecto\\graficasBarras"+LocalDate.now() + "-" + LocalTime.now().getHour() + "-"+LocalTime.now().getMinute() + "-"+LocalTime.now().getSecond()+".png";
		try {
			image = ImageIO.read(in);
			
		
		File outputfile = new File(fichero);
		
			ImageIO.write(image, "png", outputfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fichero;
	}
	   
public static String crearGraficoPastel() {
		
		JFreeChart grafica = graficoDePastel();
		
		BufferedImage objBufferedImage=grafica.createBufferedImage(600,800);
		ByteArrayOutputStream bas = new ByteArrayOutputStream();
		        try {
		            ImageIO.write(objBufferedImage, "png", bas);
		        } catch (IOException e) {
		            e.printStackTrace();
		        }

		byte[] byteArray=bas.toByteArray();
		
		
		
		InputStream in = new ByteArrayInputStream(byteArray);
		BufferedImage image;
		String fichero="C:\\GraficasProyecto\\graficasPastel"+LocalDate.now() + "-" + LocalTime.now().getHour() + "-"+LocalTime.now().getMinute() + "-"+LocalTime.now().getSecond()+".png";
		try {
			image = ImageIO.read(in);
		
		File outputfile = new File(fichero);
		
			ImageIO.write(image, "png", outputfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fichero;
	}
	   
	

}
