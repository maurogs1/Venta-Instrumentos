package ar.edu.unju.fi.dominio;


import java.io.IOException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import ar.edu.unju.fi.util.FechaUtil;
import ar.edu.unju.fi.util.VentaUtil;
/**
 * Creación de la clase VentaContado
 * Esta clase representa a las ventas a contado
 * Esta venta tendrá un descuento 
 * @author mauro
 *
 */

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue( value="Contado" )
public class VentaContado extends Venta{
	
	
	
	
	@Column(name="descuento")
	private double descuento;
	
	public VentaContado() {
		this.descuento = 0.0;
		this.importeTotal = 0.0;		
	}
	
	
	
	public VentaContado(Instrumento inst, Usuario user) throws IOException{
		super( inst, user);		
		this.descuento = 0.0;
		this.importeTotal = 0.0;
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	@Override
	public boolean isContado() {
		return true;
	}

	@Override
	public boolean isFinanciado() {
		return false;
	}



	
	
	

}
