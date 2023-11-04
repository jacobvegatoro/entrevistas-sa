package cl.puertoesperanza.entrevistassa.busqueda;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import cl.puertoesperanza.entrevistassa.modelo.Servicio;

public class ServicioHashmap {

	private HashMap<String, Servicio> servicios = new HashMap<String, Servicio>();
	
	public ServicioHashmap() {
		
	}
	
	public void add(Servicio servicio) {
		servicios.put(servicio.getDetalleServicio(), servicio);
	}

	public String toString() {
		String ret = "";
		for (Servicio servicio:servicios.values())
			ret += servicio.toString()+"\n";
		return ret;
	}
	
	public Servicio busca(String servicio) {
		return servicios.get(servicio);
	}
	
	public Collection<Servicio> getList(){
		return servicios.values();
	}
	
	public void llenar(List<Servicio> listaservicios) {
		for (Servicio s:listaservicios) {
			servicios.put(s.getDetalleServicio(), s);
		}
	}
	
	public Servicio obtener(int id) {
		Servicio servicio = new Servicio();
		
		for (Servicio s:getList()) {
			if (s.getId() == id) {
				servicio = s;
			}
		}
		
		return servicio;
	}
	
}
