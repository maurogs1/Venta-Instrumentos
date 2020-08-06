package ar.edu.unju.fi.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * Creación de la clase Pais
 * Representa los países que tendrá cada instrumento
 * @author mauro
 *
 */
@Entity
@Table(name="paises")
public class Pais {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Integer id;
	
	@Column
	private String nombre;

	/**
	 * Constructor por defecto
	 */
	public Pais() {
	}
	
	
	
	public Pais(String nombre) {
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
		return "Pais [nombre=" + nombre + "]";
	}
	
	
	
	
}
