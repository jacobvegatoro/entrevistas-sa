package cl.puertoesperanza.entrevistassa.busqueda;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import cl.puertoesperanza.entrevistassa.modelo.Canal;

public class CanalHashmap {

	private HashMap<String, Canal> canales = new HashMap<String, Canal>();
	
	public CanalHashmap() {
		
	}
	
	public void add(Canal canal) {
		canales.put(canal.getNombreCanal(), canal);
	}

	public String toString() {
		String ret = "";
		for (Canal canal:canales.values())
			ret += canal.toString()+"\n";
		return ret;
	}
	
	public Canal busca(String canal) {
		return canales.get(canal);
	}
	
	public Collection<Canal> getList(){
		return canales.values();
	}
	
	public void llenar(List<Canal> listacanales) {
		for (Canal c:listacanales) {
			canales.put(c.getNombreCanal(), c);
		}
	}
	
	public Canal obtener(int id) {
		Canal canal = new Canal();
		
		for (Canal c:getList()) {
			if (c.getId() == id) {
				canal = c;
			}
		}
		
		return canal;
	}
	
}
