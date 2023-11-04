package cl.puertoesperanza.entrevistassa.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.puertoesperanza.entrevistassa.modelo.Presentacion;

@Repository
public interface PresentacionRepository extends CrudRepository<Presentacion,Integer> {

}
