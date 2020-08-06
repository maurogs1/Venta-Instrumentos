package ar.edu.unju.fi.dao.implementation;

import java.util.List;

import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ar.edu.unju.fi.dao.VentaDao;
import ar.edu.unju.fi.dominio.Cuota;
import ar.edu.unju.fi.dominio.Venta;
import ar.edu.unju.fi.enumerado.EnumMensajes;
import ar.edu.unju.fi.exceptions.GeneralException;

@Named
public class VentaDaoImp extends GenericDaoImp<Venta> implements VentaDao  {

	
	public VentaDaoImp() {
		super(Venta.class);
		}

	public List<Cuota> getTodasAdeudadas() throws GeneralException{	 
			 Query query = entity.createQuery("SELECT u FROM Cuota u WHERE estado=?1");
			 query.setParameter(1, EnumMensajes.CUOTA_ESTADO_ADEUDA.getdescripcion());
			 if(query.getResultList() != null) {
				 return (List<Cuota>) query.getResultList();	
			 }
			throw new GeneralException(EnumMensajes.CUOTA_NO_EXISTENTE.getdescripcion());
				
}
}
