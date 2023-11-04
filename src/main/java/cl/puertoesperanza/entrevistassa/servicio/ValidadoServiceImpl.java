package cl.puertoesperanza.entrevistassa.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.puertoesperanza.entrevistassa.modelo.Validado;
import cl.puertoesperanza.entrevistassa.repositorio.ValidadoRepository;

@Service
public class ValidadoServiceImpl implements ValidadoService {

	@Autowired
	private ValidadoRepository validadoRepositorio;

	@Override
	public List<Validado> obtenerValidados() {
		return (List<Validado>) validadoRepositorio.findAll();
	}

}
