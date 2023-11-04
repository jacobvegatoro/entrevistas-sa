package cl.puertoesperanza.entrevistassa.servicio;

import java.util.List;

import cl.puertoesperanza.entrevistassa.modelo.Cliente;

public interface ClienteService {

	List<Cliente> obtenerClientes();
	Cliente obtenerClientePorId(Integer idCliente);
	boolean guardarCliente(Cliente cliente);
	boolean eliminarCliente(Integer idCliente);
	
}
