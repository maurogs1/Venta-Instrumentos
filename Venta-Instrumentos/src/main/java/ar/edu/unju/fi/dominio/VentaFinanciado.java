package ar.edu.unju.fi.dominio;


import java.io.IOException;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import ar.edu.unju.fi.util.FechaUtil;
import ar.edu.unju.fi.util.VentaUtil;
/**
 * Creación de la clase VentaFinanciado
 * Esta clase representa a las ventas financiadas, estas ventas tendrán un interés
 * @author mauro
 *
 */
@SuppressWarnings("serial")
@Entity
@DiscriminatorValue( value="Financiado" )
public class VentaFinanciado extends Venta {
	
	@Column(name="interes")
	private double interes;
	
	
	
	
	
	public VentaFinanciado() {
		this.interes = 0.0;
		this.importeTotal = 0.0;
	}

	
	public VentaFinanciado(Instrumento inst, Usuario user) throws IOException {
		super( inst, user);
		this.interes = 0.0;
		this.importeTotal = 0.0;
}
	
	public double getInteres() {
		return interes;
	}
	public void setInteres(double interes) {
		this.interes = interes;
	}
	@Override
	public boolean isContado() {
		return false;
	}
	@Override
	public boolean isFinanciado() {
		return true;
	}

		
	


}
