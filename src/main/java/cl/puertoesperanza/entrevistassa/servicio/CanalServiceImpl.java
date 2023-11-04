package cl.puertoesperanza.entrevistassa.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.puertoesperanza.entrevistassa.modelo.Canal;
import cl.puertoesperanza.entrevistassa.repositorio.CanalRepository;

@Service
public class CanalServiceImpl implements CanalService {

	@Autowired
	private CanalRepository canalRepository;

	@Override
	public List<Canal> obtenerCanales() {
		return (List<Canal>) canalRepository.findAll();
	}

	@Override
	public Canal obtenerCanalPorId(Integer idCanal) {
		Optional<Canal> canal = canalRepository.findById(idCanal);
		return canal.get();
	}
	
}
