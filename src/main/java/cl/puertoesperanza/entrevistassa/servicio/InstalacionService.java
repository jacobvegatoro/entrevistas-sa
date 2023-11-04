package cl.puertoesperanza.entrevistassa.servicio;

import java.util.List;

import cl.puertoesperanza.entrevistassa.modelo.Instalacion;

public interface InstalacionService {

	List<Instalacion> obtenerInstalaciones();
	Instalacion obtenerInstalacionPorId(Integer idInstalacion);
	boolean guardarInstalacion(Instalacion instalacion);
	boolean eliminarInstalacion(Integer idInstalacion);
	
}
