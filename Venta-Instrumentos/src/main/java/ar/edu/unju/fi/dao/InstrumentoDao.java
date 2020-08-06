package ar.edu.unju.fi.dao;


import ar.edu.unju.fi.dominio.Instrumento;

public interface InstrumentoDao extends GenericDao <Instrumento>{

	/**
	 * Busca un instrumento por el n�mero de serie
	 * @param numeroSerie
	 * @return
	 */
	public boolean findNumeroSerie(String numeroSerie);
}
