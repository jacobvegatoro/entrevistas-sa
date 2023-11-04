package cl.puertoesperanza.entrevistassa.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.puertoesperanza.entrevistassa.modelo.GraficoTres;
import cl.puertoesperanza.entrevistassa.repositorio.GraficoTresRepository;

@Service
public class GraficoTresServiceImpl implements GraficoTresService {

	@Autowired
	private GraficoTresRepository graficoTresRepositorio;
	
	@Override
	public List<GraficoTres> obtenerGraficoTres() {
		return (List<GraficoTres>) graficoTresRepositorio.findAll();
	}

}
