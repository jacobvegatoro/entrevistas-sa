package cl.puertoesperanza.entrevistassa.busqueda;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import cl.puertoesperanza.entrevistassa.modelo.Presentacion;

public class PresentacionHashmap {

	private HashMap<String, Presentacion> presentaciones = new HashMap<String, Presentacion>();
	
	public PresentacionHashmap() {
		
	}
	
	public void add(Presentacion presentacion) {
		presentaciones.put(presentacion.getEstadoPresentacion(), presentacion);
	}

	public String toString() {
		String ret = "";
		for (Presentacion presentacion:presentaciones.values())
			ret += presentacion.toString()+"\n";
		return ret;
	}
	
	public Presentacion busca(String presentacion) {
		return presentaciones.get(presentacion);
	}
	
	public Collection<Presentacion> getList(){
		return presentaciones.values();
	}
	
	public void llenar(List<Presentacion> listapresentaciones) {
		for (Presentacion p:listapresentaciones) {
			presentaciones.put(p.getEstadoPresentacion(), p);
		}
	}
	
	public Presentacion obtener(int id) {
		Presentacion presentacion = new Presentacion();
		
		for (Presentacion p:getList()) {
			if (p.getId() == id) {
				presentacion = p;
			}
		}
		
		return presentacion;
	}
	
}
