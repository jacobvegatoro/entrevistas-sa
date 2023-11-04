package cl.puertoesperanza.entrevistassa.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.puertoesperanza.entrevistassa.modelo.GraficoTres;

@Repository
public interface GraficoTresRepository extends CrudRepository<GraficoTres,String> {

}
