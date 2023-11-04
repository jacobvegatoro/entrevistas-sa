package cl.puertoesperanza.entrevistassa.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.puertoesperanza.entrevistassa.modelo.Instalacion;

@Repository
public interface InstalacionRepository extends CrudRepository<Instalacion,Integer> {

}
