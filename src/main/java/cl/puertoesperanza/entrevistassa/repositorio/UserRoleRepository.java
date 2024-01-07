package cl.puertoesperanza.entrevistassa.repositorio;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.puertoesperanza.entrevistassa.modelo.User;
import cl.puertoesperanza.entrevistassa.modelo.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

	public Set<UserRole> findByUser(User user);
	public Long deleteByUser(User user);
	
}
