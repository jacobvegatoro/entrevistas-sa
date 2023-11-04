package cl.puertoesperanza.entrevistassa.busqueda;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import cl.puertoesperanza.entrevistassa.modelo.Periodo;

public class PeriodoHashmap {

	private HashMap<String, Periodo> periodos = new HashMap<String, Periodo>();
	
	public PeriodoHashmap() {
		
	}
	
	public void add(Periodo periodo) {
		periodos.put(periodo.getDetallePeriodo().trim(), periodo);
	}

	public String toString() {
		String ret = "";
		for (Periodo periodo:periodos.values())
			ret += periodo.toString()+"\n";
		return ret;
	}
	
	public Periodo busca(String periodo) {
		return periodos.get(periodo);
	}
	
	public Collection<Periodo> getList(){
		return periodos.values();
	}
	
	public void llenar(List<Periodo> listaperiodos) {
		for (Periodo p:listaperiodos) {
			periodos.put(p.getDetallePeriodo().trim(), p);
		}
	}
	
	public Periodo obtener(int id) {
		Periodo periodo = new Periodo();
		
		for (Periodo p:getList()) {
			if (p.getId() == id) {
				periodo = p;
			}
		}
		
		return periodo;
	}
	
}
