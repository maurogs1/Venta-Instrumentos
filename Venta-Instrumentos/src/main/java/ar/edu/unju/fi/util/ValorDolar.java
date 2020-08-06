package ar.edu.unju.fi.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.inject.Named;

import org.json.JSONArray;
import org.json.JSONObject;

import ar.edu.unju.fi.dominio.Venta;

/*
 * Clase usada para pasar el precio de USD a ARS
 */

@Named
public class ValorDolar {
	/*
	 * APIENDPOINT es la url con los parametros de la api propuesta, siempre se declaran como constantes
	 */
	private static final String APIENDPOINT = "http://currencies.apps.grandtrunk.net/getlatest/usd/ars";
	public Double convertirAPesos(Double precio) {

		return null;
	}
	/*
	 * Param Double precio : el precio del instrumento a convertir
	 */
	public static  Double apiDolar(Double precio) throws IOException {

		String urly = APIENDPOINT;
		/*
		 * VAriable url : se crea una instacia de objeto URL, con la constante declarada.
		 */
		URL url = new URL(urly);
		/*
		 * Se crea una instancia de HttpURLConnection, la cual sera la que ejecutara el cURL
		 */
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		/*
		 * Se setea el tipo de request en el objeto conn, en este caso usamos una peticion tipo GET
		 */
		conn.setRequestMethod("GET");

		/*
		 * Creamos una instancia de BufferReader, que lee lo que llegara luego de que conn ejecute el cURL
		 */
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String output;
		/*
		 * Leemos linea por linea el response que esta en el buffer reader
		 */
		StringBuffer response = new StringBuffer();
		while ((output = in.readLine()) != null) {
			response.append(output);
		}
		/*
		 * cerramos el buffer reader
		 */
		in.close();
		 /*
		  * procesamos el response y lo usamos para calcular el precio del instrumento
		  */
		String valor = response.toString();
		Double cotizacion = Double.valueOf(valor);
		return cotizacion * precio;
		
	}



}
