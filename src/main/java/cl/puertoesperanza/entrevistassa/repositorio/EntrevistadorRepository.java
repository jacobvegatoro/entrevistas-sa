package cl.puertoesperanza.entrevistassa.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.puertoesperanza.entrevistassa.modelo.Entrevistador;

@Repository
public interface EntrevistadorRepository extends CrudRepository<Entrevistador,Integer> {

}
