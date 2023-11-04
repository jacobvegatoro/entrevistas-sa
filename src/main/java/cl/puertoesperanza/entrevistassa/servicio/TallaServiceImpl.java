package cl.puertoesperanza.entrevistassa.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.puertoesperanza.entrevistassa.modelo.Talla;
import cl.puertoesperanza.entrevistassa.repositorio.TallaRepository;

@Service
public class TallaServiceImpl implements TallaService {

	@Autowired
	private TallaRepository tallaRepositorio;
	
	@Override
	public List<Talla> obtenerTallas() {
		return (List<Talla>) tallaRepositorio.findAll();
	}

}
