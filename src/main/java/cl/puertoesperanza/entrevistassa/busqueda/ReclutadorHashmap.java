package cl.puertoesperanza.entrevistassa.busqueda;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import cl.puertoesperanza.entrevistassa.modelo.Reclutador;

public class ReclutadorHashmap {

	private HashMap<String, Reclutador> reclutadores = new HashMap<String, Reclutador>();
	
	public ReclutadorHashmap() {
		
	}
	
	public void add(Reclutador reclutador) {
		reclutadores.put(reclutador.getNombreReclutador(), reclutador);
	}

	public String toString() {
		String ret = "";
		for (Reclutador reclutador:reclutadores.values())
			ret += reclutador.toString()+"\n";
		return ret;
	}
	
	public Reclutador busca(String reclutador) {
		return reclutadores.get(reclutador);
	}
	
	public Collection<Reclutador> getList(){
		return reclutadores.values();
	}
	
	public void llenar(List<Reclutador> listareclutadores) {
		for (Reclutador r:listareclutadores) {
			reclutadores.put(r.getNombreReclutador(), r);
		}
	}
	
	public Reclutador obtener(int id) {
		Reclutador reclutador = new Reclutador();
		
		for (Reclutador r:getList()) {
			if (r.getId() == id) {
				reclutador = r;
			}
		}
		
		return reclutador;
	}
	
}
