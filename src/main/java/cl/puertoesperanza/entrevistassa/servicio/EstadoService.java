package cl.puertoesperanza.entrevistassa.servicio;

import java.util.List;

import cl.puertoesperanza.entrevistassa.modelo.Estado;

public interface EstadoService {

	List<Estado> obtenerEstados();
	Estado obtenerEstadoPorId(Integer idestado);
	boolean guardarEstado(Estado estado);
	boolean eliminarEstado(Integer idEstado);

	
}
