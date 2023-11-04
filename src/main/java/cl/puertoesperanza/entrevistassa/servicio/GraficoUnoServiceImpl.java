package cl.puertoesperanza.entrevistassa.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.puertoesperanza.entrevistassa.modelo.GraficoUno;
import cl.puertoesperanza.entrevistassa.repositorio.GraficoUnoRepository;

@Service
public class GraficoUnoServiceImpl implements GraficoUnoService {

	@Autowired
	private GraficoUnoRepository graficoUnoRepositorio;
	
	@Override
	public List<GraficoUno> obtenerGraficoUno() {
		return (List<GraficoUno>) graficoUnoRepositorio.findAll();
	}
	
}
