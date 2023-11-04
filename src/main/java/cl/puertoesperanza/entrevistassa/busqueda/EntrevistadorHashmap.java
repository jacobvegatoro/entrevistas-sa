package cl.puertoesperanza.entrevistassa.busqueda;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import cl.puertoesperanza.entrevistassa.modelo.Entrevistador;

public class EntrevistadorHashmap {

	private HashMap<String, Entrevistador> entrevistadores = new HashMap<String, Entrevistador>();
	
	public EntrevistadorHashmap() {
		
	}
	
	public void add(Entrevistador entrevistador) {
		entrevistadores.put(entrevistador.getNombreEntrevistador(), entrevistador);
	}

	public String toString() {
		String ret = "";
		for (Entrevistador entrevistador:entrevistadores.values())
			ret += entrevistador.toString()+"\n";
		return ret;
	}
	
	public Entrevistador busca(String entrevistador) {
		return entrevistadores.get(entrevistador);
	}
	
	public Collection<Entrevistador> getList(){
		return entrevistadores.values();
	}
	
	public void llenar(List<Entrevistador> listaentrevistadores) {
		for (Entrevistador e:listaentrevistadores) {
			entrevistadores.put(e.getNombreEntrevistador(), e);
		}
	}
	
	public Entrevistador obtener(int id) {
		Entrevistador entrevistador = new Entrevistador();
		
		for (Entrevistador e:getList()) {
			if (e.getId() == id) {
				entrevistador = e;
			}
		}
		
		return entrevistador;
	}
	
}
