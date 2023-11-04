package cl.puertoesperanza.entrevistassa.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.puertoesperanza.entrevistassa.modelo.TipoCuenta;

@Repository
public interface TipoCuentaRepository extends CrudRepository<TipoCuenta,Integer> {

}
