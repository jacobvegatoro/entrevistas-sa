package cl.puertoesperanza.entrevistassa.busqueda;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import cl.puertoesperanza.entrevistassa.modelo.Cliente;

public class ClienteHashmap {

	private HashMap<String, Cliente> clientes = new HashMap<String, Cliente>();
	
	public ClienteHashmap() {
		
	}
	
	public void add(Cliente cliente) {
		clientes.put(cliente.getNombreCliente(), cliente);
	}

	public String toString() {
		String ret = "";
		for (Cliente cliente:clientes.values())
			ret += cliente.toString()+"\n";
		return ret;
	}
	
	public Cliente busca(String nombrecliente) {
		return clientes.get(nombrecliente);
	}
	
	public Collection<Cliente> getList(){
		return clientes.values();
	}
	
	public void llenar(List<Cliente> listaclientes) {
		for (Cliente c:listaclientes) {
			clientes.put(c.getNombreCliente(), c);
		}
	}
	
	public Cliente obtener(int id) {
		Cliente cliente = new Cliente();
		
		for (Cliente c:getList()) {
			if (c.getId() == id) {
				cliente = c;
			}
		}
		
		return cliente;
	}
	
}
