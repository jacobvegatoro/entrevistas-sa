package cl.puertoesperanza.entrevistassa.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.puertoesperanza.entrevistassa.modelo.Region;
import cl.puertoesperanza.entrevistassa.repositorio.RegionRepository;

@Service
public class RegionServiceImpl implements RegionService {

	@Autowired
	private RegionRepository regionRepositorio;
	
	@Override
	public List<Region> obtenerRegiones() {
		return (List<Region>) regionRepositorio.findAll();
	}

	@Override
	public Region obtenerRegionPorId(Integer idregion) {
		Optional<Region> optionalRegion = regionRepositorio.findById(idregion);
		return optionalRegion.get();
	}

	
	
}
