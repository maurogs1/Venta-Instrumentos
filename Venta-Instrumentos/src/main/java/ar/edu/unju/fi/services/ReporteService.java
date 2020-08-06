package ar.edu.unju.fi.services;

import java.util.List;

import ar.edu.unju.fi.dominio.Venta;

public interface ReporteService {
	/**
	 * Genera un reporte individual de una venta
	 * @author mauro
	 * @param venta
	 */
	public void crearReporteIndividual(Venta venta, String tipoDeReporte);
	
	/**
	 * Genera un reporte de todas las ventas adeudadas
	 * @author mauro
	 * @param ventas
	 */
	public Boolean crearVentasNoCanceladas(List<Venta> ventas);
}
