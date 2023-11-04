package cl.puertoesperanza.entrevistassa.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.puertoesperanza.entrevistassa.modelo.Entrevistador;
import cl.puertoesperanza.entrevistassa.repositorio.EntrevistadorRepository;

@Service
public class EntrevistadorServiceImpl implements EntrevistadorService {

	@Autowired
	private EntrevistadorRepository entrevistadorRepository;
	
	@Override
	public List<Entrevistador> obtenerEntrevistadores() {
		return (List<Entrevistador>) entrevistadorRepository.findAll();
	}

	@Override
	public Entrevistador obtenerEntrevistadorPorId(Integer identrevistador) {
		Optional<Entrevistador> entrevistador = entrevistadorRepository.findById(identrevistador);
		return entrevistador.get();
	}

}
