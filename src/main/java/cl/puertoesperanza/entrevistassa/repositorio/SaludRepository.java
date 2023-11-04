package cl.puertoesperanza.entrevistassa.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.puertoesperanza.entrevistassa.modelo.Salud;

@Repository
public interface SaludRepository extends CrudRepository<Salud,Integer> {

}
