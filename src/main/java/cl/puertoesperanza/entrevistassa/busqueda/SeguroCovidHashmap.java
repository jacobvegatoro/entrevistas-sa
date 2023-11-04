package cl.puertoesperanza.entrevistassa.busqueda;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import cl.puertoesperanza.entrevistassa.modelo.SeguroCovid;

public class SeguroCovidHashmap {

	private HashMap<String, SeguroCovid> seguroscovid = new HashMap<String, SeguroCovid>();
	
	public SeguroCovidHashmap() {
		
	}
	
	public void add(SeguroCovid segurocovid) {
		seguroscovid.put(segurocovid.getEstadoSeguro(), segurocovid);
	}

	public String toString() {
		String ret = "";
		for (SeguroCovid segurocovid:seguroscovid.values())
			ret += segurocovid.toString()+"\n";
		return ret;
	}
	
	public SeguroCovid busca(String segurocovid) {
		return seguroscovid.get(segurocovid);
	}
	
	public Collection<SeguroCovid> getList(){
		return seguroscovid.values();
	}
	
	public void llenar(List<SeguroCovid> listaseguroscovid) {
		for (SeguroCovid sc:listaseguroscovid) {
			seguroscovid.put(sc.getEstadoSeguro(), sc);
		}
	}
	
	public SeguroCovid obtener(int id) {
		SeguroCovid segurocovid = new SeguroCovid();
		
		for (SeguroCovid sc:getList()) {
			if (sc.getId() == id) {
				segurocovid = sc;
			}
		}
		
		return segurocovid;
	}
	
}
