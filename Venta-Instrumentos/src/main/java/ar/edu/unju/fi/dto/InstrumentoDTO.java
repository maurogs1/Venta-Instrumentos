package ar.edu.unju.fi.dto;

import ar.edu.unju.fi.dominio.Marca;
import ar.edu.unju.fi.dominio.Pais;

public class InstrumentoDTO {

	
	private Long id;
	private String numeroSerie;	
	private String descripcion;	
	private double precioUsd;
	private double anioFabricacion;
	private Pais pais;	
	private Marca marca;
	private String tipoInstrumento;	
	private String estado;

	
	/**
	 * Constructor por defecto
	 */
	public InstrumentoDTO() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Creación del constructor con parámetros
	 * @param numeroSerie   		
	 * @param descripcion			
	 * @param precioUsd				
	 * @param anioFabricacion 		
	 * @param pais					
	 * @param marca
	 * @param tipoInstrumento
	 * @param estado				
	 */
	public InstrumentoDTO(String numeroSerie, String descripcion, double precioUsd, double anioFabricacion,
			Pais pais, Marca marca, String tipoInstrumento, String estado) {
		this.numeroSerie = numeroSerie;
		this.descripcion = descripcion;
		this.precioUsd = precioUsd;
		this.anioFabricacion = anioFabricacion;
		this.pais = pais;
		this.marca = marca;
		this.tipoInstrumento = tipoInstrumento;
		this.estado = estado;
	}

	public String getNumeroSerie() {
		return numeroSerie;
	}

	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecioUsd() {
		return precioUsd;
	}

	public void setPrecioUsd(double precioUsd) {
		this.precioUsd = precioUsd;
	}

	public double getAnioFabricacion() {
		return anioFabricacion;
	}

	public void setAnioFabricacion(double anioFabricacion) {
		this.anioFabricacion = anioFabricacion;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public String getTipoInstrumento() {
		return tipoInstrumento;
	}

	public void setTipoInstrumento(String tipoInstrumento) {
		this.tipoInstrumento = tipoInstrumento;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Instrumento [id=" + id + ", numeroSerie=" + numeroSerie + ", descripcion=" + descripcion
				+ ", precioUsd=" + precioUsd + ", anioFabricacion=" + anioFabricacion + ", pais=" + pais + ", marca="
				+ marca + ", tipoInstrumento=" + tipoInstrumento + ", estado=" + estado + "]";
	}

	
}
