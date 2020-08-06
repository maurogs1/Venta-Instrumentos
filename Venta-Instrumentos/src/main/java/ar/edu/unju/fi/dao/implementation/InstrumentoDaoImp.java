package ar.edu.unju.fi.dao.implementation;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ar.edu.unju.fi.dao.InstrumentoDao;
import ar.edu.unju.fi.dominio.Instrumento;
import ar.edu.unju.fi.util.ConnectionUtil;

@Named
public class InstrumentoDaoImp  extends GenericDaoImp<Instrumento> implements InstrumentoDao{


	
	public InstrumentoDaoImp() {
		super(Instrumento.class);
	}

	protected EntityManager entity = ConnectionUtil.getEntityManagerFactory().createEntityManager();

	public boolean findNumeroSerie(String numeroSerie) {
		Query consulta = entity.createQuery("From Instrumento i where numero_serie =:condicion");
		consulta.setParameter("condicion", numeroSerie);
		if(consulta.getResultList().size() > 0 ) {
			return false;
		}
		return true ;
	}

}
