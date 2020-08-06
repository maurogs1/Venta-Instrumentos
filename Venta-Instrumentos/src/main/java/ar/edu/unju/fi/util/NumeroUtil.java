package ar.edu.unju.fi.util;

import java.text.DecimalFormat;

public class NumeroUtil {

	public static Double dosDecimales(Double numero) {
		return Math.round(numero * 100.0) / 100.0;
	}
	
	public static Integer aEntero(Double numero) {
		return numero.intValue();
	}
	
	/**
	 * Devuelve un número con sólo dos decimales
	 * @param num
	 * @return
	 */
	public static Double obtenerDosDecimales(Double num) {
		DecimalFormat format = new DecimalFormat("#.##");
		format.format(num);
		return num;
	}
	
}
