package ar.edu.unju.fi.dao;

import ar.edu.unju.fi.dominio.Rol;

/**
 * 
 * @author mauro 
 *
 */

public interface RolDao extends GenericDao<Rol> {

	/**
	 * Busca un rol por la descripción del rol
	 * Por ejemplo un rol de tipo "admin"
	 * @param descripcion
	 * @return
	 */
	public Rol getRol(String descripcion);
	
}
