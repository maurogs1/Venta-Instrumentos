package ar.edu.unju.fi.dominio;


public class Reporte {

	private String nombreComprador;

    private String NombreIntrumento;

    private Integer codigoInstrumento;

    private Double precio;

	public String getNombreComprador() {
		return nombreComprador;
	}

	public void setNombreComprador(String nombreComprador) {
		this.nombreComprador = nombreComprador;
	}

	public String getNombreIntrumento() {
		return NombreIntrumento;
	}

	public void setNombreIntrumento(String nombreIntrumento) {
		NombreIntrumento = nombreIntrumento;
	}

	public Integer getCodigoInstrumento() {
		return codigoInstrumento;
	}

	public void setCodigoInstrumento(Integer codigoInstrumento) {
		this.codigoInstrumento = codigoInstrumento;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Reporte [nombreComprador=" + nombreComprador + ", NombreIntrumento=" + NombreIntrumento
				+ ", codigoInstrumento=" + codigoInstrumento + ", precio=" + precio + "]";
	}
    
    
}
