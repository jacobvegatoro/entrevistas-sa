package cl.puertoesperanza.entrevistassa.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.puertoesperanza.entrevistassa.modelo.Region;

@Repository
public interface RegionRepository extends CrudRepository<Region,Integer> {

}
