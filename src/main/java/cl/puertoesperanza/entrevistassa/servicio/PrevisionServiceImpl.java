package cl.puertoesperanza.entrevistassa.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.puertoesperanza.entrevistassa.modelo.Prevision;
import cl.puertoesperanza.entrevistassa.repositorio.PrevisionRepository;

@Service
public class PrevisionServiceImpl implements PrevisionService {

	@Autowired
	private PrevisionRepository previsionRepositorio;
	
	@Override
	public List<Prevision> obtenerPrevisiones() {
		return (List<Prevision>) previsionRepositorio.findAll();
	}

}
