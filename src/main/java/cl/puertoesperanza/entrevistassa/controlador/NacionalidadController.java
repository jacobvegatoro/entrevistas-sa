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

import cl.puertoesperanza.entrevistassa.modelo.Nacionalidad;
import cl.puertoesperanza.entrevistassa.servicio.EntrevistadoService;
import cl.puertoesperanza.entrevistassa.servicio.NacionalidadService;

@Controller
@RequestMapping("/nacionalidades")
public class NacionalidadController {

	@Autowired
	private NacionalidadService nacionalidadServicio;

	@Autowired
	private EntrevistadoService entrevistadoServicio;

	@GetMapping
	public ModelAndView listaNacionalidades() 
	{
		ModelAndView modelAndView = new ModelAndView("nacionalidades/listado");
		
		List<Nacionalidad> listanacionalidades = nacionalidadServicio.obtenerNacionalidades();
		
		modelAndView.addObject("listanacionalidades", listanacionalidades);

		return modelAndView;
	}

	@GetMapping("/crear")
	public ModelAndView crearNacionalidad() 
	{
		ModelAndView modelAndView = new ModelAndView("nacionalidades/crear");

		return modelAndView;
	}

	@PostMapping("/procesar")
	public ModelAndView procesarCargo(
			@RequestParam String txtNombre
			) 
	{
		ModelAndView modelAndView = new ModelAndView("nacionalidades/crear");

		String mensaje = "";
		String tipoMensaje = "";
		
		Nacionalidad nacionalidad = new Nacionalidad();
		
		nacionalidad.setDetalleNacionalidad(txtNombre);
		
		if (nacionalidadServicio.guardarNacionalidad(nacionalidad)) {
			mensaje = "La nacionalidad ha sido almacenada exitosamente";
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
	
	@GetMapping("/editar/{idnacionalidad}")
	public ModelAndView editarNacionalidad(@PathVariable Integer idnacionalidad) 
	{
		ModelAndView modelAndView = new ModelAndView("nacionalidades/editar");

		Nacionalidad nacionalidad = nacionalidadServicio.obtenerNacionalidadPorId(idnacionalidad);
		
		modelAndView.addObject("nacionalidad", nacionalidad);
		
		return modelAndView;
	}

	@PostMapping("/guardar")
	public ModelAndView guardarNacionalidad(
			@RequestParam String txtNombre,
			@RequestParam String hdnIdNacionalidad
			) 
	{
		ModelAndView modelAndView = new ModelAndView("nacionalidades/editar");

		String mensaje = "";
		String tipoMensaje = "";
		
		Nacionalidad nacionalidad = new Nacionalidad();
		nacionalidad = nacionalidadServicio.obtenerNacionalidadPorId(Integer.parseInt(hdnIdNacionalidad));
		
		nacionalidad.setDetalleNacionalidad(txtNombre);
				
		if (nacionalidadServicio.guardarNacionalidad(nacionalidad)) {
			mensaje = "La nacionalidad ha sido modificada exitosamente";
			tipoMensaje = "Ok";
		}
		else {
			mensaje = "Se produjo un error al actualizar el registro";
			tipoMensaje = "Error";
		}
		
		modelAndView.addObject("nacionalidad",nacionalidad);
		modelAndView.addObject("mensaje",mensaje);
		modelAndView.addObject("tipoMensaje",tipoMensaje);
		
		return modelAndView;
	}

	@PostMapping("/eliminar")
	public ModelAndView eliminarNacionalidad(
			@RequestParam Integer idNacionalidad
			) {
		
		ModelAndView modelAndView = new ModelAndView("nacionalidades/listado");

		String mensaje = "";
		String tipoMensaje = "";

		//Nacionalidad nacionalidad = new Nacionalidad();
		//nacionalidad = nacionalidadServicio.obtenerNacionalidadPorId(idNacionalidad);

		if (entrevistadoServicio.obtenerEntrevistadosPorNacionalidad(idNacionalidad).size() == 0) {
			if (nacionalidadServicio.eliminarNacionalidad(idNacionalidad)) {
				mensaje = "La nacionalidad se ha eliminado exitosamente.";
				tipoMensaje = "Ok";
			}
			else {
				mensaje = "Ocurrió un error al eliminar la nacionalidad.";
				tipoMensaje = "Error";
			}
		}
		else {
			mensaje = "Existen entrevistas asociadas a la nacionalidad, no se puede eliminar.";
			tipoMensaje = "Error";
		}

		List<Nacionalidad> listanacionalidades = nacionalidadServicio.obtenerNacionalidades();
		
		modelAndView.addObject("listanacionalidades", listanacionalidades);
		modelAndView.addObject("mensaje",mensaje);
		modelAndView.addObject("tipoMensaje",tipoMensaje);
		
		return modelAndView;

	}
}
