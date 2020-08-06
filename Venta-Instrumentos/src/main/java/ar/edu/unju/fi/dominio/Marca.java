package ar.edu.unju.fi.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * Creaci�n de la clase Marca
 * Representa a las marcas que tendr�n los instrumentos
 * @author mauro
 *
 */
@Entity
@Table(name="marcas")
public class Marca {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Integer id;
	
	@Column	
	private String nombre;
	
	
	/**
	 * Constructor por defecto
	 */
	public Marca() {
	}
	

	public Marca(String nombre) {
		this.nombre = nombre;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	@Override
	public String toString() {
		return "Marca [id=" + id + ", nombre=" + nombre + "]";
	}
	
	

}
