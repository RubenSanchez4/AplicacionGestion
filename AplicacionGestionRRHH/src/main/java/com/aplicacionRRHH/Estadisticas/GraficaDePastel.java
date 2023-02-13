package com.aplicacionRRHH.Estadisticas;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class GraficaDePastel {

	public static void main(String[] args) {
		

	        DefaultPieDataset dataset= new DefaultPieDataset();
	        dataset.setValue("javaScript", 20);
	        dataset.setValue("java", 16);
	        dataset.setValue("TypeScript", 13);
	        dataset.setValue("python", 18);
	        dataset.setValue("angular", 11);
	        dataset.setValue("C#", 4);
	        dataset.setValue("otro", 8);

	        JFreeChart jfreeChart = ChartFactory.createPieChart("Lenguajes de Programación", dataset, true, true, true);

	        ChartPanel panel = new ChartPanel(jfreeChart);

	        //Creamos la ventana

	        JFrame ventana = new JFrame("Gráfica");
	        ventana.setVisible(true);
	        ventana.setSize(800,600);
	       // ventana.setDefaultCloseOperation(EXIT_ON_CLOSE);

	        ventana.add(panel);


	    }

}
