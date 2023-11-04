package cl.puertoesperanza.entrevistassa.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.puertoesperanza.entrevistassa.modelo.Instalacion;
import cl.puertoesperanza.entrevistassa.repositorio.InstalacionRepository;

@Service
public class InstalacionServiceImpl implements InstalacionService {

	@Autowired
	private InstalacionRepository instalacionRepositorio;
	
	@Override
	public List<Instalacion> obtenerInstalaciones() {
		return (List<Instalacion>) instalacionRepositorio.findAll();
	}

	@Override
	public Instalacion obtenerInstalacionPorId(Integer idInstalacion) {
		Optional<Instalacion> instalacion = instalacionRepositorio.findById(idInstalacion);
		return instalacion.get();
	}

	@Override
	public boolean guardarInstalacion(Instalacion instalacion) {
		boolean resultado = false;
		
		try {
			instalacionRepositorio.save(instalacion);
			resultado = true;
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return resultado;
	}

	@Override
	public boolean eliminarInstalacion(Integer idInstalacion) {
		boolean resultado = false;
		
		try {
			instalacionRepositorio.deleteById(idInstalacion);
			resultado = true;
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return resultado;
	}

}
