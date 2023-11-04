package cl.puertoesperanza.entrevistassa.busqueda;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import cl.puertoesperanza.entrevistassa.modelo.Comuna;

public class ComunaHashmap {

	private HashMap<String, Comuna> comunas = new HashMap<String, Comuna>();
	
	public ComunaHashmap() {
		
	}
	
	public void add(Comuna comuna) {
		comunas.put(comuna.getNombreComuna(), comuna);
	}

	public String toString() {
		String ret = "";
		for (Comuna comuna:comunas.values())
			ret += comuna.toString()+"\n";
		return ret;
	}
	
	public Comuna busca(String nombrecomuna) {
		return comunas.get(nombrecomuna);
	}
	
	public Collection<Comuna> getList(){
		return comunas.values();
	}
	
	public void llenar(List<Comuna> listacomunas) {
		for (Comuna c:listacomunas) {
			comunas.put(c.getNombreComuna(), c);
		}
	}
	
	public Comuna obtener(int id) {
		Comuna comuna = new Comuna();
		
		for (Comuna c:getList()) {
			if (c.getId() == id) {
				comuna = c;
			}
		}
		
		return comuna;
	}
	
}
