package cl.puertoesperanza.entrevistassa.repositorio;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cl.puertoesperanza.entrevistassa.modelo.EntrevistadoVista;

@Repository
public interface EntrevistadoVistaRepository extends 
	CrudRepository<EntrevistadoVista,Integer>, 
	PagingAndSortingRepository<EntrevistadoVista,Integer>,
	JpaSpecificationExecutor<EntrevistadoVista>
{

	@Query (
			value = "select * from vw_entrevistado e where e.fecha_ingreso_rv >= :fechaingreso", 
			nativeQuery = true)
	List<EntrevistadoVista> findIngresoFiltrado(@Param("fechaingreso") String fechaingreso);
	
	List<EntrevistadoVista> findByUsername(String username);

	Page<EntrevistadoVista> findByUsername(String username, Pageable pageable);
	
}
