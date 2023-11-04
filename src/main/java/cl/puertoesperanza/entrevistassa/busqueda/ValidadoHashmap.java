package cl.puertoesperanza.entrevistassa.busqueda;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import cl.puertoesperanza.entrevistassa.modelo.Validado;

public class ValidadoHashmap {

	private HashMap<String, Validado> validados = new HashMap<String, Validado>();
	
	public ValidadoHashmap() {
		
	}
	
	public void add(Validado valido) {
		validados.put(valido.getEstadoValidado(), valido);
	}

	public String toString() {
		String ret = "";
		for (Validado valido:validados.values())
			ret += valido.toString()+"\n";
		return ret;
	}
	
	public Validado busca(String valido) {
		return validados.get(valido);
	}
	
	public Collection<Validado> getList(){
		return validados.values();
	}
	
	public void llenar(List<Validado> listavalidados) {
		for (Validado v:listavalidados) {
			validados.put(v.getEstadoValidado(), v);
		}
	}
	
	public Validado obtener(int id) {
		Validado valido = new Validado();
		
		for (Validado v:getList()) {
			if (v.getId() == id) {
				valido = v;
			}
		}
		
		return valido;
	}
}
