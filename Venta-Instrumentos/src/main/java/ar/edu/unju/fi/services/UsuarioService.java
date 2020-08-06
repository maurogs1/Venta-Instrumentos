package ar.edu.unju.fi.services;
import java.util.List;
import ar.edu.unju.fi.dominio.Usuario;
import ar.edu.unju.fi.dto.UsuarioDTO;
import ar.edu.unju.fi.exceptions.GeneralException;

public interface UsuarioService {
	
/**
 * Agrega un usuario a la bd	
 * @param p
 * @return
 * @throws GeneralException
 */
public UsuarioDTO registrar(UsuarioDTO p) throws GeneralException;

/**
 * Actualiza un usuario
 * @param p
 * @return
 * @throws GeneralException
 */
public UsuarioDTO update(UsuarioDTO p) throws GeneralException;
	
/**
 * Elimina un usuario
 * @param p
 * @return
 * @throws GeneralException
 */
public UsuarioDTO eliminar(UsuarioDTO p) throws GeneralException;
	
/**
 *  Busca un usuario por el numero de ID
 *si lo encuentra mapea el usuario de la bd al usuarioDTO
 * @param p
 * @return
 * @throws GeneralException
 */
public UsuarioDTO obtenerUsuarioDto(UsuarioDTO p) throws GeneralException;	
	
/**
 * Obtiene todos los usuarios de la bd
 * @return
 */
public List<Usuario> obtenerTodos();
	
/**
 * Busca un usuario de la base de datos por el numero id
 * @param id
 * @return
 */
public Usuario buscarById(long id);

/**
 * Logea a un usuario
 * En el caso de que el usuario haya errado 3 veces la contraseña, se le cambia el estado a "INACTIVO" 
 * @param user
 * @return
 * @throws GeneralException
 */
public boolean login(UsuarioDTO user) throws GeneralException;
	
/**
 * Busca a un usuario de la bd por el nombre de usuario	
 * @param user
 * @return
 * @throws GeneralException
 */
public Usuario buscarUsuario(UsuarioDTO user) throws GeneralException;

/**
 * Busca un usuario por el numero de ID
 * @param id
 * @return
 * @throws GeneralException
 */
public UsuarioDTO buscarByIdDto(long id) throws GeneralException;

	
}
