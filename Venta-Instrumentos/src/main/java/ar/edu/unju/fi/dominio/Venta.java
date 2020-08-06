package ar.edu.unju.fi.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import ar.edu.unju.fi.util.ValorDolar;
import ar.edu.unju.fi.util.ValorDolarStub;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
/**
 * Creación de la clase Venta
 * Esta clase representa a la Venta de un instrumento que realizará un usuario, también tendrá 
 * un listado de cuotas
 *  
 *  
 * @author mauro
 *
 */

@Entity
@Table(name="ventas")
@Inheritance( strategy = InheritanceType.SINGLE_TABLE )
@DiscriminatorColumn( name="tipo" )

public abstract class Venta  implements Serializable {
	@Transient
 private ValorDolar valorDolar = new ValorDolarStub();
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="importe")
	protected Double importe;
	
	@Column(name="importe_total")
	protected Double importeTotal;
	
	@Column(name="fecha")
	private Date fecha;
	
	@OneToOne
	@JoinColumn(name="instrumento")
	private Instrumento instrumento;
	
	@OneToOne
	@JoinColumn(name="usuario_id")
	private Usuario usuario;
	

	
	@Column(name="estado")
	private String estado;

	@OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
	protected List<Cuota> cuotas = new ArrayList<Cuota>();
	
	
	/**
	 * Constructor por defecto
	 */
	public Venta() {}

	/**
	 * Constructor con su istrumento a vender, el usuario que realiza la venta y la cantidad de cuotas en la que se venderá el producto
	 * @param inst
	 * @param cantidadCuotas
	 */
	public Venta( Instrumento inst, Usuario user) {
		this.importeTotal = 0.0;
		this.importe =  inst.getPrecioUsd() ;
		this.fecha = new Date();
		this.instrumento = inst;
		this.usuario = user;
		
	}
	
	/**
	 * método para saber qué tipo de clase es
	 * @author mauro
	 * @return
	 */
	public abstract boolean isContado();

	/**
	 * método para saber qué tipo de clase es
	 * @author mauro
	 * @return
	 */
	public abstract boolean isFinanciado();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Instrumento getInstrumento() {
		return instrumento;
	}

	public void setInstrumento(Instrumento instrumento) {
		this.instrumento = instrumento;
	}
		
	public Double getImporteTotal() {
		return importeTotal;
	}
	public void setImporteTotal(Double importeDescuento) {
		this.importeTotal = importeDescuento;
	}
	

	public ValorDolar getValorDolar() {
		return valorDolar;
	}

	public void setValorDolar(ValorDolar valorDolar) {
		this.valorDolar = valorDolar;
	}

	public List<Cuota> getCuotas() {
		return cuotas;
	}

	public void setCuotas(List<Cuota> cuotas) {
		this.cuotas = cuotas;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	

	
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	

	@Override
	public String toString() {
		return "Venta [valorDolar=" + valorDolar + ", id=" + id + ", importe=" + importe + ", importeTotal="
				+ importeTotal + ", fecha=" + fecha + ", instrumento=" + instrumento + "]";
	}

	
	
	
	

}

