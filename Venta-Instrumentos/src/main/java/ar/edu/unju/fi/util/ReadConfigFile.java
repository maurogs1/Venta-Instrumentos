package ar.edu.unju.fi.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
/*
 * Clase helper usada para leer los valores de decuento e intereses del archivo de cfg propuesto.
 */
public class ReadConfigFile {
	List<Double> result = new ArrayList<Double>();
	/*
	 * creamos una instancia de InputStream, que se encargara de recibir el path y stremear su contenido.
	 */
	InputStream inputStream;

	public List<Double> getPropValues() throws IOException {

		try {
			/*
			 * creamos una instancia de objeto tipo Properties
			 */
			Properties prop = new Properties();
			/*
			 * String con el nombre del archivo de configuracion a buscar
			 */
			String propFileName = "configurationSystem.cfg";
			/*
			 * el objeto inputStream, busca stremear el archivo cfg en el path
			 */
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}

			/*
			 * una vez stremeado el contenido, el objeto prop se encarga de obtener los valores, y se los asignamos a las variables correspondientes
			 */
			Double contado = Double.valueOf(prop.getProperty("descuento_contado"));
			Double tresCuotas = Double.valueOf(prop.getProperty("interes_3_cuotas"));
			Double seisCuotas = Double.valueOf(prop.getProperty("interes_6_cuotas"));
			Double doceCuotas = Double.valueOf(prop.getProperty("interes_12_cuotas"));
			result.add(contado);
			result.add(tresCuotas);
			result.add(seisCuotas);
			result.add(doceCuotas);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			/*
			 * se cierra el stream
			 */
			inputStream.close();
		}
		return result;
	}
}
