package cl.puertoesperanza.entrevistassa.busqueda;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import cl.puertoesperanza.entrevistassa.modelo.Parentesco;

public class ParentescoHashmap {

	private HashMap<String, Parentesco> parentescos = new HashMap<String, Parentesco>();
	
	public ParentescoHashmap() {
		
	}
	
	public void add(Parentesco parentesco) {
		parentescos.put(parentesco.getNombreParentesco(), parentesco);
	}

	public String toString() {
		String ret = "";
		for (Parentesco parentesco:parentescos.values())
			ret += parentesco.toString()+"\n";
		return ret;
	}
	
	public Parentesco busca(String parentesco) {
		return parentescos.get(parentesco);
	}
	
	public Collection<Parentesco> getList(){
		return parentescos.values();
	}
	
	public void llenar(List<Parentesco> listaparentescos) {
		for (Parentesco p:listaparentescos) {
			parentescos.put(p.getNombreParentesco(), p);
		}
	}
	
	public Parentesco obtener(int id) {
		Parentesco parentesco = new Parentesco();
		
		for (Parentesco p:getList()) {
			if (p.getId() == id) {
				parentesco = p;
			}
		}
		
		return parentesco;
	}
	
}
