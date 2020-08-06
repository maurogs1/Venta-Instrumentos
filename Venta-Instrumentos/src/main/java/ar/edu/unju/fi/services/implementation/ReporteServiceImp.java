package ar.edu.unju.fi.services.implementation;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import ar.edu.unju.fi.builder.ReporteBuilder;
import ar.edu.unju.fi.builder.concrete.InformeExcelBuilder;
import ar.edu.unju.fi.builder.concrete.VentaExcelBuilder;
import ar.edu.unju.fi.builder.concrete.VentaPDFBuilder;
import ar.edu.unju.fi.dao.VentaDao;
import ar.edu.unju.fi.dominio.Venta;
import ar.edu.unju.fi.services.ReporteService;

@Service
public class ReporteServiceImp implements ReporteService{

	@Inject VentaDao ventaDao;

	public void crearReporteIndividual(Venta venta,String tipoDeReporte) {

		ReporteBuilder reporte = getInstancia(venta, tipoDeReporte);
		reporte.createDocument();
		reporte.setDocument();
		reporte.setTable();
		reporte.saveDocument();		
	}

	public Boolean crearVentasNoCanceladas(List<Venta> ventas) {
		try {
			ReporteBuilder reporte = new InformeExcelBuilder(ventas);
			reporte.createDocument();
			reporte.setDocument();
			reporte.setTable();
			reporte.saveDocument();
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	public ReporteBuilder getInstancia(Venta venta,String tipoDeReporte) {
		if(tipoDeReporte == ".pdf") {
			VentaPDFBuilder reporte = new VentaPDFBuilder(venta);
			return reporte;
		}
		if(tipoDeReporte == ".xls") {
			VentaExcelBuilder reporte = new VentaExcelBuilder(venta);
			return reporte;
		}
		return null;
	}

}
