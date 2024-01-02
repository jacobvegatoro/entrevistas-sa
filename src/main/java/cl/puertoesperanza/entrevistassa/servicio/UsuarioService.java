package cl.puertoesperanza.entrevistassa.servicio;

import java.util.List;

import cl.puertoesperanza.entrevistassa.modelo.User;

public interface UsuarioService {

	List<User> obtenerUsuarios();
	User crearUsuario(User usuario);
	User obtenerUsuarioPorNombre(String username);
	boolean eliminarUsuario(String username);
	
}
