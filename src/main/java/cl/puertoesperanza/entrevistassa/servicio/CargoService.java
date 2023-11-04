package cl.puertoesperanza.entrevistassa.servicio;

import java.util.List;

import cl.puertoesperanza.entrevistassa.modelo.Cargo;

public interface CargoService {

	List<Cargo> obtenerCargos();
	Cargo obtenerCargoPorId(Integer idcargo);
	boolean guardarCargo(Cargo cargo);
	boolean eliminarCargo(Integer idCargo);
	
}
