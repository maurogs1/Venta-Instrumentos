package ar.edu.unju.fi.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Creación de la clase Usuario
 * Representa a los usuarios que se conectarán en el sistema
 * 
 * @author mauro
 *
 */

@Entity
@Table(name="usuarios")
public class Usuario {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Long id;
	
	// El campo user de la base de datos es un nombre resevado y hay que agregar
	// \" \"  dentro del name en la columna para que lo reconozca sin el nombre resevado en la bd
	@Column(name="\"user\"")
	private String user;
	
	@Column
	private String password;
	
	@Column(name="nombre_apellido")
	private String nombreApellido;
	
	@Column(name="e_mail")
	private String eMail;
	
	@Column
	private String estado;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="rol_id", nullable=false)
	private Rol rol;
	
	
	//
	/**
	 * Constructor por defecto
	 */
	public Usuario() {
		
	}
	
	/**
	 * Constructor con parámetros
	 * @param user
	 * @param password
	 * @param nombreApellido
	 * @param eMail
	 * @param estado
	 * @param rol
	 */
	public Usuario(String user,String password, String nombreApellido, String eMail, String estado, Rol rol) {
		super();
		this.user = user;
		this.password = password;
		this.nombreApellido = nombreApellido;
		this.eMail = eMail;
		this.estado = estado;
		this.rol = rol;
	}

	/**
	 * Constructor con parámetros
	 * @param id
	 * @param user
	 * @param password
	 * @param nombreApellido
	 * @param eMail
	 * @param estado
	 * @param rol
	 */
	public Usuario(Long id, String user, String password, String nombreApellido, String eMail, String estado, Rol rol) {
		this.id = id;
		this.user = user;
		this.password = password;
		this.nombreApellido = nombreApellido;
		this.eMail = eMail;
		this.estado = estado;
		this.rol =rol;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getNombreApellido() {
		return nombreApellido;
	}

	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
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

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", user=" + user + ", password=" + password + ", nombreApellido=" + nombreApellido
				+ ", eMail=" + eMail + ", estado=" + estado + ", rol=" + rol + "]";
	}
	
	
	
}
