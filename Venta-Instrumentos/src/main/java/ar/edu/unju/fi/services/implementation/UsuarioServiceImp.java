package ar.edu.unju.fi.services.implementation;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ar.edu.unju.fi.dao.UsuarioDao;
import ar.edu.unju.fi.dominio.Usuario;
import ar.edu.unju.fi.dominio.Venta;
import ar.edu.unju.fi.dto.UsuarioDTO;
import ar.edu.unju.fi.dto.VentaDTO;
import ar.edu.unju.fi.enumerado.EnumMensajes;
import ar.edu.unju.fi.exceptions.GeneralException;
import ar.edu.unju.fi.services.UsuarioService;


@Service
public class UsuarioServiceImp implements UsuarioService{

	private static Logger log = Logger.getLogger(UsuarioServiceImp.class);
	private ModelMapper mapper = new ModelMapper();
	@Inject
	private UsuarioDao usuarioDao;
	
	public UsuarioDTO registrar(UsuarioDTO p) throws GeneralException{		
		if(usuarioDao.getUsuarioAndPass(p.getUser(), p.getPassword())) {
			throw new GeneralException(EnumMensajes.USUARIO_EXISTENTE.getdescripcion());
		}
		if(usuarioDao.getByEmail(p.geteMail())) {
			throw new GeneralException(EnumMensajes.USUARIO_CORREO_EXISTENTE.getdescripcion());
		}
		Usuario usuario = new Usuario();
		mapper.map(p, usuario);
		usuarioDao.add(usuario);
		return p;
	}

	public UsuarioDTO update(UsuarioDTO p) throws GeneralException {
		Usuario user = usuarioDao.getUsuario(p.getUser());
		if(user != null) {
			mapper.map(p, user);
			usuarioDao.update(user);	
			return p;
		} 
		throw new GeneralException(EnumMensajes.USUARIO_INEXISTENTE.getdescripcion());				
	}

	public UsuarioDTO eliminar(UsuarioDTO p) throws GeneralException {	
		Usuario usuario = buscarById(p.getId());
		mapper.map(p, usuario);		
		if(usuario != null) {
			usuarioDao.delete(usuario);
			return p;
		}
		throw new GeneralException(EnumMensajes.USUARIO_INEXISTENTE.getdescripcion());	
	}

	
	public UsuarioDTO obtenerUsuarioDto(UsuarioDTO p) throws GeneralException {
		Usuario user = usuarioDao.getUsuario(p.getUser());
		if(user != null) {
			mapper.map(user, p);
			return p ;	
		}
		throw new GeneralException(EnumMensajes.USUARIO_INEXISTENTE.getdescripcion());	
	}

	
	public void cambiarEstado(UsuarioDTO p) {
			Usuario user = usuarioDao.getUsuario(p.getUser());
			user.setEstado(EnumMensajes.USUARIO_ESTADO_INACTIVO.getdescripcion());
			usuarioDao.update(user);
			log.info(EnumMensajes.USUARIO_CAMBIO_DE_ESTADO_POR_CONTRASEÑA.getdescripcion());			
		
	}

	public List<Usuario> obtenerTodos() {
		List<Usuario> lista = usuarioDao.getAll();
		return lista;
	}

	public Usuario buscarById(long id) {
		Usuario encontrado = (Usuario) usuarioDao.findByID(id);
		if(encontrado != null) {			
			return  encontrado;
		}
		log.info(EnumMensajes.NUMERO_ID_INEXISTENTE.getdescripcion());
		return 	null;
	}
	
	public UsuarioDTO buscarByIdDto(long id) throws GeneralException{
		UsuarioDTO usuarioDto = new UsuarioDTO();
		Usuario usuario = buscarById(id);
		if(usuario != null) {
			mapper.map(usuario, usuarioDto);
			return usuarioDto;
		}
		throw new GeneralException(EnumMensajes.USUARIO_INEXISTENTE.getdescripcion());
		
	}

	public boolean login(UsuarioDTO user) throws GeneralException {
		boolean resultado = true;	
		if(!usuarioDao.getUsuarioAndPass(user.getUser(), user.getPassword()) ) {
			user.setAttemps(user.getAttemps()+ 1);
			log.info("Intento n°"+user.getAttemps()+" fallido.");
			resultado =  false;
		}
		if(user.getAttemps() >= 3) {
			cambiarEstado(user);
			return false;
		}	
		return resultado;
	}


	public Usuario buscarUsuario(UsuarioDTO user) throws GeneralException {
		Usuario encontrado = usuarioDao.getUsuario(user.getUser());
		if(encontrado != null) {
			return encontrado;
		}
		throw new GeneralException(EnumMensajes.USUARIO_INEXISTENTE.getdescripcion());
	}







}
