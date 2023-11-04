package cl.puertoesperanza.entrevistassa.busqueda;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import cl.puertoesperanza.entrevistassa.modelo.Talla;

public class TallaHashmap {

	private HashMap<String, Talla> tallas = new HashMap<String, Talla>();
	
	public TallaHashmap() {
		
	}
	
	public void add(Talla talla) {
		tallas.put(talla.getNombreTalla(), talla);
	}

	public String toString() {
		String ret = "";
		for (Talla talla:tallas.values())
			ret += talla.toString()+"\n";
		return ret;
	}
	
	public Talla busca(String talla) {
		return tallas.get(talla);
	}
	
	public Collection<Talla> getList(){
		return tallas.values();
	}
	
	public void llenar(List<Talla> listatallas) {
		for (Talla t:listatallas) {
			tallas.put(t.getNombreTalla(), t);
		}
	}
	
	public Talla obtener(int id) {
		Talla talla = new Talla();
		
		for (Talla t:getList()) {
			if (t.getId() == id) {
				talla = t;
			}
		}
		
		return talla;
	}
	
}
