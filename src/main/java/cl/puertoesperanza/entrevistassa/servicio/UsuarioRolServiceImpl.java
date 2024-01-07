package cl.puertoesperanza.entrevistassa.servicio;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.puertoesperanza.entrevistassa.modelo.User;
import cl.puertoesperanza.entrevistassa.modelo.UserRole;
import cl.puertoesperanza.entrevistassa.repositorio.UserRoleRepository;

@Service
public class UsuarioRolServiceImpl implements UsuarioRolService {

	@Autowired
	private UserRoleRepository userRoleRepositorio;
	
	@Override
	public boolean crearUsuarioRol(UserRole usuarioRol) {
		if (userRoleRepositorio.save(usuarioRol) != null) {
			return true;			
		}
		else {
			return false;
		}
	}

	@Override
	public Set<UserRole> obtenerRolesUsuario(User usuario) {
		return userRoleRepositorio.findByUser(usuario);
	}

	@Override
	public long eliminarRolesUsuario(User usuario) {
		return userRoleRepositorio.deleteByUser(usuario);
	}

}
