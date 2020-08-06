package ar.edu.unju.fi.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import ar.edu.unju.fi.dominio.Cuota;
import ar.edu.unju.fi.dominio.Venta;
import ar.edu.unju.fi.enumerado.EnumMensajes;
import ar.edu.unju.fi.exceptions.GeneralException;

public interface VentaDao extends GenericDao<Venta>{
	/**
	 * Obtiene todas las cuotas adeudadas
	 * @return
	 * @throws GeneralException
	 */
	public List<Cuota> getTodasAdeudadas() throws GeneralException;
}
