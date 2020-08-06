package ar.edu.unju.fi.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.hibernate.mapping.Set;

import ar.edu.unju.fi.dominio.Venta;

//Esta clase crea un PDF con informacion de una venta realizada
//Recibe un parametro venta de la clase Venta
public class ExcelReport {
	// Este será nuestro libro de trabajo.
	private HSSFWorkbook libro;
	// Este será nuestra hoja de calculo.
	private HSSFSheet hoja1;

	private static Venta venta;
	private static String FILE;

	public ExcelReport() {

	}

	public ExcelReport(Venta venta) {
		this.venta = venta;
		this.FILE = "src/main/resources/ventas/venta" + venta.getId().toString() + ".xls";
	}

	public void generarLibroExcel() {
		// Con se genera un libro de Excel
		try {
			// Aquí instanciamos el libro y le agregamos una hoja.
			libro = new HSSFWorkbook();
			hoja1 = libro.createSheet("Reporte de venta");

			// Creammos la instancia de un archivo y le indicamos la ruta de salida.
			File archivoVentaExcel = new File(FILE);
			FileOutputStream Salida = new FileOutputStream(archivoVentaExcel);

			// Con este método agregaremos información a la hoja de calculo.
			// colocarFecha(hoja1);
			// Agregamos la información de la venta en la hoja
			agregarInformacionDeLaVenta(hoja1);

			// Guardamos los cambios
			libro.write(Salida);
			// Cerramos el archivo.
			Salida.close();
			System.out.println("Guardado con exito!");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void colocarFecha(HSSFSheet hoja1) {
		// Ingresamos el número de fila que vamos a utilizar
		HSSFRow fila = hoja1.createRow(0);
		// Ahora indicamos la celda
		HSSFCell celda1 = fila.createCell((short) 0);
		// Vamos a colocar la fecha actual en formato largo en la primera celda de
		// nuestra hoja de Excel.
		HSSFRichTextString fecha = new HSSFRichTextString(FechaUtil.fechaLarga(new Date()));
		celda1.setCellValue(fecha);
		// Esta la vamos a dejar vacias para simular un salto de linea
		HSSFRow fila1 = hoja1.createRow(1);
		HSSFCell celda2 = fila.createCell((short) 0);
	}

	public static void agregarInformacionDeLaVenta(HSSFSheet hoja1) {
		// Con esto creo una matriz de objetos el cual voy a llenar con la informacion
		// de la venta.
		Object[][] informacionVenta = { { "", FechaUtil.fechaLarga(new Date()) }, { "", "" },
				{ "Reporte de Venta", "" }, { "", "" }, { "Vendedor: ", venta.getUsuario().getNombreApellido() },
				{ "Instrumento: ", venta.getInstrumento().getTipoInstrumento() },
				{ "Descripción: ", venta.getInstrumento().getDescripcion() },
				{ "Año de Fabricación: ", NumeroUtil.aEntero(venta.getInstrumento().getAnioFabricacion()) },
				{ "Número de serie: ", venta.getInstrumento().getNumeroSerie() },
				{ "Precio: ", "$" + NumeroUtil.dosDecimales(venta.getImporte()).toString() },
				{ "Precio final: $", NumeroUtil.dosDecimales(venta.getImporteTotal()).toString() },
				{ "Fecha de venta: ", FechaUtil.fechaCorta(venta.getFecha()) },
				{ "Reporte realizado el dia: ", FechaUtil.fechaCorta(new Date()) } };

		// Cargamos la matriz en Excel
		int rowCount = 0;
		for (Object[] aBook : informacionVenta) {
			Row row = hoja1.createRow(++rowCount);
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

}
