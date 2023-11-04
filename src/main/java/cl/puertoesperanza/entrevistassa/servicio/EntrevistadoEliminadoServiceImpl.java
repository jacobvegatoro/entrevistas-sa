package cl.puertoesperanza.entrevistassa.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.puertoesperanza.entrevistassa.modelo.EntrevistadoEliminado;
import cl.puertoesperanza.entrevistassa.repositorio.EntrevistadoEliminadoRepository;

@Service
public class EntrevistadoEliminadoServiceImpl implements EntrevistadoEliminadoService {

	@Autowired
	private EntrevistadoEliminadoRepository entrevistadoEliminadoRepositorio;
	
	@Override
	public boolean agregarEntrevistadoEliminado(EntrevistadoEliminado entrevistado) {
		if (entrevistadoEliminadoRepositorio.save(entrevistado) != null)
			return true;
		else
			return false;
	}

}
