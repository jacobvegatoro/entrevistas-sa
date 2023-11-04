package cl.puertoesperanza.entrevistassa.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.puertoesperanza.entrevistassa.modelo.Talla;

@Repository
public interface TallaRepository extends CrudRepository<Talla,Integer> {

}
