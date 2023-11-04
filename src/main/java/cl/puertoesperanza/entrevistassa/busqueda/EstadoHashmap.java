package cl.puertoesperanza.entrevistassa.busqueda;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import cl.puertoesperanza.entrevistassa.modelo.Estado;

public class EstadoHashmap {

	private HashMap<String, Estado> estados = new HashMap<String, Estado>();
	
	public EstadoHashmap() {
		
	}
	
	public void add(Estado estado) {
		estados.put(estado.getDetalleEstado(), estado);
	}

	public String toString() {
		String ret = "";
		for (Estado estado:estados.values())
			ret += estado.toString()+"\n";
		return ret;
	}
	
	public Estado busca(String estado) {
		return estados.get(estado);
	}
	
	public Collection<Estado> getList(){
		return estados.values();
	}
	
	public void llenar(List<Estado> listaestados) {
		for (Estado e:listaestados) {
			estados.put(e.getDetalleEstado(), e);
		}
	}
	
	public Estado obtener(int id) {
		Estado estado = new Estado();
		
		for (Estado e:getList()) {
			if (e.getId() == id) {
				estado = e;
			}
		}
		
		return estado;
	}
	
}
