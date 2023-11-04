package cl.puertoesperanza.entrevistassa.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.puertoesperanza.entrevistassa.modelo.Prevision;

@Repository
public interface PrevisionRepository extends CrudRepository<Prevision,Integer> {

}
