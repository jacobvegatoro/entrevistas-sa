package cl.puertoesperanza.entrevistassa.busqueda;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import cl.puertoesperanza.entrevistassa.modelo.Salud;

public class SaludHashmap {
	private HashMap<String, Salud> saludes = new HashMap<String, Salud>();
	
	public SaludHashmap() {
		
	}
	
	public void add(Salud salud) {
		saludes.put(salud.getDetalleSalud(), salud);
	}

	public String toString() {
		String ret = "";
		for (Salud salud:saludes.values())
			ret += salud.toString()+"\n";
		return ret;
	}
	
	public Salud busca(String salud) {
		return saludes.get(salud);
	}
	
	public Collection<Salud> getList(){
		return saludes.values();
	}
	
	public void llenar(List<Salud> listasaludes) {
		for (Salud s:listasaludes) {
			saludes.put(s.getDetalleSalud(), s);
		}
	}
	
	public Salud obtener(int id) {
		Salud salud = new Salud();
		
		for (Salud s:getList()) {
			if (s.getId() == id) {
				salud = s;
			}
		}
		
		return salud;
	}
	
}
