package cl.puertoesperanza.entrevistassa.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.puertoesperanza.entrevistassa.modelo.Nacionalidad;

@Repository
public interface NacionalidadRepository extends CrudRepository<Nacionalidad,Integer> {

}
