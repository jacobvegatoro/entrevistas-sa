package cl.puertoesperanza.entrevistassa.busqueda;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import cl.puertoesperanza.entrevistassa.modelo.Banco;

public class BancoHashmap {

	private HashMap<String, Banco> bancos = new HashMap<String, Banco>();
	
	public BancoHashmap() {
		
	}
	
	public void add(Banco banco) {
		bancos.put(banco.getNombreBanco(), banco);
	}

	public String toString() {
		String ret = "";
		for (Banco banco:bancos.values())
			ret += banco.toString()+"\n";
		return ret;
	}
	
	public Banco busca(String banco) {
		return bancos.get(banco);
	}
	
	public Collection<Banco> getList(){
		return bancos.values();
	}
	
	public void llenar(List<Banco> listabancos) {
		for (Banco b:listabancos) {
			bancos.put(b.getNombreBanco(), b);
		}
	}
	
	public Banco obtener(int id) {
		Banco banco = new Banco();
		
		for (Banco b:getList()) {
			if (b.getId() == id) {
				banco = b;
			}
		}
		
		return banco;
	}
	
}
