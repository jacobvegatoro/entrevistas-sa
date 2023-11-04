package cl.puertoesperanza.entrevistassa.busqueda;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import cl.puertoesperanza.entrevistassa.modelo.Cargo;

public class CargoHashmap {

	private HashMap<String, Cargo> cargos = new HashMap<String, Cargo>();
	
	public CargoHashmap() {
		
	}
	
	public void add(Cargo cargo) {
		cargos.put(cargo.getNombreCargo(), cargo);
	}

	public String toString() {
		String ret = "";
		for (Cargo cargo:cargos.values())
			ret += cargo.toString()+"\n";
		return ret;
	}
	
	public Cargo busca(String nombrecargo) {
		return cargos.get(nombrecargo);
	}
	
	public Collection<Cargo> getList(){
		return cargos.values();
	}
	
	public void llenar(List<Cargo> listacargos) {
		for (Cargo c:listacargos) {
			cargos.put(c.getNombreCargo(), c);
		}
	}
	
	public Cargo obtener(int id) {
		Cargo cargo = new Cargo();
		
		for (Cargo c:getList()) {
			if (c.getId() == id) {
				cargo = c;
			}
		}
		
		return cargo;
	}
	
}
