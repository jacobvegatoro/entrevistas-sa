package cl.puertoesperanza.entrevistassa.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.puertoesperanza.entrevistassa.modelo.Reclutador;

@Repository
public interface ReclutadorRepository extends CrudRepository<Reclutador,Integer> {

}
