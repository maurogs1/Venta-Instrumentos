package ar.edu.unju.fi.dominio;

import static org.junit.Assert.*;

import org.apache.log4j.BasicConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ar.edu.unju.fi.bean.AppBean;
import ar.edu.unju.fi.exceptions.GeneralException;
import ar.edu.unju.fi.services.ReporteService;
import ar.edu.unju.fi.services.VentaService;
import ar.edu.unju.fi.services.implementation.ReporteServiceImp;
import ar.edu.unju.fi.services.implementation.VentaServiceImp;

public class ReporteTestCase {
	private ReporteService reporteService;
	private VentaService ventaService;

	@Before
	public void setUp() {
		BasicConfigurator.configure();
		reporteService =  AppBean.getBean(ReporteServiceImp.class);
		ventaService = AppBean.getBean(VentaServiceImp.class);

	}

	@After
	public void tearsDown() {
		reporteService = null;
		ventaService = null;
	}
	
	@Test
	public void generarReporteIndividual() {
		try {
			Venta ventaEncontrada = ventaService.buscarById(77);
			reporteService.crearReporteIndividual(ventaEncontrada, ".doc");
		} catch (GeneralException e) {
			e.printStackTrace();
		}
	}
}
