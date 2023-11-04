package cl.puertoesperanza.entrevistassa.busqueda;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import cl.puertoesperanza.entrevistassa.modelo.Entrevistado;

public class EntrevistadoHashmap {

	private HashMap<String,Entrevistado> entrevistados = new HashMap<String, Entrevistado>();
	
	public EntrevistadoHashmap() {
		
	}
	
	public void add(Entrevistado entrevistado) {
		entrevistados.put(entrevistado.getRun(), entrevistado);
	}
	
	public String toString() {
		String ret = "";
		for (Entrevistado entrevistado:entrevistados.values())
			ret += entrevistado.getRun() + " " + entrevistado.getNombres() + " " + entrevistado.getApPaterno() + "\n";
		return ret;
	}
	
	public Entrevistado busca(String run) {
		return entrevistados.get(run);
	}
	
	public Collection<Entrevistado> getList(){
		return entrevistados.values();
	}
	
	public void llenar(List<Entrevistado> listaentrevistados) {
		for (Entrevistado e:listaentrevistados) {
			entrevistados.put(e.getRun(), e);
		}
	}
	
}
