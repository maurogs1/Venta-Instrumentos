package ar.edu.unju.fi.dao.implementation;

import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import ar.edu.unju.fi.dao.GenericDao;
import ar.edu.unju.fi.enumerado.EnumMensajes;
import ar.edu.unju.fi.services.implementation.VentaServiceImp;
import ar.edu.unju.fi.util.ConnectionUtil;

/**
 * 
 * @author mauro
 *
 */

@Named
public class GenericDaoImp<T> implements GenericDao<T> {
	private Class<T> nombreClase;
	protected EntityManager entity = ConnectionUtil.getEntityManagerFactory().createEntityManager();
	private static Logger log = Logger.getLogger(VentaServiceImp.class);
	
	public GenericDaoImp(Class<T> clase) {
		this.nombreClase = clase;
	}
	
	public GenericDaoImp() {
		

	}
	
	
	
	public Class<T> getNombreClase() {
		return nombreClase;
	}


	public void add(T agregado) {
		
		entity.getTransaction().begin();
		entity.persist(agregado);
		entity.getTransaction().commit();
		log.info(EnumMensajes.AGREGADO_CORRECTO.getdescripcion());
	}


	public void delete(T borrado) {
		entity.getTransaction().begin();
		entity.remove(borrado);
		entity.getTransaction().commit();
		log.info(EnumMensajes.ELIMINADO_CORRECTO.getdescripcion());


	}
	
	public void deleteMerge(T borrado) {
		entity.getTransaction().begin();
		entity.remove(entity.merge(borrado));
		entity.getTransaction().commit();
		log.info(EnumMensajes.ELIMINADO_CORRECTO.getdescripcion());


	}

	public void update(T modificado) {
		entity.getTransaction().begin();
		entity.merge(modificado);
		entity.getTransaction().commit();
		log.info(EnumMensajes.EDITADO_CORRECTO.getdescripcion());

	}

	/**
	 * Devuelve un objeto específico
	 * El parámetro clase es para el nombre de la clase 
	 * El nombre representa nombre del al atributo que queremos buscar
	 * Por ejemplo para buscar en la clase Pais el nombre Mexico
	 *  @param clase
	 *  @param nombre 
	 */
	public Object findByNombre(String nombre) {		
		
		Query consulta = entity.createQuery("SELECT o FROM " + getNombreClase().getSimpleName() + " o WHERE nombre =:condicion");
		consulta.setParameter("condicion", nombre);
		return (Object) consulta.getSingleResult();
	}
	
	
	/**
	 * devuelve una lista
	 * el parámetro es el nombre de la clase que queremos devolver
	 * por ejemplo el nombre de la clase puede ser Usuario y devuelve la lista de todos los usuarios
	 * @param clase 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List getAll() {
		Query consulta = entity.createQuery("From " + getNombreClase().getSimpleName()+ " c");
		return consulta.getResultList();
	}

	/**
	 * Devuelve un objeto específico
	 * El parámetro clase es para el nombre de la clase 
	 * El nombre representa nombre del al atributo que queremos buscar
	 * Por ejemplo para buscar en la clase Pais el nombre Mexico
	 *  @param nombreClase
	 *  @param id
	 */

	public Object findByID(Long id) {
		Query consulta = entity.createQuery("SELECT o FROM " +getNombreClase().getSimpleName() + " o WHERE id =:condicion");
		consulta.setParameter("condicion", id);
		if( consulta.getResultList().size()>0) {
			return (Object) consulta.getResultList().get(0);
		}
		return null;
	}




}
