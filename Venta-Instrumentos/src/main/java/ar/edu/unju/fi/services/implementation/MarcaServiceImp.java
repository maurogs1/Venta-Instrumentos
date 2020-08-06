package ar.edu.unju.fi.services.implementation;

import javax.inject.Inject;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.dao.MarcaDao;
import ar.edu.unju.fi.dominio.Marca;
import ar.edu.unju.fi.dto.MarcaDTO;
import ar.edu.unju.fi.services.MarcaService;
@Service
public class MarcaServiceImp implements MarcaService{
	private ModelMapper mapper = new ModelMapper();
	@Inject private MarcaDao marcaDao;
	
	
	
	public MarcaDTO add(MarcaDTO marcaDto) {
		Marca marca = new Marca();
		mapper.map(marcaDto, marca);
		marcaDao.add(marca);
			return marcaDto;
	}

	public MarcaDTO update(MarcaDTO marcaDto) {
		Marca marca = new Marca();
		mapper.map(marcaDto, marca);		
		if(marca != null) {
			marcaDao.update(marca);
			return marcaDto;
		}
		return null;
	}

	public MarcaDTO delete(MarcaDTO marcaDto) {
		Marca marca = (Marca) findByNombre(marcaDto.getNombre());
		mapper.map(marcaDto, marca);		
		if(marca != null) {
			marcaDao.delete(marca);
			return marcaDto;
		}
		return null;
	}

	public Marca findByNombre(String nombre) {		
		return (Marca) marcaDao.findByNombre(nombre);
	}
	
	public MarcaDTO findByNombreDto(String nombre) {		
		return (MarcaDTO) marcaDao.findByNombre(nombre);
	}
	
}
