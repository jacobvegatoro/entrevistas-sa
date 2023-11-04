package cl.puertoesperanza.entrevistassa.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.puertoesperanza.entrevistassa.modelo.SeguroCovid;
import cl.puertoesperanza.entrevistassa.repositorio.SeguroCovidRepository;

@Service
public class SeguroCovidServiceImpl implements SeguroCovidService {

	@Autowired
	private SeguroCovidRepository seguroCovidRepositorio;
	
	@Override
	public List<SeguroCovid> obtenerSegurosCovid() {
		return (List<SeguroCovid>) seguroCovidRepositorio.findAll();
	}

}
