package ar.edu.unju.fi.services;

import ar.edu.unju.fi.dominio.Pais;
import ar.edu.unju.fi.dto.PaisDTO;

public interface PaisService {
	
	public PaisDTO add(PaisDTO paisDto);
	public PaisDTO update(PaisDTO paisDto);
	public PaisDTO delete (PaisDTO paisDto);
	public Pais findByNombre(String nombre);
	public PaisDTO findByNombreDto(String nombre);		

}
