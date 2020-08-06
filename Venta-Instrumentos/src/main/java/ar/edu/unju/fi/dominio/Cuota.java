package ar.edu.unju.fi.dominio;


import java.util.Date;

import javax.inject.Named;
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
 * Creación de la clase Cuota
 * Esta clase representa las cuotas que tendrá una venta
 * @author mauro
 *
 */

@Entity
@Table( name="cantidad_cuotas" )
@Named
public class Cuota {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="numero_cuota")
	private Long numeroCuota;
	@Column(name="valor")
	private Double valor;
	@Column(name="estado")
	private String estado;
	@Column(name="fecha_pago")
	private Date fechaPago;
	@Column(name="fecha_vencimiento")
	private Date fechaVencimiento;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_venta")
	private Venta venta;
	
	
	public Cuota() {
		this.estado =  "ADEUDA";
	}

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getNumeroCuota() {
		return numeroCuota;
	}
	public void setNumeroCuota(Long numeroCuota) {
		this.numeroCuota = numeroCuota;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Date getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public Venta getVenta() {
		return venta;
	}
	public void setVenta(Venta venta) {
		this.venta = venta;
	}
	@Override
	public String toString() {
		return "Cuota [id=" + id + ", numeroCuota=" + numeroCuota + ", valor=" + valor + ", estado=" + estado
				+ ", fechaPago=" + fechaPago + ", fechaVencimiento=" + fechaVencimiento + ", venta=" + venta + "]";
	}
	
	

}

