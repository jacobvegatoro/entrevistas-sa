package cl.puertoesperanza.entrevistassa.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.puertoesperanza.entrevistassa.modelo.Reclutador;
import cl.puertoesperanza.entrevistassa.repositorio.ReclutadorRepository;

@Service
public class ReclutadorServiceImpl implements ReclutadorService {

	@Autowired
	private ReclutadorRepository reclutadorRepositorio;
	
	@Override
	public List<Reclutador> obtenerReclutadores() {
		return (List<Reclutador>) reclutadorRepositorio.findAll();
	}

	@Override
	public Reclutador obtenerReclutadorPorId(Integer idReclutador) {
		Optional<Reclutador> reclutador = reclutadorRepositorio.findById(idReclutador);
		return reclutador.get();
	}

	@Override
	public boolean guardarReclutador(Reclutador reclutador) {
		boolean resultado = false;
		
		try {
			reclutadorRepositorio.save(reclutador);
			resultado = true;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return resultado;
	}

	@Override
	public boolean eliminarReclutador(Integer idReclutador) {
		boolean resultado = false;
		
		try {
			reclutadorRepositorio.deleteById(idReclutador);
			resultado = true;
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return resultado;
	}

}
