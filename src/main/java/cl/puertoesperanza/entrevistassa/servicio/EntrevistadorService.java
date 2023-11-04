package cl.puertoesperanza.entrevistassa.servicio;

import java.util.List;

import cl.puertoesperanza.entrevistassa.modelo.Entrevistador;

public interface EntrevistadorService {

	List<Entrevistador> obtenerEntrevistadores();
	Entrevistador obtenerEntrevistadorPorId(Integer identrevistador);
	
}
