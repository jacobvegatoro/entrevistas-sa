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

import cl.puertoesperanza.entrevistassa.modelo.Estado;
import cl.puertoesperanza.entrevistassa.servicio.EntrevistadoService;
import cl.puertoesperanza.entrevistassa.servicio.EstadoService;

@Controller
@RequestMapping("/estados")
public class EstadoController {

	@Autowired 
	private EstadoService estadoServicio;

	@Autowired 
	private EntrevistadoService entrevistadoServicio;

	@GetMapping
	public ModelAndView listaEstados() 
	{
		ModelAndView modelAndView = new ModelAndView("estados/listado");
		
		List<Estado> listaestados = estadoServicio.obtenerEstados();
		
		modelAndView.addObject("listaestados", listaestados);

		return modelAndView;
	}

	@GetMapping("/crear")
	public ModelAndView crearEstado() 
	{
		ModelAndView modelAndView = new ModelAndView("estados/crear");

		return modelAndView;
	}

	@PostMapping("/procesar")
	public ModelAndView procesarEstado(
			@RequestParam String txtNombre
			) 
	{
		ModelAndView modelAndView = new ModelAndView("estados/crear");

		String mensaje = "";
		String tipoMensaje = "";
		
		Estado estado = new Estado();
		
		estado.setDetalleEstado(txtNombre);
		
		if (estadoServicio.guardarEstado(estado)) {
			mensaje = "El estado ha sido almacenado exitosamente";
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
	
	@GetMapping("/editar/{idestado}")
	public ModelAndView editarEstado(@PathVariable Integer idestado) 
	{
		ModelAndView modelAndView = new ModelAndView("estados/editar");

		Estado estado = estadoServicio.obtenerEstadoPorId(idestado);
		
		modelAndView.addObject("estado", estado);
		
		return modelAndView;
	}

	@PostMapping("/guardar")
	public ModelAndView guardarEstado(
			@RequestParam String txtNombre,
			@RequestParam String hdnIdEstado
			) 
	{
		ModelAndView modelAndView = new ModelAndView("estados/editar");

		String mensaje = "";
		String tipoMensaje = "";
		
		Estado estado = new Estado();
		estado = estadoServicio.obtenerEstadoPorId(Integer.parseInt(hdnIdEstado));
		
		estado.setDetalleEstado(txtNombre);
				
		if (estadoServicio.guardarEstado(estado)) {
			mensaje = "El estado ha sido modificado exitosamente";
			tipoMensaje = "Ok";
		}
		else {
			mensaje = "Se produjo un error al actualizar el registro";
			tipoMensaje = "Error";
		}
		
		modelAndView.addObject("estado",estado);
		modelAndView.addObject("mensaje",mensaje);
		modelAndView.addObject("tipoMensaje",tipoMensaje);
		
		return modelAndView;
	}
	
	@PostMapping("/eliminar")
	public ModelAndView eliminarEstado(
			@RequestParam Integer idEstado
			) {
		
		ModelAndView modelAndView = new ModelAndView("estados/listado");

		String mensaje = "";
		String tipoMensaje = "";

		//Estado estado = new Estado();
		//estado = estadoServicio.obtenerEstadoPorId(idEstado);

		if (entrevistadoServicio.obtenerEntrevistadosPorEstado(idEstado).size() == 0) {
			if (estadoServicio.eliminarEstado(idEstado)) {
				mensaje = "El estado se ha eliminado exitosamente.";
				tipoMensaje = "Ok";
			}
			else {
				mensaje = "Ocurrió un error al eliminar el estado.";
				tipoMensaje = "Error";
			}
		}
		else {
			mensaje = "Existen entrevistas asociadas al estado, no se puede eliminar.";
			tipoMensaje = "Error";
		}

		List<Estado> listaestados = estadoServicio.obtenerEstados();
		
		modelAndView.addObject("listaestados", listaestados);
		modelAndView.addObject("mensaje",mensaje);
		modelAndView.addObject("tipoMensaje",tipoMensaje);
		
		return modelAndView;

	}
	
}
