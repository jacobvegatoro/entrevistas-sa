package cl.puertoesperanza.entrevistassa.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.puertoesperanza.entrevistassa.modelo.Comuna;
import cl.puertoesperanza.entrevistassa.modelo.Region;
import cl.puertoesperanza.entrevistassa.repositorio.ComunaRepository;

@Service
public class ComunaServiceImpl implements ComunaService {

	@Autowired
	private ComunaRepository comunaRepositorio;
	
	@Override
	public List<Comuna> obtenerComunasPorRegion(Region region) {
		return (List<Comuna>) comunaRepositorio.findByRegion(region);
	}

	@Override
	public Comuna obtenerComunaPorId(Integer idcomuna) {
		Optional<Comuna> optionalComuna = comunaRepositorio.findById(idcomuna);
		return optionalComuna.get();
	}

	@Override
	public List<Comuna> obtenerComunas() {
		return (List<Comuna>) comunaRepositorio.findAll();
	}

}
