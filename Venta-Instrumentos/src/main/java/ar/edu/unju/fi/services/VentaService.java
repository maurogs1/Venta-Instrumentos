package ar.edu.unju.fi.services;

import java.io.IOException;
import java.util.List;

import ar.edu.unju.fi.dominio.Cuota;
import ar.edu.unju.fi.dominio.Venta;
import ar.edu.unju.fi.dto.CuotaDTO;
import ar.edu.unju.fi.dto.VentaDTO;
import ar.edu.unju.fi.exceptions.GeneralException;

public interface VentaService {
	/**
	 * Agrega una venta
	 * @param ventaDto
	 * @return
	 * @throws IOException
	 * @throws GeneralException 
	 */
	public VentaDTO add(VentaDTO ventaDto)throws IOException, GeneralException;
	/**
	 * Actualiza una venta
	 * @param venta
	 * @return
	 * @throws GeneralException
	 */
	public VentaDTO update (VentaDTO venta) throws GeneralException;
	/**
	 * Borra una venta
	 * @param ventaDto
	 * @return
	 * @throws GeneralException
	 */
	public VentaDTO delete(VentaDTO ventaDto) throws GeneralException;
	
	
	/**
	 * Determina la cantidad de cuotas que tendr� la venta
	 * @param ventaDto
	 * @param cantidadCuotas 
	 * @return
	 * @throws GeneralException 
	 */
	public VentaDTO determinarCantidadCuotas(VentaDTO ventaDto, Integer cantidadCuotas) throws GeneralException;
	
	/**
	 * Busca una venta por el n�mero de ID
	 * @param id
	 * @return
	 * @throws GeneralException
	 */
	public Venta buscarById(long id) throws GeneralException;
	
	/**
	 * Busca una ventaDTO por el n�mero de ID
	 * @param id
	 * @return
	 * @throws GeneralException
	 */
	public VentaDTO buscarVenta(long id) throws GeneralException;
	
	
	/**
	 * Devuelve una instancia de un tipo de venta
	 * Puede devolver una venta financiada o venta contado
	 * @author mauro
	 * @param numeroCuotas
	 * @return
	 */
	public Venta getInstanceVenta(Integer numeroCuotas);


	
	
	/**
	 *agrega una cuota a la lista, dependiendo de cu�ntas cuotas se puso a la hora de crear la venta
	 *por ejemplo si se pone 3 cuotas a la hora de generar la venta, el m�todo agregar� 3 cuotas a la lista de cuotas que tiene esa venta
	 * @author mauro
	 * @param venta
	 * @param cantidadCuotas
	 * @return
	 */
	public Venta addCuota(Venta venta,Integer cantidadCuotas);
	
		
	
	/**
	 * Genera los montos con intereses/descuento que tendr�n las cuotas de la venta, dependiendo el n�mero de cuotas agregadas a la lista
	 * @author mauro 
	 * @param venta
	 * @return
	 * @throws IOException 
	 */
	public Venta generarMontoDeCuota(Venta venta) throws IOException;

	
	
	/**
	 * Genera el monto con descuento de sus respectivas cuotas
	 * @author mauro
	 * @param venta
	 * @return
	 * @throws IOException
	 */
	public Venta generarMontoDeContado(Venta venta)throws IOException;
	
	/**
	 * Genera el monto con inter�s de sus respectivas cuotas
	 * @author mauro
	 * @param venta
	 * @return
	 * @throws IOException
	 */
	public Venta generarMontoDeFinanciado(Venta venta)throws IOException;
	
	
	
	/**
	 * M�todo para pagar una cuota, a esa cuota el estado se le cambia dependiendo si tiene o no m�s cuotas
	 * Si tiene m�s cuotas, el estado de la cuota ser� "PAGADA", en cambio si ya no tiene m�s cuotas, su estado ser� FINALIZADA
	 * @author mauro
	 * @param venta
	 * @return
	 */
	public Cuota pagarCuota(Venta venta);
	
	/**
	 * Cambia el estado de una cuota a pagada
	 * @author mauro
	 * @param cuota
	 * @param venta
	 * @return
	 */
	public Cuota cambiarEstadoCuotaPagada(Cuota cuota, Venta venta);

	/**
	 * Cambia el estado de una cuota a finalizada
	 * @author mauro
	 * @param cuota
	 * @param venta
	 * @return
	 */
	public Cuota cambiarEstadoCuotaFinalizada(Cuota cuota, Venta venta);
	
	
	/**
	 * Devuelve el total de una cuenta adeudada
	 * @author mauro
	 * @param venta
	 * @return
	 */
	public Double verImporteTotalDeUnaVenta(Venta venta);
	
	/**
	 * Devuelve una lista con todas las ventas realizadas.
	 * @return Lista de ventas.
	 */
	public List<Venta> obtenerTodos();

	public Double getDeudaVentas();

}
