package ar.edu.unju.fi.dto;

import java.io.Serializable;
import java.util.Date;

public class CuotaDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	private String estado;
	private Double valor;
	private Long numeroCuota;
	private Long idVenta;
	private Date fechaPago;
	private Date fechaVencimiento;
	
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public Date getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(Date date) {
		this.fechaPago = date;
	}
	public Long getIdVenta() {
		return idVenta;
	}
	public void setIdVenta(Long idVenta) {
		this.idVenta = idVenta;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Long getNumeroCuota() {
		return numeroCuota;
	}
	public void setNumeroCuota(Long numeroCuota) {
		this.numeroCuota = numeroCuota;
	}

	@Override
	public String toString() {
		return "CuotaDTO [id=" + id + ", estado=" + estado + ", valor=" + valor + ", numeroCuota=" + numeroCuota + "]";
	}

	public CuotaDTO(){

	}

	public CuotaDTO(String estado, Double valor){
		this.estado = estado;
		this.valor = valor;
	}
	public CuotaDTO(Long idVenta){
		this.idVenta = idVenta;
	}

}
