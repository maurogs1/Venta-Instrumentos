package ar.edu.unju.fi.util;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import ar.edu.unju.fi.dominio.Venta;

public class PDFReport {

	private static Venta venta;

	// En este directorio que se encuentra dentro del proyecto se guardaran las
	// ventas realizadas.
	private static String FILE;

	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	private static Font helvetica = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);

	public PDFReport() {

	}

	public PDFReport(Venta venta) {
		this.venta = venta;
		this.FILE = "src/main/resources/ventas/venta" + venta.getId().toString() + ".pdf";
	}

	public void generarPDF() {
		try {
			// Se crea la instancia de un documento.
			Document documento = new Document();
			// Aquí es donde y con que nombre se guardara el PDF
			PdfWriter.getInstance(documento, new FileOutputStream(FILE));
			// Una vez abierto se debe ir modelando el reporte, desde aquí el documento esta
			// abierto.
			documento.open();
			// Aca se llama al método estatico que coloca una imagen en la parte
			// Superior izquierda del PDF
			agregarLogo(documento);
			agregarInformacionVenta(documento);
			documento.close();
			System.out.println("PDF Generado con exito...");
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}

	// Este método sirve para llenar de informacion el reporte PDF
	private static void agregarInformacionVenta(Document document) throws DocumentException {

		Paragraph parrafo = new Paragraph();
		agregarLineaEnBlanco(parrafo, 2);

		parrafo.add(new Paragraph("Vendedor: " + venta.getUsuario().getNombreApellido(), catFont));
		agregarLineaEnBlanco(parrafo, 1);

		parrafo.add(new Paragraph("Instrumento: " + venta.getInstrumento().getTipoInstrumento(), catFont));
		agregarLineaEnBlanco(parrafo, 1);

		parrafo.add(new Paragraph("Descripcion: " + venta.getInstrumento().getDescripcion(), catFont));
		agregarLineaEnBlanco(parrafo, 1);

		parrafo.add(new Paragraph(
				"Año de fabricación: " + NumeroUtil.aEntero(venta.getInstrumento().getAnioFabricacion()), catFont));
		agregarLineaEnBlanco(parrafo, 1);

		parrafo.add(new Paragraph("Número de serie: " + venta.getInstrumento().getNumeroSerie(), catFont));
		agregarLineaEnBlanco(parrafo, 1);

		parrafo.add(new Paragraph("Precio: $" + NumeroUtil.dosDecimales(venta.getImporte()), catFont));
		agregarLineaEnBlanco(parrafo, 1);

		if (venta.isFinanciado()) {
			parrafo.add(new Paragraph("Modalidad de pago: Financiado", catFont));
			agregarLineaEnBlanco(parrafo, 1);

			parrafo.add(new Paragraph("Cantidad de cuotas:" + venta.getCuotas().size(), catFont));
			agregarLineaEnBlanco(parrafo, 1);

			parrafo.add(new Paragraph("Importe total con interes: $" + NumeroUtil.dosDecimales(venta.getImporteTotal()),
					catFont));
			agregarLineaEnBlanco(parrafo, 1);

		} else {
			parrafo.add(new Paragraph("Modalidad de pago: Contado", catFont));
			agregarLineaEnBlanco(parrafo, 1);

			parrafo.add(new Paragraph(
					"Importe total con descuento: $" + NumeroUtil.dosDecimales(venta.getImporteTotal()), catFont));
			agregarLineaEnBlanco(parrafo, 1);
		}

		parrafo.add(new Paragraph("Fecha de venta: " + FechaUtil.fechaCorta(venta.getFecha()), catFont));
		agregarLineaEnBlanco(parrafo, 1);

		parrafo.add(new Paragraph("Reporte generado por: " + System.getProperty("user.name") + ", el dia: "
				+ FechaUtil.fechaCorta(new Date()), smallBold));
		agregarLineaEnBlanco(parrafo, 1);

		document.add(parrafo);
	}

	// Este método sirve para generar saltos de linea
	private static void agregarLineaEnBlanco(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	// Este método agrega la fecha actual en la parte superior derecha, el logo de
	// la empresa en la parte superior izquierda del PDF y una leyenda.
	public static void agregarLogo(Document documento) throws Exception {
		Paragraph fecha = new Paragraph(FechaUtil.fechaLarga(new Date()));
		fecha.setAlignment(Element.ALIGN_RIGHT);
		documento.add(fecha);
		Image logoEmpresa = Image.getInstance("src/main/resources/imagenes/logo.jpg");
		logoEmpresa.scalePercent(10);
		documento.add(logoEmpresa);
		documento.add(new Paragraph("JUJUY INSTRUMENTOS", helvetica));
		documento.add(new Paragraph("San Salvador de Jujuy - Jujuy 1234", helvetica));
	}

}
