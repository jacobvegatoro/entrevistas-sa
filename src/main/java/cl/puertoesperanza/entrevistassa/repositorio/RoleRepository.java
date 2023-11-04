package cl.puertoesperanza.entrevistassa.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.puertoesperanza.entrevistassa.modelo.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

	Role findByRole(String role);
	
}
