package ar.edu.unju.fi.services.implementation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.dao.VentaDao;
import ar.edu.unju.fi.dominio.Cuota;
import ar.edu.unju.fi.dominio.Venta;
import ar.edu.unju.fi.dominio.VentaContado;
import ar.edu.unju.fi.dominio.VentaFinanciado;
import ar.edu.unju.fi.dto.CuotaDTO;
import ar.edu.unju.fi.dto.VentaDTO;
import ar.edu.unju.fi.enumerado.EnumMensajes;
import ar.edu.unju.fi.exceptions.GeneralException;
import ar.edu.unju.fi.services.VentaService;
import ar.edu.unju.fi.util.FechaUtil;
import ar.edu.unju.fi.util.NumeroUtil;
import ar.edu.unju.fi.util.VentaUtil;

@Service
public class VentaServiceImp implements VentaService  {
	private static Logger log = Logger.getLogger(VentaServiceImp.class);
	@Inject private VentaDao ventaDao;
	private ModelMapper mapper = new ModelMapper();


	
	public VentaDTO add(VentaDTO ventaDto) throws IOException, GeneralException {		
		if(ventaDto != null && ventaDto.getInstrumento().getId() != null && ventaDto.getUsuario().getId() != null) {			
			Venta venta = getInstanceVenta(ventaDto.getCantidadCuota());			
			mapper.map(ventaDto, venta);	
			venta.setEstado(EnumMensajes.VENTA_ESTADO_ACTIVA.getdescripcion());
			venta = addCuota(venta, ventaDto.getCantidadCuota());
			venta = generarMontoDeCuota(venta);
			ventaDao.add(venta);
			ventaDto.setId(venta.getId());
			return ventaDto;
		}
		throw new GeneralException(EnumMensajes.CAMPOS_VACIOS.getdescripcion());		
	}

	
	
	public VentaDTO update(VentaDTO ventaDto) throws GeneralException {		
		Venta venta = buscarById(ventaDto.getId());
		if(venta != null) {
			mapper.map(ventaDto, venta);
			ventaDao.update(venta);
			return ventaDto;
		}
		throw new GeneralException(EnumMensajes.NUMERO_ID_INEXISTENTE.getdescripcion());		
	} 
	
	public VentaDTO buscarVenta(long id) throws GeneralException {
	VentaDTO ventaDto = new VentaDTO();
	Venta venta = buscarById(id);
	mapper.map(venta, ventaDto);	
	return ventaDto;
	}
	
	public VentaDTO delete(VentaDTO ventaDto) throws GeneralException {
		Venta venta = buscarById(ventaDto.getId());		
		if(venta != null) {
			mapper.map(ventaDto, venta);
			ventaDao.delete(venta);
			return ventaDto;
		}
		throw new GeneralException(EnumMensajes.NUMERO_ID_INEXISTENTE.getdescripcion());		
	}
	
	public Venta buscarById(long id) throws GeneralException {
		Venta ventaEncontrada = (Venta) ventaDao.findByID(id);
		if(ventaEncontrada!= null ) {
			return ventaEncontrada;
		} 			
		throw new GeneralException(EnumMensajes.NUMERO_ID_INEXISTENTE.getdescripcion());		
	}


	

	public Venta addCuota(Venta venta,Integer cantidadCuotas) {		
			for(int i=0; i< cantidadCuotas; i++) {
				Cuota generarCuota = new Cuota();
				generarCuota.setVenta(venta);
				generarCuota.setNumeroCuota((long) venta.getCuotas().size()+1);
				generarCuota.setFechaVencimiento(new Date());
				venta.getCuotas().add(generarCuota);
			}
			return venta;	
		
	}

	public Venta generarMontoDeCuota(Venta venta) throws IOException {
		if(venta.isContado()) {			
			return generarMontoDeContado(venta);
		}		
		if(venta.isFinanciado()) {
			return generarMontoDeFinanciado(venta);
		}		
		return null;		
	}

	public Venta generarMontoDeContado(Venta venta) throws IOException {
		VentaContado ventaContado = (VentaContado) venta;
		ventaContado.setDescuento(VentaUtil.getInteresODescuento( ventaContado.getCuotas().size()));
		ventaContado.setImporte(VentaUtil.convertirDolaresPesos(ventaContado));		
		ventaContado.setImporteTotal(VentaUtil.calcularImporteTotal(ventaContado));
		ventaContado.setEstado(EnumMensajes.CUOTA_ESTADO_FINALIZADO.getdescripcion());
		ventaContado.getCuotas().get(0).setFechaPago(new Date());
		ventaContado.getCuotas().get(0).setFechaVencimiento(new Date());
		ventaContado.getCuotas().get(0).setValor(VentaUtil.calcularImporteTotal(ventaContado) /ventaContado.getCuotas().size());
		ventaContado.getCuotas().get(0).setEstado(EnumMensajes.CUOTA_ESTADO_PAGADO.getdescripcion());
		return ventaContado;
	}

