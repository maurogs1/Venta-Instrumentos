package ar.edu.unju.fi.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 
 * @author mauro
 *
 */

public class ConnectionUtil {
	
	private static final String PERSISTENCE_UNIT_NAME = "PERSISTENCE"; 
	private static EntityManagerFactory factory; //Conexión a la BD

	/**
	 * Establece la conexión a la base de datos
	 * @return
	 */
	public static EntityManagerFactory getEntityManagerFactory() {
		if (factory==null) {
			factory=Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		}
		return factory;				
	}
	
	/**
	 * Finaliza la conexión a la base de datos
	 */
	public static void shutdown() {
		if (factory!=null) {
			factory.close();
		}		
	}
	
}
