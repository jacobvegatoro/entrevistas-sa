package cl.puertoesperanza.entrevistassa.servicio;

import java.util.List;

import cl.puertoesperanza.entrevistassa.modelo.Reclutador;

public interface ReclutadorService {

	List<Reclutador> obtenerReclutadores();
	Reclutador obtenerReclutadorPorId(Integer idReclutador);
	boolean guardarReclutador(Reclutador reclutador);
	boolean eliminarReclutador(Integer idReclutador);

}
