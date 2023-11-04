package cl.puertoesperanza.entrevistassa.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.puertoesperanza.entrevistassa.modelo.Cliente;
import cl.puertoesperanza.entrevistassa.repositorio.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepositorio;
	
	@Override
	public List<Cliente> obtenerClientes() {
		return (List<Cliente>) clienteRepositorio.findAll();
	}

	@Override
	public Cliente obtenerClientePorId(Integer idCliente) {
		Optional<Cliente> cliente = clienteRepositorio.findById(idCliente);
		return cliente.get();
	}

	@Override
	public boolean guardarCliente(Cliente cliente) {
		
		boolean resultado = false;
		
		try {
			clienteRepositorio.save(cliente);
			resultado = true;
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return resultado;
	}

	@Override
	public boolean eliminarCliente(Integer idCliente) {
		boolean resultado = false;
		
		try {
			clienteRepositorio.deleteById(idCliente);
			resultado = true;
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return resultado;
	}

}
