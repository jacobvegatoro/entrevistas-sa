package cl.puertoesperanza.entrevistassa.busqueda;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import cl.puertoesperanza.entrevistassa.modelo.TipoCuenta;

public class TipoCuentaHashmap {
	
	private HashMap<String, TipoCuenta> tiposcuenta = new HashMap<String, TipoCuenta>();
	
	public TipoCuentaHashmap() {
		
	}
	
	public void add(TipoCuenta tipocuenta) {
		tiposcuenta.put(tipocuenta.getDetalleTipo(), tipocuenta);
	}

	public String toString() {
		String ret = "";
		for (TipoCuenta tipocuenta:tiposcuenta.values())
			ret += tipocuenta.toString()+"\n";
		return ret;
	}
	
	public TipoCuenta busca(String tipocuenta) {
		return tiposcuenta.get(tipocuenta);
	}
	
	public Collection<TipoCuenta> getList(){
		return tiposcuenta.values();
	}
	
	public void llenar(List<TipoCuenta> listatiposcuenta) {
		for (TipoCuenta tc:listatiposcuenta) {
			tiposcuenta.put(tc.getDetalleTipo(), tc);
		}
	}
	
	public TipoCuenta obtener(int id) {
		TipoCuenta tipocuenta = new TipoCuenta();
		
		for (TipoCuenta tc:getList()) {
			if (tc.getId() == id) {
				tipocuenta = tc;
			}
		}
		
		return tipocuenta;
	}
	
}
