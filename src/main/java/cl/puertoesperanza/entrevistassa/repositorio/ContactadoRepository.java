package cl.puertoesperanza.entrevistassa.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.puertoesperanza.entrevistassa.modelo.Contactado;

@Repository
public interface ContactadoRepository extends CrudRepository<Contactado,Integer> {

}
