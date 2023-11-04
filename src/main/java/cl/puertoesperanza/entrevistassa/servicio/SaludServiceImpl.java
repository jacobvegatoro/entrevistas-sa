package cl.puertoesperanza.entrevistassa.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.puertoesperanza.entrevistassa.modelo.Salud;
import cl.puertoesperanza.entrevistassa.repositorio.SaludRepository;

@Service
public class SaludServiceImpl implements SaludService {

	@Autowired
	private SaludRepository saludRepositorio;
	
	@Override
	public List<Salud> obtenerSaludes() {
		return (List<Salud>) saludRepositorio.findAll();
	}

}
