package cl.puertoesperanza.entrevistassa.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.puertoesperanza.entrevistassa.modelo.Cargo;
import cl.puertoesperanza.entrevistassa.repositorio.CargoRepository;

@Service
public class CargoServiceImpl implements CargoService {

	@Autowired
	private CargoRepository cargoRepositorio;
	
	@Override
	public List<Cargo> obtenerCargos() {
		return (List<Cargo>) cargoRepositorio.findAll();
	}

	@Override
	public Cargo obtenerCargoPorId(Integer idcargo) {
		Optional<Cargo> cargo = cargoRepositorio.findById(idcargo);
		return cargo.get();
	}

	@Override
	public boolean guardarCargo(Cargo cargo) {
		boolean resultado = false;
		
		try {
			cargoRepositorio.save(cargo);
			resultado = true;
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return resultado;
	}

	@Override
	public boolean eliminarCargo(Integer idCargo) {
		boolean resultado = false;
		
		try {
			cargoRepositorio.deleteById(idCargo);
			resultado = true;
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return resultado;
	}

}
