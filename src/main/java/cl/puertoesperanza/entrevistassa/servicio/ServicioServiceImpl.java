package cl.puertoesperanza.entrevistassa.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.puertoesperanza.entrevistassa.modelo.Servicio;
import cl.puertoesperanza.entrevistassa.repositorio.ServicioRepository;

@Service
public class ServicioServiceImpl implements ServicioService {

	@Autowired
	private ServicioRepository servicioRepositorio;
	
	@Override
	public List<Servicio> obtenerServicios() {
		return (List<Servicio>) servicioRepositorio.findAll();
	}

}
