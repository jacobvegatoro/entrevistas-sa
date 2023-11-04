package cl.puertoesperanza.entrevistassa.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.puertoesperanza.entrevistassa.modelo.GraficoDos;
import cl.puertoesperanza.entrevistassa.repositorio.GraficoDosRepository;

@Service
public class GraficoDosServiceImpl implements GraficoDosService {

	@Autowired
	private GraficoDosRepository graficoDosRepositorio;
	
	@Override
	public List<GraficoDos> obtenerGraficoDos() {
		return (List<GraficoDos>) graficoDosRepositorio.findAll();
	}

}
