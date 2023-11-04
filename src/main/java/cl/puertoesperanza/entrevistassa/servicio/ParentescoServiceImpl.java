package cl.puertoesperanza.entrevistassa.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.puertoesperanza.entrevistassa.modelo.Parentesco;
import cl.puertoesperanza.entrevistassa.repositorio.ParentescoRepository;

@Service
public class ParentescoServiceImpl implements ParentescoService {

	@Autowired
	private ParentescoRepository parentescoRepositorio;
	
	@Override
	public List<Parentesco> obtenerParentescos() {
		return (List<Parentesco>) parentescoRepositorio.findAll();
	}

}
