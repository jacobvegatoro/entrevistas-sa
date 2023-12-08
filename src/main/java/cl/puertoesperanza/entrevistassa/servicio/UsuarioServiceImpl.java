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

}
