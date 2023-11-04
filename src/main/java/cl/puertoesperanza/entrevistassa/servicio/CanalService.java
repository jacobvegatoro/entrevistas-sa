package cl.puertoesperanza.entrevistassa.servicio;

import java.util.List;

import cl.puertoesperanza.entrevistassa.modelo.Canal;

public interface CanalService {

	List<Canal> obtenerCanales();
	Canal obtenerCanalPorId(Integer idCanal);
	
}
