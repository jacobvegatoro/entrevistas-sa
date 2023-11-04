package cl.puertoesperanza.entrevistassa.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.puertoesperanza.entrevistassa.modelo.Periodo;
import cl.puertoesperanza.entrevistassa.repositorio.PeriodoRepository;

@Service
public class PeriodoServiceImpl implements PeriodoService {

	@Autowired
	private PeriodoRepository periodoRepositorio;
	
	@Override
	public List<Periodo> obtenerPeriodos() {
		return (List<Periodo>) periodoRepositorio.findAll();
	}

}
