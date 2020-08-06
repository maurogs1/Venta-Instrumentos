package ar.edu.unju.fi.builder.director;

import ar.edu.unju.fi.builder.ReporteBuilder;
import ar.edu.unju.fi.dominio.Venta;

//Clase Director
public class ReporteDirector {
	
	private ReporteBuilder reporteBuilder;

	public ReporteBuilder getReporteBuilder() {
		return reporteBuilder;
	}

	public void setReporteBuilder(ReporteBuilder reporteBuilder) {
		this.reporteBuilder = reporteBuilder;
	}

	public void buildDocument() {
		reporteBuilder.createDocument();
		reporteBuilder.setDocument();
		reporteBuilder.setTable();
		reporteBuilder.saveDocument();
	}
	
}
