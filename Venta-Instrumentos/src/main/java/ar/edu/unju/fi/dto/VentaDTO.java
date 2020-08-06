package ar.edu.unju.fi.dto;

import java.util.Date;
import java.util.List;

import ar.edu.unju.fi.dominio.Cuota;
import ar.edu.unju.fi.dominio.Instrumento;
import ar.edu.unju.fi.dominio.Usuario;
import ar.edu.unju.fi.dominio.Venta;

public class VentaDTO {

	private Long id;
	private Instrumento instrumento;
	private Usuario usuario;
	private Double importe;
	private Date fecha;
	private Integer cantidadCuota;
	private String estado;
	private Boolean contado;
//	private List<Cuota> cuotas;
	
	public VentaDTO() {
	}

	public VentaDTO(Instrumento instrumento, Usuario usuario, Integer cantidadCuota) {
		this.cantidadCuota = cantidadCuota;
		this.instrumento = instrumento;
		this.usuario = usuario;
		this.importe =  instrumento.getPrecioUsd() ;
		this.fecha = new Date();
	}
	
	public VentaDTO(Instrumento instrumento, Usuario usuario) {
		this.instrumento = instrumento;
		this.usuario = usuario;
		this.importe =  instrumento.getPrecioUsd() ;
		this.fecha = new Date();
	}
	
	public VentaDTO(Venta venta) {
		this.id = venta.getId();
		this.cantidadCuota = venta.getCuotas().size();
		this.instrumento = venta.getInstrumento();
		this.usuario = venta.getUsuario();
		this.importe =  instrumento.getPrecioUsd() ;
		this.fecha = new Date();
		this.estado = venta.getEstado();
		this.contado = venta.isContado();
	//	this.cuotas = venta.getCuotas();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instrumento getInstrumento() {
		return instrumento;
	}

	public void setInstrumento(Instrumento instrumento) {
		this.instrumento = instrumento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	public Integer getCantidadCuota() {
		return cantidadCuota;
	}

	public void setCantidadCuota(Integer cantidadCuota) {
		this.cantidadCuota = cantidadCuota;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Boolean getContado() {
		return contado;
	}

	public void setContado(Boolean contado) {
		this.contado = contado;
	}

//	public List<Cuota> getCuotas() {
//		return cuotas;
//	}
//
//	public void setCuotas(List<Cuota> cuotas) {
//		this.cuotas = cuotas;
//	}

	@Override
	public String toString() {
		return "VentaDTO [id=" + id + ", instrumento=" + instrumento + ", usuario=" + usuario + ", importe=" + importe
				+ ", fecha=" + fecha + ", cantidadCuota=" + cantidadCuota + ", estado=" + estado + ", contado="
				+ contado
//				+ ", cuotas=" + cuotas 
				+			"]";
	}

	

	
}
