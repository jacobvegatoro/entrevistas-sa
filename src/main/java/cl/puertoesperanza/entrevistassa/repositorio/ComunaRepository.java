package cl.puertoesperanza.entrevistassa.repositorio;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.puertoesperanza.entrevistassa.modelo.Comuna;
import cl.puertoesperanza.entrevistassa.modelo.Region;

@Repository
public interface ComunaRepository extends CrudRepository<Comuna,Integer> {

	public List<Comuna> findByRegion(Region region);
	
}
