package cl.puertoesperanza.entrevistassa.busqueda;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import cl.puertoesperanza.entrevistassa.modelo.Prevision;

public class PrevisionHashmap {

	private HashMap<String, Prevision> previsiones = new HashMap<String, Prevision>();
	
	public PrevisionHashmap() {
		
	}
	
	public void add(Prevision prevision) {
		previsiones.put(prevision.getDetallePrevision(), prevision);
	}

	public String toString() {
		String ret = "";
		for (Prevision prevision:previsiones.values())
			ret += prevision.toString()+"\n";
		return ret;
	}
	
	public Prevision busca(String prevision) {
		return previsiones.get(prevision);
	}
	
	public Collection<Prevision> getList(){
		return previsiones.values();
	}
	
	public void llenar(List<Prevision> listaprevisiones) {
		for (Prevision p:listaprevisiones) {
			previsiones.put(p.getDetallePrevision(), p);
		}
	}
	
	public Prevision obtener(int id) {
		Prevision prevision = new Prevision();
		
		for (Prevision p:getList()) {
			if (p.getId() == id) {
				prevision = p;
			}
		}
		
		return prevision;
	}
	
}
