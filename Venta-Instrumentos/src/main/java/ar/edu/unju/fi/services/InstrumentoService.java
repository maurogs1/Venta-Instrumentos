package ar.edu.unju.fi.services;

import ar.edu.unju.fi.dominio.Instrumento;
import ar.edu.unju.fi.dto.InstrumentoDTO;
import ar.edu.unju.fi.enumerado.EnumMensajes;
import ar.edu.unju.fi.exceptions.GeneralException;

public interface InstrumentoService   {
	
	/**
	 * Agrega un instrumento a la bd, validando que no exista el mismo número de serie
	 * @param instrumento
	 * @return
	 * @throws GeneralException 
	 */
	public InstrumentoDTO add(InstrumentoDTO instrumentoDto) throws GeneralException;
	
	/**
	 * Actualiza un instrumento
	 * @param instrumentoDto
	 * @return
	 * @throws GeneralException
	 */
	public InstrumentoDTO update(InstrumentoDTO instrumentoDto) throws GeneralException;
	
	/**
	 * Borra un instrumento
	 * @param instrumentoDto
	 * @return
	 * @throws GeneralException
	 */
	public InstrumentoDTO delete(InstrumentoDTO instrumentoDto) throws GeneralException;
	
	/**
	 * Busca un Instrumento por número de serie
	 * @param instrumento
	 * @return
	 */
	public Instrumento buscarNumeroSerie(Instrumento instrumento);
	
	/**
	 * Busca un instrumento por el id
	 * Devuelve un instrumento
	 * @param id
	 * @return
	 * @throws GeneralException
	 */
	public Instrumento buscarById(long id) throws GeneralException;
	
	/**
	 * Buscas un instrumento por el id
	 * Devuelve un instrumentoDTO
	 * @param id
	 * @return
	 * @throws GeneralException
	 */
	public InstrumentoDTO buscarByIdDto(long id) throws GeneralException;
}
