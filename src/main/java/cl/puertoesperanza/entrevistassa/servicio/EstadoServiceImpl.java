package cl.puertoesperanza.entrevistassa.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.puertoesperanza.entrevistassa.modelo.Estado;
import cl.puertoesperanza.entrevistassa.repositorio.EstadoRepository;

@Service
public class EstadoServiceImpl implements EstadoService {

	@Autowired
	private EstadoRepository estadoRepositorio;
	
	@Override
	public List<Estado> obtenerEstados() {
		return (List<Estado>) estadoRepositorio.findAll();
	}

	@Override
	public Estado obtenerEstadoPorId(Integer idestado) {
		Optional<Estado> estado = estadoRepositorio.findById(idestado);
		return estado.get();
	}

	@Override
	public boolean guardarEstado(Estado estado) {
		boolean resultado = false;
		
		try {
			estadoRepositorio.save(estado);
			resultado = true;
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return resultado;
	}

	@Override
	public boolean eliminarEstado(Integer idEstado) {
		boolean resultado = false;
		
		try {
			estadoRepositorio.deleteById(idEstado);
			resultado = true;
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return resultado;
	}

}
