package ar.edu.unju.fi.dao.implementation;

import javax.inject.Named;

import ar.edu.unju.fi.dao.PaisDao;
import ar.edu.unju.fi.dominio.Pais;
@Named
public class PaisDaoImp extends GenericDaoImp<Pais> implements PaisDao {

	public PaisDaoImp() {
		super(Pais.class);
	}



}
