package cl.puertoesperanza.entrevistassa.busqueda;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import cl.puertoesperanza.entrevistassa.modelo.Nacionalidad;

public class NacionalidadHashmap {

	private HashMap<String, Nacionalidad> nacionalidades = new HashMap<String, Nacionalidad>();
	
	public NacionalidadHashmap() {
		
	}
	
	public void add(Nacionalidad nacionalidad) {
		nacionalidades.put(nacionalidad.getDetalleNacionalidad(), nacionalidad);
	}

	public String toString() {
		String ret = "";
		for (Nacionalidad nacionalidad:nacionalidades.values())
			ret += nacionalidad.toString()+"\n";
		return ret;
	}
	
	public Nacionalidad busca(String nacionalidad) {
		return nacionalidades.get(nacionalidad);
	}
	
	public Collection<Nacionalidad> getList(){
		return nacionalidades.values();
	}
	
	public void llenar(List<Nacionalidad> listanacionalidades) {
		for (Nacionalidad n:listanacionalidades) {
			nacionalidades.put(n.getDetalleNacionalidad(), n);
		}
	}
	
	public Nacionalidad obtener(int id) {
		Nacionalidad nacionalidad = new Nacionalidad();
		
		for (Nacionalidad n:getList()) {
			if (n.getId() == id) {
				nacionalidad = n;
			}
		}
		
		return nacionalidad;
	}
	
}
