package cl.puertoesperanza.entrevistassa.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.puertoesperanza.entrevistassa.modelo.Parentesco;

@Repository
public interface ParentescoRepository extends CrudRepository<Parentesco,Integer> {

}
