package ar.edu.unju.fi.dao.implementation;

import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.dao.UsuarioDao;
import ar.edu.unju.fi.dominio.Usuario;
import ar.edu.unju.fi.enumerado.EnumMensajes;
import ar.edu.unju.fi.exceptions.GeneralException;


@Named
public class UsuarioDaoImp extends GenericDaoImp<Usuario> implements UsuarioDao{

	





	public UsuarioDaoImp() {
		super(Usuario.class);
	}

	public Usuario getUsuario(String usuario) {
			Query query = entity.createQuery("SELECT u FROM Usuario u WHERE user = ?1");
			query.setParameter(1, usuario);
			if( query.getResultList().size()>0) {
				return (Usuario) query.getResultList().get(0);
			}
			return null;
	}
	
	public Boolean getUsuarioAndPass(String usuario, String password)throws GeneralException {
			Query query = entity.createQuery("SELECT u FROM Usuario u WHERE user = ?1 and password = ?2");
			query.setParameter(1, usuario);
			query.setParameter(2, password);
			if( query.getResultList().size()>0) {
				return true;
			}
			return false;
			
	}
	
	public Boolean getByEmail(String email) {
		Query query = entity.createQuery("SELECT u FROM Usuario u WHERE e_mail = ?1");
		query.setParameter(1, email);
		if( query.getResultList().size()>0) {
			return true;
		}
		return false;
}


}
