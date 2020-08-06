package ar.edu.unju.fi.dao.implementation;

import javax.inject.Named;

import ar.edu.unju.fi.dao.MarcaDao;
import ar.edu.unju.fi.dominio.Marca;
@Named
public class MarcaDaoImp extends GenericDaoImp<Marca> implements MarcaDao{

	
	public MarcaDaoImp() {
		super(Marca.class);
	}

}
