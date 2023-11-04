package cl.puertoesperanza.entrevistassa.busqueda;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import cl.puertoesperanza.entrevistassa.modelo.Contactado;

public class ContactadoHashmap {
	private HashMap<String, Contactado> contactados = new HashMap<String, Contactado>();
	
	public ContactadoHashmap() {
		
	}
	
	public void add(Contactado contactado) {
		contactados.put(contactado.getEstadoContactado(), contactado);
	}

	public String toString() {
		String ret = "";
		for (Contactado contactado:contactados.values())
			ret += contactado.toString()+"\n";
		return ret;
	}
	
	public Contactado busca(String contactado) {
		return contactados.get(contactado);
	}
	
	public Collection<Contactado> getList(){
		return contactados.values();
	}
	
	public void llenar(List<Contactado> listacontactados) {
		for (Contactado c:listacontactados) {
			contactados.put(c.getEstadoContactado(), c);
		}
	}
	
	public Contactado obtener(int id) {
		Contactado contactado = new Contactado();
		
		for (Contactado c:getList()) {
			if (c.getId() == id) {
				contactado = c;
			}
		}
		
		return contactado;
	}
}
