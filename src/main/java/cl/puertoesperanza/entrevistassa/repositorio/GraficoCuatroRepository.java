package cl.puertoesperanza.entrevistassa.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.puertoesperanza.entrevistassa.modelo.GraficoCuatro;

@Repository
public interface GraficoCuatroRepository extends CrudRepository<GraficoCuatro,String> {

}
