package cl.puertoesperanza.entrevistassa.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.puertoesperanza.entrevistassa.modelo.User;
import cl.puertoesperanza.entrevistassa.repositorio.UserRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UserRepository usuarioRepository;
	
	@Override
	public List<User> obtenerUsuarios() {
		return usuarioRepository.findAll();
	}

	@Override
	public User crearUsuario(User usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	public User obtenerUsuarioPorNombre(String username) {
		return usuarioRepository.findById(username).orElse(null);
	}

	@Override
	public boolean eliminarUsuario(String username) {
		boolean resultado = false;
		try {
			usuarioRepository.deleteById(username);
			resultado = true;
		}catch(Exception e) {
			System.out.println("Error al eliminar el usuario con ID: " + username);
		}
		return resultado;
	}

}
