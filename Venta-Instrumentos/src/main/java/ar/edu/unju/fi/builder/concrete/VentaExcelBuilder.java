package ar.edu.unju.fi.builder.concrete;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;

import ar.edu.unju.fi.builder.ReporteBuilder;
import ar.edu.unju.fi.dominio.Cuota;
import ar.edu.unju.fi.dominio.Venta;
import ar.edu.unju.fi.dto.VentaDTO;
import ar.edu.unju.fi.util.FechaUtil;
import ar.edu.unju.fi.util.NumeroUtil;

public class VentaExcelBuilder extends ReporteBuilder {
	private static Logger log = Logger.getLogger(VentaExcelBuilder.class);
	// Este será nuestro libro de trabajo.
	private static HSSFWorkbook libro;
	// Este será nuestra hoja de calculo.
	private static HSSFSheet hoja1;
	private static String FILE;
	private static File archivoVentaExcel;
	private static FileOutputStream Salida;
	private static Venta venta;
	private static String[] columnas = { "Numero", "Importe", "Fecha Vencimiento", "Fecha pago", "Estado" };
	private static int indiceFila = 0;

	public VentaExcelBuilder() {
		super();
	}

	public VentaExcelBuilder(Venta venta) {
		super();
		this.venta = venta;
		this.FILE = "src/main/resources/ventas/venta" + venta.getId().toString() + ".xls";
	}

	@Override
	public void createDocument() {
		// Aquí instanciamos el libro y le agregamos una hoja.
		try {
			libro = new HSSFWorkbook();
			hoja1 = libro.createSheet("Reporte de venta");
			// Creammos la instancia de un archivo y le indicamos la ruta de salida.
			archivoVentaExcel = new File(FILE);
			Salida = new FileOutputStream(archivoVentaExcel);
		} catch (FileNotFoundException e) {
			log.error(e.getMessage());
		}
	}

	@Override
	public void setDocument() {
		agregarInformacion();
	}

	@Override
	public void setTable() {
		try {
			hoja1.getRow(indiceFila).getCell(1).setCellValue("Modalidad de pago: ");
			if (venta.isContado()) {
				hoja1.getRow(indiceFila).getCell(2).setCellValue("Contado");
				++indiceFila;
			} else {
				hoja1.getRow(indiceFila).getCell(2).setCellValue("Financiado");
				++indiceFila;
				mensajeCuotas();
				generarTablaColumnas();
				llenarCamposTablaCuotas();
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@Override
	public void saveDocument() {
		try {
			libro.write(Salida);
			Salida.close();
			log.info("Excel guardado con exito");
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	public static void agregarInformacion() {

		Object[][] informacionVenta = { { "", FechaUtil.fechaLarga(new Date()) }, { "", "" },
				{ "Reporte de Venta", "" }, { "Vendedor: ", venta.getUsuario().getNombreApellido() },
				{ "Instrumento: ", venta.getInstrumento().getTipoInstrumento() },
				{ "Descripción: ", venta.getInstrumento().getDescripcion() },
				{ "Año de Fabricación: ", NumeroUtil.aEntero(venta.getInstrumento().getAnioFabricacion()).toString() },
				{ "Número de serie: ", venta.getInstrumento().getNumeroSerie().toString() },
				{ "Precio final en $: ", NumeroUtil.dosDecimales(venta.getImporte()).toString() },
				{ "Fecha de venta: ", FechaUtil.fechaCorta(venta.getFecha()) }, };
		for (Object[] aBook : informacionVenta) {
			Row row = hoja1.createRow(++indiceFila);
			int columnCount = 0;
			for (Object field : aBook) {
				Cell cell = row.createCell(++columnCount);
				if (field instanceof String) {
					cell.setCellValue((String) field);
				} else if (field instanceof Integer) {
					cell.setCellValue((Integer) field);
				}
			}

		}
	}

	public static void mensajeCuotas() {
		// Creamos una fila
		Row mensajeCuotas = hoja1.createRow(indiceFila);
		Cell celdaMensajeCuotas = mensajeCuotas.createCell(1);
		celdaMensajeCuotas.setCellValue("Cuotas");
		++indiceFila;
	}

	public static void generarTablaColumnas() {
		// Creamos una fila
		Row camposTablaCuotas = hoja1.createRow(indiceFila);
		// Creamos celdas
		for (int i = 0; i < columnas.length; i++) {
			Cell celda = camposTablaCuotas.createCell(i + 1);
			celda.setCellValue(columnas[i]);
		}
		++indiceFila;
	}

	public static void llenarCamposTablaCuotas() {
		for (Cuota cuota : venta.getCuotas()) {
			Row fila = hoja1.createRow(indiceFila++);
			fila.createCell(1).setCellValue(cuota.getNumeroCuota());
			fila.createCell(2).setCellValue("$" + NumeroUtil.dosDecimales(cuota.getValor()).toString());
			fila.createCell(3).setCellValue(FechaUtil.fechaConGuiones(cuota.getFechaVencimiento()));
			fila.createCell(4).setCellValue(FechaUtil.fechaCorta(cuota.getFechaPago()));
			fila.createCell(5).setCellValue(cuota.getEstado());
		}
	}

}
