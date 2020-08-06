package ar.edu.unju.fi.services.implementation;



import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.dao.InstrumentoDao;
import ar.edu.unju.fi.dominio.Instrumento;
import ar.edu.unju.fi.dominio.Venta;
import ar.edu.unju.fi.dto.InstrumentoDTO;
import ar.edu.unju.fi.dto.VentaDTO;
import ar.edu.unju.fi.enumerado.EnumMensajes;
import ar.edu.unju.fi.exceptions.GeneralException;
import ar.edu.unju.fi.services.InstrumentoService;

@Service
public class InstrumentoServiceImp implements InstrumentoService{
	private static Logger log = Logger.getLogger(InstrumentoServiceImp.class);
	private ModelMapper mapper = new ModelMapper();

	@Inject
	private InstrumentoDao instrumentoDao;
	
	
	public InstrumentoDTO add(InstrumentoDTO instrumentoDto) throws GeneralException {
		
		if(instrumentoDao.findNumeroSerie(instrumentoDto.getNumeroSerie())) {
			Instrumento nuevoInstrumento = new Instrumento();
			mapper.map(instrumentoDto, nuevoInstrumento);
			instrumentoDao.add(nuevoInstrumento);
			instrumentoDto.setId(nuevoInstrumento.getId());
			return	instrumentoDto;
		}		
		throw new GeneralException(EnumMensajes.NUMERO_SERIE_DUPLICADO.getdescripcion());			
	}


	public InstrumentoDTO update(InstrumentoDTO instrumentoDto) throws GeneralException {
			Instrumento instrumentoEncontrado =buscarById(instrumentoDto.getId());
			if(instrumentoEncontrado != null) {				
			mapper.map(instrumentoDto,instrumentoEncontrado);
			instrumentoDao.update(instrumentoEncontrado);
			return instrumentoDto;
		}
		throw new GeneralException(EnumMensajes.CAMPOS_VACIOS.getdescripcion());
	}


	public InstrumentoDTO delete(InstrumentoDTO instrumentoDto) throws GeneralException {
		Instrumento instrumentoEncontrado = buscarById(instrumentoDto.getId());
		if(instrumentoEncontrado.getId() != null) {
			mapper.map(instrumentoDto, instrumentoEncontrado);
			instrumentoDao.delete(instrumentoEncontrado);
			return instrumentoDto;
		}
		throw new GeneralException(EnumMensajes.NUMERO_ID_INEXISTENTE.getdescripcion());		

	}


	public Instrumento buscarNumeroSerie(Instrumento instrumento) {
		instrumentoDao.findNumeroSerie(instrumento.getNumeroSerie());
		return instrumento;
	}

	

	
	public Instrumento buscarById(long id) throws GeneralException {
		Instrumento encontrado = (Instrumento) instrumentoDao.findByID(id);
		if(encontrado != null) {			
			return  encontrado;
		}
		return null;
	}
	
	public InstrumentoDTO buscarByIdDto(long id) throws GeneralException {
		InstrumentoDTO instrumentoDto = new InstrumentoDTO();
		Instrumento encontrado = buscarById(id);
		if(encontrado != null) {
			mapper.map(encontrado, instrumentoDto);	
			return instrumentoDto;
		}		
		System.out.println("error");
		throw new GeneralException(EnumMensajes.NUMERO_ID_INEXISTENTE.getdescripcion());		
	}


}
