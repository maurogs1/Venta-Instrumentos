package ar.edu.unju.fi.dao;

import java.util.List;


import ar.edu.unju.fi.dominio.Usuario;
import ar.edu.unju.fi.exceptions.GeneralException;

/**
 * 
 * @author mauro
 *
 */

public interface UsuarioDao extends GenericDao<Usuario> {
	
	/**
	 * Obtiene un usuario por el nombre de usuario
	 * @param usuario
	 * @return
	 */
	public Usuario getUsuario(String usuario);
	
	/**
	 * Busca un usuario por el nombre de email
	 * Devuleve true si lo encuentra
	 * @param email
	 * @return
	 */
	public Boolean getByEmail(String email);

	/**
	 * Busca un usuario por el nombre de usuario y contraseña
	 * Devuelve true si lo encuentra
	 * @param user
	 * @param password
	 * @return
	 * @throws GeneralException
	 */
	public Boolean getUsuarioAndPass(String user, String password) throws GeneralException;
	
}
