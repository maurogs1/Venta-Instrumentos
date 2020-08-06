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
	private static EntityManagerFactory factory; //Conexi�n a la BD

	/**
	 * Establece la conexi�n a la base de datos
	 * @return
	 */
	public static EntityManagerFactory getEntityManagerFactory() {
		if (factory==null) {
			factory=Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		}
		return factory;				
	}
	
	/**
	 * Finaliza la conexi�n a la base de datos
	 */
	public static void shutdown() {
		if (factory!=null) {
			factory.close();
		}		
	}
	
}
