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

import cl.puertoesperanza.entrevistassa.busqueda.SearchSpecifications;
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

	List<EntrevistadoVista> findByUsernameOrderByIdEntrevistadoAsc(String username);

	List<EntrevistadoVista> findAllByOrderByIdEntrevistadoAsc();

	Page<EntrevistadoVista> findByUsername(String username, Pageable pageable);

	@Query (
			value = "select * from vw_entrevistado ve order by id_entrevistado limit :cantidad offset :desde", 
			nativeQuery = true)
	List<EntrevistadoVista> findRegistrosPagina(@Param("cantidad") int cantidad, @Param("desde") int desde);

	@Query (
			value = "select * from vw_entrevistado ve where ve.username = :username order by id_entrevistado limit :cantidad offset :desde", 
			nativeQuery = true)
	List<EntrevistadoVista> findRegistrosPaginaUsername(@Param("cantidad") int cantidad, @Param("desde") int desde, @Param("username") String username);

	long countByUsername(String username);

	@Query (
			value = "select * from vw_entrevistado ve ?3 order by id_entrevistado limit ?1 offset ?2",
			nativeQuery = true)
	List<EntrevistadoVista> findRegistrosPaginaCondicion(int cantidad, int desde, String condicion);
	
	Page<EntrevistadoVista> findByOrderByIdEntrevistadoAsc(SearchSpecifications<EntrevistadoVista> searchSpecifications, Pageable pageable);

}
