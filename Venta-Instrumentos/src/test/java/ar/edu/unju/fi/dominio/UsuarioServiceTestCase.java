package ar.edu.unju.fi.dominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.After;
import org.junit.Before;
import ar.edu.unju.fi.bean.AppBean;
import ar.edu.unju.fi.dto.UsuarioDTO;
import ar.edu.unju.fi.enumerado.EnumMensajes;
import ar.edu.unju.fi.exceptions.GeneralException;
import ar.edu.unju.fi.services.UsuarioService;
import ar.edu.unju.fi.services.implementation.UsuarioServiceImp;
import ar.edu.unju.fi.services.RolService;
import ar.edu.unju.fi.services.implementation.RolServiceImp;

public class UsuarioServiceTestCase {

	private UsuarioService usuarioService;
	private RolService rolService;

	@Before
	public void setUp() throws Exception {
		usuarioService = AppBean.getBean(UsuarioServiceImp.class);
		rolService = AppBean.getBean(RolServiceImp.class);
	}

	@After
	public void tearDown() throws Exception {
		usuarioService = null;
	}

	//@Test
	public void testRegistrar()   {		
		try {
		Rol r = rolService.get("Vendedor");
		UsuarioDTO p = new UsuarioDTO("NombreApellido","de22de","contraseña","333d@hotmail.com",r);
		usuarioService.registrar(p);
		UsuarioDTO check =	usuarioService.obtenerUsuarioDto(p);		
		assertNotNull(check);
		} catch (GeneralException e) {
			e.printStackTrace();
		}
	
	}

//	@Test
	public void testEliminar()  {
		UsuarioDTO encontrado;
		try {
			encontrado = usuarioService.buscarByIdDto(15);
			usuarioService.eliminar(encontrado);	 
			Usuario buscarEliminado = usuarioService.buscarById(51);	 
			assertNull(buscarEliminado);
		} catch (GeneralException e) {
			e.printStackTrace();
		}
		
	}

	//	@Test
	public void testModificar() {
		try {
			UsuarioDTO encontrado = usuarioService.buscarByIdDto(16);
			encontrado.setNombreApellido("NUEVO APELLIDO");
			usuarioService.update(encontrado);
			Usuario actualizado = usuarioService.buscarById(15);
			assertEquals(encontrado.getNombreApellido(), actualizado.getNombreApellido());
		} catch (GeneralException e) {
			e.printStackTrace();
		}
	

	}


//	@Test
	public void loginTest() {

		try {
			UsuarioDTO user1 = new UsuarioDTO("apellido", "dede", "serge12534");
			UsuarioDTO user2 = new UsuarioDTO("apellido", "de2de", "serge12534");
			usuarioService.login(user1);	//intento 1 de user1 1
			usuarioService.login(user1);	//intento 2 de user1 1				
			usuarioService.login(user2);	//intento 1 de user1 2	
			usuarioService.login(user1);	//intento 3 de user1 1	
			Usuario buscarInactivo = usuarioService.buscarUsuario(user1);
			assertTrue(buscarInactivo.getEstado() == EnumMensajes.USUARIO_ESTADO_INACTIVO.getdescripcion());
		} catch (GeneralException e) {			
			e.printStackTrace();
		}		
	
	}

}
