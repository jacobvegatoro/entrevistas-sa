package cl.puertoesperanza.entrevistassa.servicio;

import java.util.Set;

import cl.puertoesperanza.entrevistassa.modelo.User;
import cl.puertoesperanza.entrevistassa.modelo.UserRole;

public interface UsuarioRolService {

	boolean crearUsuarioRol(UserRole usuarioRol);
	Set<UserRole> obtenerRolesUsuario(User usuario);
	long eliminarRolesUsuario(User usuario);
	
}
