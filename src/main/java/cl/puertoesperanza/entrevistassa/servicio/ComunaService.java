package cl.puertoesperanza.entrevistassa.servicio;

import java.util.List;

import cl.puertoesperanza.entrevistassa.modelo.Comuna;
import cl.puertoesperanza.entrevistassa.modelo.Region;

public interface ComunaService {

	List<Comuna> obtenerComunasPorRegion(Region region);
	Comuna obtenerComunaPorId(Integer idcomuna);
	List<Comuna> obtenerComunas();
	
}
