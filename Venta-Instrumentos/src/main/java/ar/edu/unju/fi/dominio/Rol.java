package ar.edu.unju.fi.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Creación de la clase Rol
 * Representa los roles que tendrán los usuarios
 * @author mauro
 *
 */

@Entity
@Table(name="roles")
public class Rol {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Integer id;
	
	@Column
	private String descripcion;
	
	/**
	 * Constructor por defecto
	 */
	public Rol() {
		
	}
	
	public Rol(String descripcion) {
		super();
		this.descripcion = descripcion;
	}
	
	public Rol(Integer id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Rol [descripcion=" + descripcion + "]";
	}


	
}