	public Venta generarMontoDeFinanciado(Venta venta) throws IOException {
		VentaFinanciado ventaFinanciado = (VentaFinanciado) venta;
		ventaFinanciado.setInteres(VentaUtil.getInteresODescuento( ventaFinanciado.getCuotas().size()));
		ventaFinanciado.setImporte(VentaUtil.convertirDolaresPesos(ventaFinanciado));		
		ventaFinanciado.setImporteTotal(VentaUtil.calcularImporteTotal(ventaFinanciado));
		for(int i = 0; i<ventaFinanciado.getCuotas().size();i++) {
			ventaFinanciado.getCuotas().get(i).setValor(NumeroUtil.dosDecimales(ventaFinanciado.getImporteTotal() /ventaFinanciado.getCuotas().size()));
			ventaFinanciado.getCuotas().get(i).setFechaVencimiento(FechaUtil.sumarFechas(ventaFinanciado.getCuotas().get(i).getFechaVencimiento(),i+1));
			ventaFinanciado.getCuotas().get(i).setEstado(EnumMensajes.CUOTA_ESTADO_ADEUDA.getdescripcion());
		}
		return ventaFinanciado;	
	}

	
	public Cuota pagarCuota(Venta venta) {		
		for(Cuota o: venta.getCuotas()) {
			if(o.getEstado().contentEquals(EnumMensajes.CUOTA_ESTADO_ADEUDA.getdescripcion())) {				
				if(o.getNumeroCuota() == venta.getCuotas().size()) {					
					return cambiarEstadoCuotaFinalizada(o,venta);
				}				
				return cambiarEstadoCuotaPagada(o,venta);
			}
		}		
		log.info(EnumMensajes.CUOTA_SIN_MAS_PAGOS.getdescripcion());
		return null;
	}
	
	
	public Cuota cambiarEstadoCuotaPagada(Cuota cuota,Venta venta) {
		
		cuota.setEstado(EnumMensajes.CUOTA_ESTADO_PAGADO.getdescripcion());
		cuota.setFechaPago(new Date());				
		log.info(EnumMensajes.CUOTA_PAGADA.getdescripcion());
		ventaDao.update(venta);
		return cuota;
	}
	
	public Cuota cambiarEstadoCuotaFinalizada(Cuota cuota,Venta venta) {
	cuota.setEstado(EnumMensajes.CUOTA_ESTADO_PAGADO.getdescripcion());
	cuota.setFechaPago(new Date());
	venta.setEstado(EnumMensajes.CUOTA_ESTADO_FINALIZADO.getdescripcion());
	log.info(EnumMensajes.CUOTA_ESTADO_PAGADO.getdescripcion());	
	ventaDao.update(venta);
		return cuota;
	}

	public Double verImporteTotalDeUnaVenta(Venta venta) {
		Double importeTotal= 0d;
		for(Cuota o: venta.getCuotas()) {
			if(o.getEstado().equals(EnumMensajes.CUOTA_ESTADO_ADEUDA.getdescripcion())) {
				importeTotal = importeTotal + o.getValor();
			}
		}		
		if(importeTotal != 0) {
			log.info(EnumMensajes.CUOTA_INDIVIDUAL_ADEUDADO.getdescripcion()+importeTotal);	
		}		
		importeTotal = NumeroUtil.dosDecimales(importeTotal);
		return importeTotal;
	}


	
	public Venta getInstanceVenta(Integer numeroCuotas) {
		if(numeroCuotas == 1) {
			VentaContado venta = new VentaContado();
			return venta;
		}else {
			VentaFinanciado venta = new VentaFinanciado();
			return venta;
		}
	}



	/**
	 * Lo agregue para obtener todas las ventas
	 * @author Enzo
	 * @return Lista de ventas realizadas.
	 */
	public List obtenerTodos() {
		return ventaDao.getAll();
	}
	
	
	public VentaDTO determinarCantidadCuotas(VentaDTO venta, Integer cantidadCuotas) throws GeneralException{
		if(cantidadCuotas == 1 || cantidadCuotas == 3 || cantidadCuotas == 6 || cantidadCuotas == 12) {
		venta.setCantidadCuota(cantidadCuotas);
		return venta;
		}		
		 throw new GeneralException(EnumMensajes.VENTA_ERROR_CUOTAS.getdescripcion());
	}
	
	
	
	public Double getDeudaVentas() {
		
			try {
				Double result = 0d;
				List<Cuota> listaCuotas = ventaDao.getTodasAdeudadas();
				for(int i = 0; i<listaCuotas.size();i++) {
					Cuota cuota = listaCuotas.get(i);
					result = result + (Double)cuota.getValor();
				}
				log.info(EnumMensajes.CUOTA_TOTAL_ADEUDADO.getdescripcion() + result);
				result =NumeroUtil.dosDecimales(result);
				return result;
			} catch (GeneralException e) {
				e.printStackTrace();
			}
			return null;
		
		
	}

}
