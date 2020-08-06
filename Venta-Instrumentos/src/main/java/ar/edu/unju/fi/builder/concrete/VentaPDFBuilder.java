package ar.edu.unju.fi.builder.concrete;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;

import org.apache.log4j.Logger;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import ar.edu.unju.fi.builder.ReporteBuilder;
import ar.edu.unju.fi.dominio.Venta;
import ar.edu.unju.fi.dto.VentaDTO;
import ar.edu.unju.fi.util.FechaUtil;
import ar.edu.unju.fi.util.NumeroUtil;

public class VentaPDFBuilder extends ReporteBuilder {
	private static Logger log = Logger.getLogger(VentaPDFBuilder.class);
	private static Document documento;
	private static PdfWriter escritor;
	private static String FILE;
	private static Venta venta;
	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	private static Font helvetica = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);

	public VentaPDFBuilder() {
		super();
	}

	public VentaPDFBuilder(Venta venta) {
		super();
		this.FILE = "src/main/resources/ventas/venta" + venta.getId().toString() + ".pdf";
		this.venta = venta;
	}

	@Override
	public void createDocument() {
		try {
			documento = new Document();
			escritor = PdfWriter.getInstance(documento, new FileOutputStream(FILE));
			documento.open();
		} catch (FileNotFoundException e) {
			log.error(e.getMessage());
		} catch (DocumentException e) {
			log.error(e.getMessage());
		}
	}

	@Override
	public void setDocument() {
		try {
			agregarFecha();
			agregarLogo();
			agregarInformacion();
		} catch (DocumentException e) {
			log.error(e.getMessage());
		} catch (MalformedURLException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	@Override
	public void setTable() {
		try {
			if (venta.isContado()) {
				documento.add(new Paragraph("Modalidad de pago: Contado", catFont));
				documento.add(new Paragraph(
						"Importe total con descuento: $" + NumeroUtil.dosDecimales(venta.getImporte()), catFont));
			} else {
				documento.add(new Paragraph("Modalidad de pago: Financiado", catFont));
				documento.add(new Paragraph(" ----------------------------------------------------------------------------------------------------------------------------------"));
				generarTablaCuotas();
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@Override
	public void saveDocument() {
		try {
			documento.add(new Paragraph("Reporte generado por: " + System.getProperty("user.name") + ", el dia: "
					+ FechaUtil.fechaCorta(new Date()), smallBold));
		} catch (DocumentException e) {
			log.error(e.getMessage());
		}
		documento.close();
		escritor.close();
		log.info("PDF Generado con exito...");
	}

	public static void agregarFecha() throws DocumentException {
		Paragraph fecha = new Paragraph(FechaUtil.fechaLarga(new Date()));
		fecha.setAlignment(Element.ALIGN_RIGHT);
		documento.add(fecha);
	}

	public static void agregarLogo() throws DocumentException, MalformedURLException, IOException {
		Image logoEmpresa = Image.getInstance("src/main/resources/imagenes/logo.jpg");
		logoEmpresa.scalePercent(10);
		documento.add(logoEmpresa);
		documento.add(new Paragraph("JUJUY INSTRUMENTOS", helvetica));
		documento.add(new Paragraph("San Salvador de Jujuy - Jujuy 1234", helvetica));
	}

	private static void agregarInformacion() throws DocumentException {

		Paragraph parrafo = new Paragraph();
		agregarSaltosDeLinea(parrafo, 2);
		parrafo.add(new Paragraph("Vendedor: " + venta.getUsuario().getNombreApellido(), catFont));
		parrafo.add(new Paragraph("Instrumento: " + venta.getInstrumento().getTipoInstrumento(), catFont));
		parrafo.add(new Paragraph("Descripcion: " + venta.getInstrumento().getDescripcion(), catFont));
		parrafo.add(new Paragraph(
				"Año de fabricación: " + NumeroUtil.aEntero(venta.getInstrumento().getAnioFabricacion()), catFont));
		parrafo.add(new Paragraph("Número de serie: " + venta.getInstrumento().getNumeroSerie(), catFont));
		String precio = NumeroUtil.dosDecimales(venta.getImporte()).toString();
		if(precio == null) {
			precio = "null";
		}
		parrafo.add(new Paragraph("Precio final en $" + precio, catFont));
		parrafo.add(new Paragraph("Fecha de venta: " + FechaUtil.fechaCorta(venta.getFecha()), catFont));
		documento.add(parrafo);
	}

	private static void agregarSaltosDeLinea(Paragraph paragraph, int number) throws DocumentException {
		for (int i = 0; i < number; i++) {
			documento.add(new Paragraph(" "));
		}
	}

	public static void generarTablaCuotas() { 
		PdfPTable tabla = new PdfPTable(5);

		try {
			tabla.addCell("Numero");
			tabla.addCell("Importe");
			tabla.addCell("Fecha de vencimiento");
			tabla.addCell("Fecha de pago");
			tabla.addCell("Estado");

			for (int i = 0; i<venta.getCuotas().size(); i++){ 
				tabla.addCell(venta.getCuotas().get(i).getNumeroCuota().toString()); 
				//Lo llene de if para comprobar si campos en null eran lo que impedian la generación del PDF
				//Efectivamente así
				//Prueba y error ....
				if(venta.getCuotas().get(i).getValor()==null) {
					tabla.addCell("null");
				} else {
					tabla.addCell("$" + NumeroUtil.dosDecimales(venta.getCuotas().get(i).getValor()).toString());
				}

				if(venta.getCuotas().get(i).getFechaVencimiento()==null) {
					tabla.addCell("null");
				} else {
					tabla.addCell(FechaUtil.fechaCorta(venta.getCuotas().get(i).getFechaVencimiento()));
				}

				if(venta.getCuotas().get(i).getFechaPago()==null) {
					tabla.addCell("-");
				} else {
					tabla.addCell(FechaUtil.fechaCorta(venta.getCuotas().get(i).getFechaPago()));
				}

				if(venta.getCuotas().get(i).getEstado()==null) {
					tabla.addCell("null");
				} else {
					tabla.addCell(venta.getCuotas().get(i).getEstado());
				} 
			} 
			documento.add(tabla);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}


}
