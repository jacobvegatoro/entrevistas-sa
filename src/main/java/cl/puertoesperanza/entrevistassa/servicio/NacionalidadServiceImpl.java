package cl.puertoesperanza.entrevistassa.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.puertoesperanza.entrevistassa.modelo.Nacionalidad;
import cl.puertoesperanza.entrevistassa.repositorio.NacionalidadRepository;

@Service
public class NacionalidadServiceImpl implements NacionalidadService {

	@Autowired
	private NacionalidadRepository nacionalidadRepositorio;
	
	@Override
	public List<Nacionalidad> obtenerNacionalidades() {
		return (List<Nacionalidad>) nacionalidadRepositorio.findAll();
	}

	@Override
	public Nacionalidad obtenerNacionalidadPorId(Integer idnacionalidad) {
		Optional<Nacionalidad> nacionalidad = nacionalidadRepositorio.findById(idnacionalidad);
		return nacionalidad.get();
	}

	@Override
	public boolean guardarNacionalidad(Nacionalidad nacionalidad) {
		boolean resultado = false;
		
		try {
			nacionalidadRepositorio.save(nacionalidad);
			resultado = true;
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return resultado;
	}

	@Override
	public boolean eliminarNacionalidad(Integer idnacionalidad) {
		boolean resultado = false;
		
		try {
			nacionalidadRepositorio.deleteById(idnacionalidad);
			resultado = true;
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return resultado;
	}

}
