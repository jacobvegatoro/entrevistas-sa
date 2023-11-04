package cl.puertoesperanza.entrevistassa.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.puertoesperanza.entrevistassa.modelo.Presentacion;
import cl.puertoesperanza.entrevistassa.repositorio.PresentacionRepository;

@Service
public class PresentacionServiceImpl implements PresentacionService {

	@Autowired
	private PresentacionRepository presentacionRepositorio;
	
	@Override
	public List<Presentacion> obtenerPresentaciones() {
		return (List<Presentacion>) presentacionRepositorio.findAll();
	}

}
