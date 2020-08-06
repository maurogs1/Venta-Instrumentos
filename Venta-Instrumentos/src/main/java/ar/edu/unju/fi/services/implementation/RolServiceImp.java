package ar.edu.unju.fi.services.implementation;

import javax.inject.Inject;


import org.springframework.stereotype.Service;

import ar.edu.unju.fi.dao.RolDao;
import ar.edu.unju.fi.dominio.Rol;
import ar.edu.unju.fi.services.RolService;
//;0
@Service
public class RolServiceImp implements RolService {
	

	@Inject
	private RolDao rolDao;
	
	public Rol get(String rol) {
		Rol r = rolDao.getRol(rol);
		return r;
	}
	
	

}
