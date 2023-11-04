package cl.puertoesperanza.entrevistassa.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.puertoesperanza.entrevistassa.modelo.EntrevistadoEliminado;

@Repository
public interface EntrevistadoEliminadoRepository extends CrudRepository<EntrevistadoEliminado, Integer> {

}
