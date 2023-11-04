package cl.puertoesperanza.entrevistassa.busqueda;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import cl.puertoesperanza.entrevistassa.modelo.Instalacion;

public class InstalacionHashmap {

	private HashMap<String, Instalacion> instalaciones = new HashMap<String, Instalacion>();
	
	public InstalacionHashmap() {
		
	}
	
	public void add(Instalacion instalacion) {
		instalaciones.put(instalacion.getNombreInstalacion(), instalacion);
	}

	public String toString() {
		String ret = "";
		for (Instalacion instalacion:instalaciones.values())
			ret += instalacion.toString()+"\n";
		return ret;
	}
	
	public Instalacion busca(String instalacion) {
		return instalaciones.get(instalacion);
	}
	
	public Collection<Instalacion> getList(){
		return instalaciones.values();
	}
	
	public void llenar(List<Instalacion> listainstalaciones) {
		for (Instalacion i:listainstalaciones) {
			instalaciones.put(i.getNombreInstalacion(), i);
		}
	}
	
	public Instalacion obtener(int id) {
		Instalacion instalacion = new Instalacion();
		
		for (Instalacion i:getList()) {
			if (i.getId() == id) {
				instalacion = i;
			}
		}
		
		return instalacion;
	}
	
}
