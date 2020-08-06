package ar.edu.unju.fi.dominio;
import static org.junit.Assert.*;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ar.edu.unju.fi.bean.AppBean;
import ar.edu.unju.fi.dto.InstrumentoDTO;
import ar.edu.unju.fi.exceptions.GeneralException;
import ar.edu.unju.fi.services.InstrumentoService;
import ar.edu.unju.fi.services.MarcaService;
import ar.edu.unju.fi.services.PaisService;
import ar.edu.unju.fi.services.implementation.InstrumentoServiceImp;
import ar.edu.unju.fi.services.implementation.MarcaServiceImp;
import ar.edu.unju.fi.services.implementation.PaisServiceImp;

public class InstrumentoTestCase {

	private static Logger log = Logger.getLogger(InstrumentoTestCase.class);
	
	private MarcaService marcaService;
	private PaisService paisService;
	private InstrumentoService instrumentoService;

	@Before
	public void setUp() {
		BasicConfigurator.configure();
		paisService =  AppBean.getBean(PaisServiceImp.class);
		instrumentoService =  AppBean.getBean(InstrumentoServiceImp.class);
		marcaService =  AppBean.getBean(MarcaServiceImp.class);

	}

	@After
	public void tearsDown() {
		paisService= null;
		marcaService = null;
		instrumentoService = null;
	}

	
//	@Test
	public void addInstrumentoService(){	
		try {
			Marca marcaEncontrada = (Marca) marcaService.findByNombre("Fender");
			Pais paisEncontrado = (Pais) paisService.findByNombre("Mexico");		
			InstrumentoDTO nuevoInstrumento = new InstrumentoDTO("23d2d2", "Fender Stratocaster2", 350, 2019, paisEncontrado, marcaEncontrada, "Guitarra", "ASD");
			instrumentoService.add(nuevoInstrumento);
			Instrumento encontrado2 = instrumentoService.buscarById(231232);
			assertNotNull(encontrado2);
		} catch (GeneralException e) {
			e.printStackTrace();
		}		
	
	}
	//	@Test
	public void updateInstrumentoService()  {		
		try {
			InstrumentoDTO buscarInstrumento;
			buscarInstrumento = instrumentoService.buscarByIdDto(9999);
			buscarInstrumento.setNumeroSerie("9998899");
			instrumentoService.update(buscarInstrumento);		
			Instrumento instrumentoEncontrado = instrumentoService.buscarById(buscarInstrumento.getId());		
			assertEquals(buscarInstrumento.getNumeroSerie(), instrumentoEncontrado.getNumeroSerie());
		} catch (GeneralException e) {
			e.printStackTrace();
		}
	
	}
	
	@Test
	public void deleteInstrumentoService(){
				
		
		try {
			Marca marcaEncontrada = (Marca) marcaService.findByNombre("Fender");
			Pais paisEncontrado = (Pais) paisService.findByNombre("Mexico");		
			InstrumentoDTO nuevoInstrumento = new InstrumentoDTO("23df2d2", "Fender Stratocaster2", 350, 2019, paisEncontrado, marcaEncontrada, "Guitarra", "ASD");
			instrumentoService.add(nuevoInstrumento);
			InstrumentoDTO buscarInstrumento = instrumentoService.buscarByIdDto(nuevoInstrumento.getId());		
			instrumentoService.delete(buscarInstrumento);		
			Instrumento instrumentoEncontrado2 = instrumentoService.buscarById(buscarInstrumento.getId());
			assertNull(instrumentoEncontrado2);
		} catch (GeneralException e) {
			e.printStackTrace();
		}	
	
	}
}
