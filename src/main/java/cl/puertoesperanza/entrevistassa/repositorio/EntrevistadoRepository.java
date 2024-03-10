package cl.puertoesperanza.entrevistassa.repositorio;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import cl.puertoesperanza.entrevistassa.modelo.Cargo;
import cl.puertoesperanza.entrevistassa.modelo.Cliente;
import cl.puertoesperanza.entrevistassa.modelo.Entrevistado;
import cl.puertoesperanza.entrevistassa.modelo.Instalacion;
import cl.puertoesperanza.entrevistassa.modelo.Reclutador;
import cl.puertoesperanza.entrevistassa.modelo.User;

@Repository
public interface EntrevistadoRepository extends CrudRepository<Entrevistado,Integer>,
 PagingAndSortingRepository<Entrevistado, Integer>, 
 JpaSpecificationExecutor<Entrevistado> {

	public List<Entrevistado> findByRun(String run);
	public List<Entrevistado> findByCliente(Cliente cliente);
	public List<Entrevistado> findByReclutador(Reclutador reclutador);
	public List<Entrevistado> findByInstalacion(Instalacion instalacion);
	public List<Entrevistado> findByCargo(Cargo cargo);
	public List<Entrevistado> findByNacionalidad(Integer idnacionalidad);
	public List<Entrevistado> findByEstado(Integer idestado);
	public List<Entrevistado> findByUsuario(User usuario);
	public Page<Entrevistado> findByUsuario(User usuario, Pageable pageable);
	public long countByRun(String run);
	
}
