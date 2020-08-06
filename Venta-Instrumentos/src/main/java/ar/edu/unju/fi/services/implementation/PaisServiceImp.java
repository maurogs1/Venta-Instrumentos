package ar.edu.unju.fi.services.implementation;

import javax.inject.Inject;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.dao.PaisDao;
import ar.edu.unju.fi.dominio.Pais;
import ar.edu.unju.fi.dto.PaisDTO;
import ar.edu.unju.fi.services.PaisService;
@Service
public class PaisServiceImp implements PaisService {
	
	private ModelMapper mapper = new ModelMapper();
	@Inject private PaisDao paisDao;
	
	
	public PaisDTO add(PaisDTO paisDto) {
		Pais pais = new Pais();
		mapper.map(paisDto, pais);
		paisDao.add(pais);
			return paisDto;
	}

	public PaisDTO update(PaisDTO paisDto) {
		Pais pais = new Pais();
		mapper.map(paisDto, pais);		
		if(pais != null) {
			paisDao.update(pais);
			return paisDto;
		}
		return null;
	}

	public PaisDTO delete(PaisDTO paisDto) {
		Pais pais = findByNombre(paisDto.getNombre());
		mapper.map(paisDto, pais);		
		if(pais != null) {
			paisDao.delete(pais);
			return paisDto;
		}
		return null;
	}

	public Pais findByNombre(String nombre) {		
		return (Pais) paisDao.findByNombre(nombre);
	}
	public PaisDTO findByNombreDto(String nombre) {		
		return (PaisDTO) paisDao.findByNombre(nombre);
	}

}
