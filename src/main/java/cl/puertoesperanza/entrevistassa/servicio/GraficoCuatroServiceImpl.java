package cl.puertoesperanza.entrevistassa.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.puertoesperanza.entrevistassa.modelo.GraficoCuatro;
import cl.puertoesperanza.entrevistassa.repositorio.GraficoCuatroRepository;

@Service
public class GraficoCuatroServiceImpl implements GraficoCuatroService {

	@Autowired
	private GraficoCuatroRepository graficoCuatroRepositorio;
	
	@Override
	public List<GraficoCuatro> obtenerGraficoCuatro() {
		return (List<GraficoCuatro>) graficoCuatroRepositorio.findAll();
	}

}
