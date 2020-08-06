package ar.edu.unju.fi.enumerado;

public enum EnumMensajes {
	INSTRUMENTO_ESTADO_DISPONIBLE("DISPONIBLE"),
	INSTRUMENTO_ESTADO_VENDIDO("VENDIDO"),	
	//Mensajes generales
	CAMPOS_VACIOS("Algunos campos est�n vac�os..."),
	NUMERO_ID_INEXISTENTE("El n�mero de ID no existe..."),
	AGREGADO_CORRECTO("Se agreg� el �tem correctamente..."),
	EDITADO_CORRECTO(" Se actualiz� el �tem correctamente..."),
	ELIMINADO_CORRECTO(" Se elimin� el �tem correctamente"),
	NUMERO_SERIE_DUPLICADO("El n�mero de serie est� duplicado..."),
	VENTA_ERROR_CUOTAS("S�lo se permite hacer el pago en 1,3,6 o 12 cuotas"),

	//Mensajes para cuotas
	CUOTA_ESTADO_PAGADO("PAGADA"),
	CUOTA_ESTADO_ADEUDA("ADEUDADA"),
	CUOTA_ESTADO_FINALIZADO("FINALIZADA"),
	CUOTA_TOTAL_ADEUDADO("El total adedudado en todas las ventas es: "),
	CUOTA_INDIVIDUAL_ADEUDADO("El total adedudado en esta venta es: "),
	VENTA_NO_ENCONTRADA("Venta no encontrada"),
	CUOTA_NO_EXISTENTE("No hay cuotas adeudadas para pagar"),
	CUOTA_PAGADA("La siguiente cuota ha sido pagada: "),
	CUOTA_SIN_MAS_PAGOS("La cuota a pagar ya no tiene pagos adeudados"),
	
	//Mensajes para usuarios
	USUARIO_ESTADO_INACTIVO("INACTIVO"),
	USUARIO_ESTADO_ACTIVO("ACTIVO"),
	USUARIO_EXISTENTE("El nombre de usuario ya existe"),
	USUARIO_CORREO_EXISTENTE("El correo ya existe"),
	USUARIO_INEXISTENTE("El usuario no existe"),
	USUARIO_CAMBIO_DE_ESTADO_POR_CONTRASE�A("CONTRASE�A INCORRECTA. SU USUARIO HA SIDO CAMBIADO A INACTIVO"),
	
	
	VENTA_ESTADO_ACTIVA("ACTIVA");
	
	
	private final String descripcion;
	
	public String getdescripcion() {
		return descripcion;
	}
	
	EnumMensajes(String descripcion){
		this.descripcion = descripcion;
	}

}
