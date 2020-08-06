package ar.edu.unju.fi.dominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ar.edu.unju.fi.bean.AppBean;
import ar.edu.unju.fi.dto.VentaDTO;
import ar.edu.unju.fi.exceptions.GeneralException;
import ar.edu.unju.fi.services.InstrumentoService;
import ar.edu.unju.fi.services.ReporteService;
import ar.edu.unju.fi.services.UsuarioService;
import ar.edu.unju.fi.services.VentaService;
import ar.edu.unju.fi.services.implementation.InstrumentoServiceImp;
import ar.edu.unju.fi.services.implementation.ReporteServiceImp;
import ar.edu.unju.fi.services.implementation.UsuarioServiceImp;
import ar.edu.unju.fi.services.implementation.VentaServiceImp;

public class VentaTestCase {
	private ReporteService reporteService;
	private VentaService ventaService;
	private InstrumentoService instrumentoService;
	private UsuarioService usuarioService;


	@Before
	public void setUp() {
		BasicConfigurator.configure();
		reporteService =  AppBean.getBean(ReporteServiceImp.class);
		ventaService = AppBean.getBean(VentaServiceImp.class);
		instrumentoService = AppBean.getBean(InstrumentoServiceImp.class);
		usuarioService = AppBean.getBean(UsuarioServiceImp.class);
	}

	@After
	public void tearsDown() {
		ventaService = null;
		instrumentoService = null;
		usuarioService = null;
		reporteService = null;
	}

	//@Test
	public void ventaFinanciadaTest()  {
		try {
			Instrumento buscarInstrumento = instrumentoService.buscarById(3);
			Usuario usuarioActivo = usuarioService.buscarById(14);
			VentaDTO venta = new VentaDTO(buscarInstrumento, usuarioActivo);
			ventaService.determinarCantidadCuotas(venta, 3);
			ventaService.add(venta);
			Venta buscarVenta = ventaService.buscarById(venta.getId());
			assertNotNull(buscarVenta);
		} catch (GeneralException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	//@Test
	public void ventaContadoTest() {

		try {
			Usuario usuarioActivo = usuarioService.buscarById(5);
			Instrumento buscarInstrumento = instrumentoService.buscarById(4);
			VentaDTO venta = new VentaDTO(buscarInstrumento, usuarioActivo);
			ventaService.determinarCantidadCuotas(venta, 1);
			ventaService.add(venta);
			Venta buscarVenta = ventaService.buscarById(venta.getId());
			assertNotNull(buscarVenta);	
		} catch (GeneralException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// @Test
	public void updateVentaDtotest()  {

		try {
			VentaDTO buscarVenta = ventaService.buscarVenta(53);
			buscarVenta.setImporte(100000.00);
			ventaService.update(buscarVenta);
			Venta buscarVentaActualizada = ventaService.buscarById(9);
			assertEquals(buscarVentaActualizada.getImporte(), buscarVenta.getImporte());
		} catch (GeneralException e) {
			e.printStackTrace();
		}

	}

	// @Test
	public void deleteVentaTest() {
		try {
			VentaDTO buscarVenta = ventaService.buscarVenta(10);
			ventaService.delete(buscarVenta);
			Venta buscarVenta2 = ventaService.buscarById(10);
			assertNull(buscarVenta2);
		} catch (GeneralException e) {
			e.printStackTrace();
		}

	}

	//@Test
	public void buscarPagarCuotaTest() throws IOException, GeneralException {
		Venta venta = ventaService.buscarById(1);
		Cuota c = ventaService.pagarCuota(venta);
		assertNotNull(c.getFechaPago());
	}

	// @Test
	public void buscarTotalEnUnaVentaTest() throws GeneralException {
		Venta buscarVenta = ventaService.buscarById(61);
		Double total = ventaService.verImporteTotalDeUnaVenta(buscarVenta);
		assertTrue((double) buscarVenta.getImporteTotal() == (double) total);
	}

	//@Test
	public void calcularTotalDeudaTest() throws IOException {
		Double totalDeuda = ventaService.getDeudaVentas();
		assertNotNull(totalDeuda);
	}


	@Test
	public void testReportePDF() throws IOException, GeneralException {
		try {
			Venta ventaEncontrada = ventaService.buscarById(1);
			reporteService.crearReporteIndividual(ventaEncontrada, ".pdf");
			assertNotNull(ventaEncontrada);
		} catch (GeneralException e) {
			e.printStackTrace();
		}
	}


	//@Test
	public void testReporteExcel() throws IOException, GeneralException {
		try {
			Venta ventaEncontrada = ventaService.buscarById(1);
			reporteService.crearReporteIndividual(ventaEncontrada, ".xls");
			assertNotNull(ventaEncontrada);
		} catch (GeneralException e) {
			e.printStackTrace();
		}
	}

//	@Test
	public void testReporteGeneralVentas() throws IOException, GeneralException {
		List<Venta> ventas = ventaService.obtenerTodos();
		Boolean response = reporteService.crearVentasNoCanceladas(ventas);
		assertTrue(response);
	}

}
