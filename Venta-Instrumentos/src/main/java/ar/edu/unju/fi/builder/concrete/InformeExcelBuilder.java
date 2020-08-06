package ar.edu.unju.fi.builder.concrete;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;

import ar.edu.unju.fi.builder.ReporteBuilder;
import ar.edu.unju.fi.dominio.Venta;
import ar.edu.unju.fi.dto.VentaDTO;
import ar.edu.unju.fi.enumerado.EnumMensajes;
import ar.edu.unju.fi.services.implementation.InstrumentoServiceImp;
import ar.edu.unju.fi.util.FechaUtil;
import ar.edu.unju.fi.util.NumeroUtil;

public class InformeExcelBuilder extends ReporteBuilder {
	private static Logger log = Logger.getLogger(InformeExcelBuilder.class);
	// Este será nuestro libro de trabajo.
	private HSSFWorkbook libro;
	// Este será nuestra hoja de calculo.
	private HSSFSheet hoja1;
	// Estos seran los campos de mi tabla informe
	private static String[] columnas = { "ID Venta", "Vendedor", "Instrumento", "Descripción", "Importe", "Fecha" };

	private static List<Venta> ventas;
	private static List<VentaDTO> ventasAdeudadas;
	private static String FILE;
	private static File archivoVentaExcel;
	private static FileOutputStream Salida;
	private static CreationHelper createHelper;

	public InformeExcelBuilder() {
		super();
	}

	public InformeExcelBuilder(List<Venta> ventas) {
		super();
		this.ventas = ventas;
		ventasAdeudadas = new ArrayList<VentaDTO>();
		this.FILE = "src/main/resources/ventas/informe" + FechaUtil.fechaConGuiones(new Date()) + ".xls";
	}

	@Override
	public void createDocument() {
		try {
			// Aquí instanciamos el libro..
			libro = new HSSFWorkbook();
			// Sirve para crear varias cosas como ser Hyperlink, RichTextString etc,
			createHelper = libro.getCreationHelper();
			// Creamos una hoja
			hoja1 = libro.createSheet("Informe de ventas adeudadas");

			// Creammos la instancia de un archivo y le indicamos la ruta de salida.
			archivoVentaExcel = new File(FILE);
			Salida = new FileOutputStream(archivoVentaExcel);
		} catch (FileNotFoundException e) {
			log.error(e.getMessage());
		}

	}

	@Override
	public void setDocument() {
		generarVentasAdeudadas();
		HSSFFont headerFont = libro.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setColor(IndexedColors.RED.getIndex());
		// Create a CellStyle with the font
		CellStyle headerCellStyle = libro.createCellStyle();
		headerCellStyle.setFont(headerFont);
		// Create a Row
		Row headerRow = hoja1.createRow(0);
		// Create cells
		for (int i = 0; i < columnas.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columnas[i]);
			cell.setCellStyle(headerCellStyle);
		}
	}

	@Override
	public void setTable() {
		// creamos una fila a partir de la segunda y rellenamos con los datos
		int indiceFila = 1;
		for (VentaDTO ventaDTO : ventasAdeudadas) {
			loadRow(indiceFila, ventaDTO);
		}
	}

	@Override
	public void saveDocument() {
		try {// Guardamos los cambios
			libro.write(Salida);
			// Cerramos el archivo.
			Salida.close();
			log.info("Guardado con exito!");
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	public void generarVentasAdeudadas() {
		for (int i = 0; i < ventas.size(); i++) {
			if (ventas.get(i).getEstado() != EnumMensajes.CUOTA_ESTADO_FINALIZADO.getdescripcion()
					&& ventas.get(i).getUsuario() != null) {
				ventasAdeudadas.add(new VentaDTO(ventas.get(i)));
			}
		}
	}
	
	public void loadRow(Integer indiceFila, VentaDTO ventaDTO) {
		Row row = hoja1.createRow(indiceFila++);
		row.createCell(0).setCellValue(ventaDTO.getId().toString());
		row.createCell(1).setCellValue(ventaDTO.getUsuario().getNombreApellido());
		row.createCell(2).setCellValue(ventaDTO.getInstrumento().getTipoInstrumento());
		row.createCell(3).setCellValue(ventaDTO.getInstrumento().getDescripcion());
		row.createCell(4).setCellValue("U$D " + NumeroUtil.dosDecimales(ventaDTO.getImporte()).toString());
		row.createCell(5).setCellValue(FechaUtil.fechaCorta(ventaDTO.getFecha()));
	}

}
