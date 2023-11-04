package cl.puertoesperanza.entrevistassa.servicio;

import java.util.List;

import cl.puertoesperanza.entrevistassa.modelo.Region;

public interface RegionService {

	List<Region> obtenerRegiones();
	Region obtenerRegionPorId(Integer idregion);
	
}
