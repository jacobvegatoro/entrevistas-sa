package cl.puertoesperanza.entrevistassa.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.puertoesperanza.entrevistassa.modelo.SeguroCovid;

@Repository
public interface SeguroCovidRepository extends CrudRepository<SeguroCovid,Integer> {

}
