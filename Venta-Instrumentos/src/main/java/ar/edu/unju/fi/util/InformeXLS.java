package ar.edu.unju.fi.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;

import com.itextpdf.text.Font;

import ar.edu.unju.fi.dominio.Venta;
import ar.edu.unju.fi.dto.VentaDTO;
import ar.edu.unju.fi.enumerado.EnumMensajes;

//Esta clase crea un Excel con informacion de todas las ventas que
//que aun no fueron canceladas, recibe un parametro una coleccion de ventas.
public class InformeXLS {

	// Este será nuestro libro de trabajo.
	private HSSFWorkbook libro;
	// Este será nuestra hoja de calculo.
	private HSSFSheet hoja1;
	// Estos seran los campos de mi tabla informe
	private static String[] columnas = { "ID Venta", "Vendedor", "Instrumento", "Descripción", "Importe", "Fecha" };

	private static List<Venta> ventas;
	private static List<VentaDTO> ventasAdeudadas;
	private static String FILE;

	public InformeXLS() {

	}

	public InformeXLS(List<Venta> ventas) {
		this.ventas = ventas;
		ventasAdeudadas = new ArrayList<VentaDTO>();
		this.FILE = "src/main/resources/ventas/informe" + FechaUtil.fechaConGuiones(new Date()) + ".xls";
	}

	public void generarInformeExcel() {

		for (int i = 0; i < ventas.size(); i++) {
			
			//Me daba todas Excepciones porque algunos usuarios en mi base de datos en la tabla ventas tenian el id en null
			if (ventas.get(i).getEstado() != EnumMensajes.CUOTA_ESTADO_FINALIZADO.getdescripcion()
					&& ventas.get(i).getUsuario() != null) {
				ventasAdeudadas.add(new VentaDTO(ventas.get(i)));
			}
		}
		
		for(int i = 0; i < ventasAdeudadas.size(); i++) {
			System.out.println(ventasAdeudadas.get(i).toString());
		}

		try {
			// Aquí instanciamos el libro..
			libro = new HSSFWorkbook();
			// Sirve para crear varias cosas como ser Hyperlink, RichTextString etc,
			CreationHelper createHelper = libro.getCreationHelper();
			// Creamos una hoja
			hoja1 = libro.createSheet("Informe de ventas adeudadas");

			// Creammos la instancia de un archivo y le indicamos la ruta de salida.
			File archivoVentaExcel = new File(FILE);
			FileOutputStream Salida = new FileOutputStream(archivoVentaExcel);

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

			// Create Other rows and cells with employees data
			int rowNum = 1;
			for (VentaDTO ventaDTO : ventasAdeudadas) {
				Row row = hoja1.createRow(rowNum++);
				row.createCell(0).setCellValue(ventaDTO.getId().toString());
				row.createCell(1).setCellValue(ventaDTO.getUsuario().getNombreApellido());
				row.createCell(2).setCellValue(ventaDTO.getInstrumento().getTipoInstrumento());
				row.createCell(3).setCellValue(ventaDTO.getInstrumento().getDescripcion());
				row.createCell(4).setCellValue("U$D" + NumeroUtil.dosDecimales(ventaDTO.getImporte()).toString());
				row.createCell(5).setCellValue(FechaUtil.fechaCorta(ventaDTO.getFecha()));
			}

			// Resize all columns to fit the content size
			for (int i = 0; i < columnas.length; i++) {
				hoja1.autoSizeColumn(i);
			}

			// Guardamos los cambios
			libro.write(Salida);
			// Cerramos el archivo.
			Salida.close();
			System.out.println("Guardado con exito!");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
