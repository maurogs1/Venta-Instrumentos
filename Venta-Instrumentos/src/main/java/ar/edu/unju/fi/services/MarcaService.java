package ar.edu.unju.fi.services;

import ar.edu.unju.fi.dominio.Marca;
import ar.edu.unju.fi.dto.MarcaDTO;
import ar.edu.unju.fi.dto.PaisDTO;

public interface MarcaService {
	/**
	 * agrega una marca 
	 * @param marcaDto
	 * @return
	 */
	public MarcaDTO add(MarcaDTO marcaDto);
	/**
	 * Actualiza una marca
	 * @param marcaDto
	 * @return
	 */
	public MarcaDTO update(MarcaDTO marcaDto);
	/**
	 * Borra una marca
	 * @param marcaDto
	 * @return
	 */
	public MarcaDTO delete (MarcaDTO marcaDto);
	/**
	 * Busca por el nombre de la marca
	 * Devuelve una MarcaDTO
	 * @param nombre
	 * @return
	 */
	public Marca findByNombre(String nombre);
	/**
	 * Busca por el nombre de la marca
	 * Devuelve una Marca
	 * @param nombre
	 * @return
	 */
	public MarcaDTO findByNombreDto(String nombre);
	

}
