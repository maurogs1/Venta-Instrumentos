package ar.edu.unju.fi.dao;

import java.util.List;



/**
 * 
 * @author mauro
 *
 * @param <T>
 */

public interface GenericDao<T> {
	
	/**
	 * Agrega un item
	 * @param agregado
	 */
	public void add(T agregado);
	
	/**
	 * Elimina un item
	 * @param borrado es el item que queremos borrar
	 */
	public void delete(T borrado);
	
	/**
	 * Elimina un item
	 * @param borrado es el item que queremos borrar
	 */
	public void deleteMerge(T borrado);
	
	/**
	 * Modifica un item
	 * @param modificado
	 */
	public void update(T modificado);
	
	/**
	 * Devuelve un objeto específico 
	 * El nombre representa nombre del al atributo que queremos buscar
	 * Por ejemplo para buscar en la clase Pais el nombre Mexico
	 *  @param clase
	 *  @param nombre 
	 */
	Object findByNombre(String nombre);

	/**
	 * Devuelve un objeto por el número de ID
	 * @param id
	 * @return
	 */
	Object findByID( Long id);
	
	/**
	 * devuelve una lista de objetos
	 * @param clase 
	 * @return
	 */
	public List getAll();

}
