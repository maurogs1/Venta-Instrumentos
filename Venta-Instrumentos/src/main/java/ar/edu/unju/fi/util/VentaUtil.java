package ar.edu.unju.fi.util;

import java.io.IOException;
import java.util.List;

import javax.inject.Named;

import ar.edu.unju.fi.dominio.Venta;



@Named
public class VentaUtil {
	static ReadConfigFile properties;
	static List<Double> valoresCfg;
	public VentaUtil() throws IOException {
		 properties = new ReadConfigFile();
		 valoresCfg = properties.getPropValues();
	}
	
	/**
	 * Dependiendo la cantidad de cuotas, determina el interés o descuento que se necesita para cada tipo de venta.  
	 * @author mauro
	 * @param tipoDeVenta
	 * @param cantidadCuotas
	 * @return
	 * @throws IOException
	 */
	public static Double getInteresODescuento( int cantidadCuotas ) throws IOException {
		Double result = 0.0;
		result = devolverUnaCuota(cantidadCuotas,result);
		result = devolverTresCuota(cantidadCuotas,result);
		result = devolverSeisCuota(cantidadCuotas,result);
		result = devolverDoceCuota(cantidadCuotas,result);	
		NumeroUtil.dosDecimales(result);
		return result;
	}


	/**
	 * Dependiendo la cantidad de cuotas, determina el importe total de la venta
	 * @author mauro
	 * @param venta
	 * @return
	 * @throws IOException
	 */
	public static Double calcularImporteTotal(Venta venta) throws IOException {
		Double result = 0.0;
		result = calcularImporteContado(venta, result);
		result = calcularImporteTresCuotas(venta, result);
		result = calcularImporteSeisCuotas(venta, result);
		result = calcularImporteDoceCuotas(venta, result);
		result = NumeroUtil.dosDecimales(result);
		return result;
	}
	
	/**
	 * Convierte de dólares a pesos
	 * @param venta
	 * @return
	 * @throws IOException
	 */
	public static Double convertirDolaresPesos(Venta venta) throws IOException {
		Double result = null;
		result = ValorDolar.apiDolar(venta.getImporte());
		 result = NumeroUtil.dosDecimales(result);		
		return result;
	}


	
	
	public static Double devolverUnaCuota(int  cantidadCuotas, double result)  {
		if( cantidadCuotas == 1 && result == 0.0) {
			result =  valoresCfg.get(0);
		}
		return result;
	}
	public static Double devolverTresCuota(int  cantidadCuotas, double result)  {
		if( cantidadCuotas == 3 && result == 0.0) {
			result = valoresCfg.get(1);
		}
		return result;
	}
	public static Double devolverSeisCuota(int  cantidadCuotas, double result){
		if( cantidadCuotas == 6 && result == 0.0) {	
			result =  valoresCfg.get(2);			
		}
		 return result;
	}
	public static Double devolverDoceCuota(int  cantidadCuotas, double result)  {
		if( cantidadCuotas == 12 && result == 0.0) {
		 result = valoresCfg.get(3);		 
		}
		 return result;
	}
	
	public static Double calcularImporteContado(Venta venta, double result) {
		if(venta.isContado() && result == 0.0) {
			result = venta.getImporte() - (venta.getImporte() * valoresCfg.get(0));			
		}		
		return result;
	}
	
	public static Double calcularImporteTresCuotas(Venta venta, double result) {
		if(venta.isFinanciado() && venta.getCuotas().size() == 3 && result == 0.0) {
			result = venta.getImporte() + (venta.getImporte() * valoresCfg.get(1)); 
		}
		return result;
	}
	
	public static Double calcularImporteSeisCuotas(Venta venta, double result) {
		if(venta.isFinanciado() && venta.getCuotas().size() == 6 && result == 0.0) {
			result = venta.getImporte() + (venta.getImporte() * valoresCfg.get(2)); 
		}
		return result;
	}
	
	public static Double calcularImporteDoceCuotas(Venta venta, double result) {
		if(venta.isFinanciado() && venta.getCuotas().size() == 12 && result == 0.0) {
			result = venta.getImporte() + (venta.getImporte() * valoresCfg.get(3)); 
		}
		return result;
	}
}
