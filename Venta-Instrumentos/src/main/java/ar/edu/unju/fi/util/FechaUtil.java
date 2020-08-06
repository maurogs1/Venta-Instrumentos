package ar.edu.unju.fi.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class FechaUtil {

	/**
	 * Aumenta meses a una fecha determinada
	 * @author mauro
	 * @param fecha
	 * @param meses
	 * @return
	 */
	public static Date sumarFechas(Date fecha, int meses) {
		
		      Calendar calendar = Calendar.getInstance();
		      calendar.setTime(fecha); 
		      calendar.add(Calendar.MONTH, meses);  
		      return calendar.getTime();
		      
	}
	
	public static String fechaLarga(Date fecha) {
    	SimpleDateFormat formateador = new SimpleDateFormat("'San Salvador de Jujuy - Jujuy,'"
    			+ " dd 'de' MMMM 'de' yyyy", new Locale("ES"));
    	String fechaFormateada = formateador.format(fecha);
    	return fechaFormateada;
    }
    
    public static String fechaCorta(Date fecha) {
    	SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
    	if(fecha == null) {
    		return "-";
    	}
    	String fechaFormateada = formateador.format(fecha);
    	return fechaFormateada;
    }
	
    public static String fechaConGuiones(Date fecha) {
    	SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
    	String fechaFormateada = formateador.format(fecha);
    	return fechaFormateada;
    }
}
