package cl.puertoesperanza.entrevistassa.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.puertoesperanza.entrevistassa.modelo.Contactado;
import cl.puertoesperanza.entrevistassa.repositorio.ContactadoRepository;

@Service
public class ContactadoServiceImpl implements ContactadoService {

	@Autowired
	private ContactadoRepository contactadoRepositorio;
	
	@Override
	public List<Contactado> obtenerContactados() {
		return (List<Contactado>) contactadoRepositorio.findAll();
	}

}
