package ar.edu.unju.fi.dto;

import java.io.Serializable;

import ar.edu.unju.fi.dominio.Rol;
import ar.edu.unju.fi.enumerado.EnumMensajes;

public class UsuarioDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nombreApellido;
	private Integer attemps;
	private String user;
	private String password;
	private String eMail;
	private String estado;
	private Rol rol;
	
	
	

	public UsuarioDTO() {
		
	}
	
	public UsuarioDTO(Long id, String nombreApellido, String user, String password) {
		this.id = id;
		this.nombreApellido = nombreApellido;
		this.user = user;
		this.password = password;
		this.attemps = 0;
	}
	
	public UsuarioDTO( String nombreApellido, String user, String password) {
		this.nombreApellido = nombreApellido;
		this.user = user;
		this.password = password;
		this.attemps = 0;

	}
	
	
	
	
	
	
	
	public UsuarioDTO(String nombreApellido, String user, String password, String eMail, Rol rol) {
		super();
		this.nombreApellido = nombreApellido;
		this.user = user;
		this.password = password;
		this.eMail = eMail;
		this.estado = EnumMensajes.USUARIO_ESTADO_ACTIVO.getdescripcion();
		this.rol = rol;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Integer getAttemps() {
		return attemps;
	}

	public void setAttemps(Integer attemps) {
		this.attemps = attemps;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreApellido() {
		return nombreApellido;
	}

	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UsuarioDTO [id=" + id + ", nombreApellido=" + nombreApellido + ", user=" + user + ", password="
				+ password + "]";
	}

	
	
	
	
}
