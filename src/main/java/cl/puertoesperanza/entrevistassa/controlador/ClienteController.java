package cl.puertoesperanza.entrevistassa.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cl.puertoesperanza.entrevistassa.modelo.Cliente;
import cl.puertoesperanza.entrevistassa.modelo.Comuna;
import cl.puertoesperanza.entrevistassa.modelo.Region;
import cl.puertoesperanza.entrevistassa.servicio.ClienteService;
import cl.puertoesperanza.entrevistassa.servicio.ComunaService;
import cl.puertoesperanza.entrevistassa.servicio.EntrevistadoService;
import cl.puertoesperanza.entrevistassa.servicio.RegionService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteServicio;

	@Autowired
	private RegionService regionServicio;

	@Autowired
	private ComunaService comunaServicio;
	
	@Autowired
	private EntrevistadoService entrevistadoServicio;
	
	@GetMapping
	public ModelAndView listaClientes() 
	{
		ModelAndView modelAndView = new ModelAndView("clientes/listado");
		
		List<Cliente> listaclientes = clienteServicio.obtenerClientes();
		
		modelAndView.addObject("listaclientes", listaclientes);
		
		return modelAndView;
	}

	@GetMapping("/crear")
	public ModelAndView crearCliente() 
	{
		ModelAndView modelAndView = new ModelAndView("clientes/crear");

		modelAndView.addObject("regiones",regionServicio.obtenerRegiones());
		modelAndView.addObject("idregionselec",0);

		return modelAndView;
	}

	@PostMapping("/procesar")
	public ModelAndView procesarCliente(
			@RequestParam String slcRegion,	
			@RequestParam String slcComuna,
			@RequestParam String txtNombre,
			@RequestParam String txtRut) 
	{
		ModelAndView modelAndView = new ModelAndView("clientes/crear");

		modelAndView.addObject("regiones",regionServicio.obtenerRegiones());
		modelAndView.addObject("idregionselec",0);

		String mensaje = "";
		String tipoMensaje = "";
		
		Cliente cliente = new Cliente();
		
		cliente.setNombreCliente(txtNombre);
		cliente.setRutCliente(txtRut);
		
		Comuna comuna = comunaServicio.obtenerComunaPorId(Integer.parseInt(slcComuna));
		cliente.setComuna(comuna);
		
		if (clienteServicio.guardarCliente(cliente)) {
			mensaje = "El cliente ha sido almacenado exitosamente";
			tipoMensaje = "Ok";
		}
		else {
			mensaje = "Se produjo un error al almacenar el registro";
			tipoMensaje = "Error";
		}
		
		modelAndView.addObject("mensaje",mensaje);
		modelAndView.addObject("tipoMensaje",tipoMensaje);
		
		return modelAndView;
	}

	@GetMapping("/editar/{idcliente}")
	public ModelAndView editarCliente(@PathVariable Integer idcliente) 
	{
		ModelAndView modelAndView = new ModelAndView("clientes/editar");
		modelAndView.addObject("regiones",regionServicio.obtenerRegiones());

		Cliente cliente = clienteServicio.obtenerClientePorId(idcliente);
		Region reg = regionServicio.obtenerRegionPorId(cliente.getComuna().getRegion().getId());
		
		modelAndView.addObject("listacomunas",comunaServicio.obtenerComunasPorRegion(reg));
		modelAndView.addObject("cliente", cliente);
		
		return modelAndView;
	}

	@PostMapping("/guardar")
	public ModelAndView guardarCliente(
			@RequestParam String slcRegion,	
			@RequestParam String slcComuna,
			@RequestParam String txtNombre,
			@RequestParam String txtRut,
			@RequestParam String hdnIdCliente
			) 
	{
		ModelAndView modelAndView = new ModelAndView("clientes/editar");

		String mensaje = "";
		String tipoMensaje = "";
		
		Cliente cliente = new Cliente();
		cliente = clienteServicio.obtenerClientePorId(Integer.parseInt(hdnIdCliente));
		
		cliente.setNombreCliente(txtNombre);
		cliente.setRutCliente(txtRut);
		
		Comuna comuna = comunaServicio.obtenerComunaPorId(Integer.parseInt(slcComuna));
		cliente.setComuna(comuna);
		
		if (clienteServicio.guardarCliente(cliente)) {
			mensaje = "El cliente ha sido modificado exitosamente";
			tipoMensaje = "Ok";
		}
		else {
			mensaje = "Se produjo un error al actualizar el registro";
			tipoMensaje = "Error";
		}
		
		Region reg = cliente.getComuna().getRegion();
		
		modelAndView.addObject("regiones",regionServicio.obtenerRegiones());
		modelAndView.addObject("listacomunas",comunaServicio.obtenerComunasPorRegion(reg));
		modelAndView.addObject("cliente",cliente);
		modelAndView.addObject("mensaje",mensaje);
		modelAndView.addObject("tipoMensaje",tipoMensaje);
		
		return modelAndView;
	}
	
	@PostMapping("/eliminar")
	public ModelAndView eliminarCliente(
			@RequestParam Integer idCliente
			) {
		
		ModelAndView modelAndView = new ModelAndView("clientes/listado");

		String mensaje = "";
		String tipoMensaje = "";

		Cliente cliente = new Cliente();
		cliente = clienteServicio.obtenerClientePorId(idCliente);
				
		if (entrevistadoServicio.obtenerEntrevistadosPorCliente(cliente).size() == 0) {
			if (clienteServicio.eliminarCliente(idCliente)) {
				mensaje = "El cliente se ha eliminado exitosamente.";
				tipoMensaje = "Ok";
			}
			else {
				mensaje = "Ocurrió un error al eliminar el cliente.";
				tipoMensaje = "Error";
			}
		}
		else {
			mensaje = "Existen entrevistas asociadas al cliente, no se puede eliminar.";
			tipoMensaje = "Error";
		}

		List<Cliente> listaclientes = clienteServicio.obtenerClientes();
		
		modelAndView.addObject("listaclientes", listaclientes);
		modelAndView.addObject("mensaje",mensaje);
		modelAndView.addObject("tipoMensaje",tipoMensaje);
		
		return modelAndView;

	}
}
