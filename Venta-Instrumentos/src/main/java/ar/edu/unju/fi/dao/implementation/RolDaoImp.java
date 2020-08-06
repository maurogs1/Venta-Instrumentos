package ar.edu.unju.fi.dao.implementation;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ar.edu.unju.fi.dao.RolDao;
import ar.edu.unju.fi.dominio.Rol;
import ar.edu.unju.fi.util.ConnectionUtil;

/**
 * 
 * @author mauro
 *
 */
@Named
public class RolDaoImp extends GenericDaoImp<Rol> implements RolDao{
	
	
	public RolDaoImp() {
		super(Rol.class);
	}
	protected EntityManager entity = ConnectionUtil.getEntityManagerFactory().createEntityManager();
	public Rol getRol(String detalle) {
		try { 
			Query query = entity.createQuery("SELECT u FROM Rol u WHERE descripcion = ?1");
			query.setParameter(1, detalle);
			return (Rol) query.getSingleResult();
		} catch(Exception e) {
			return null;
		}
	}
	
}
