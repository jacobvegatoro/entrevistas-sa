package cl.puertoesperanza.entrevistassa.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.puertoesperanza.entrevistassa.modelo.GraficoUno;

@Repository
public interface GraficoUnoRepository extends CrudRepository<GraficoUno,Integer> {

}
