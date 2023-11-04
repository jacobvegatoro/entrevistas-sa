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

import cl.puertoesperanza.entrevistassa.modelo.Comuna;
import cl.puertoesperanza.entrevistassa.modelo.Instalacion;
import cl.puertoesperanza.entrevistassa.modelo.Region;
import cl.puertoesperanza.entrevistassa.servicio.ComunaService;
import cl.puertoesperanza.entrevistassa.servicio.EntrevistadoService;
import cl.puertoesperanza.entrevistassa.servicio.InstalacionService;
import cl.puertoesperanza.entrevistassa.servicio.RegionService;

@Controller
@RequestMapping("/instalaciones")
public class InstalacionController {

	@Autowired
	private RegionService regionServicio;

	@Autowired
	private ComunaService comunaServicio;

	@Autowired
	private InstalacionService instalacionServicio;

	@Autowired
	private EntrevistadoService entrevistadoServicio;

	@GetMapping
	public ModelAndView listaInstalaciones() 
	{
		ModelAndView modelAndView = new ModelAndView("instalaciones/listado");

		List<Instalacion> listainstalaciones = instalacionServicio.obtenerInstalaciones();
		
		modelAndView.addObject("listainstalaciones", listainstalaciones);
		
		return modelAndView;
	}

	@GetMapping("/crear")
	public ModelAndView crearInstalacion() 
	{
		ModelAndView modelAndView = new ModelAndView("instalaciones/crear");

		modelAndView.addObject("regiones",regionServicio.obtenerRegiones());
		modelAndView.addObject("idregionselec",0);

		return modelAndView;
	}

	@PostMapping("/procesar")
	public ModelAndView procesarCliente(
			@RequestParam String slcRegion,	
			@RequestParam String slcComuna,
			@RequestParam String txtNombre,
			@RequestParam String txtRun) 
	{
		ModelAndView modelAndView = new ModelAndView("instalaciones/crear");

		modelAndView.addObject("regiones",regionServicio.obtenerRegiones());
		modelAndView.addObject("idregionselec",0);

		String mensaje = "";
		String tipoMensaje = "";
		
		Instalacion instalacion = new Instalacion();
		
		instalacion.setNombreInstalacion(txtNombre);
		instalacion.setRutInstalacion(txtRun);
		
		Comuna comuna = comunaServicio.obtenerComunaPorId(Integer.parseInt(slcComuna));
		instalacion.setComuna(comuna);
		
		if (instalacionServicio.guardarInstalacion(instalacion)) {
			mensaje = "La instalacion ha sido almacenada exitosamente";
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
	
	@GetMapping("/editar/{idinstalacion}")
	public ModelAndView editarInstalacion(@PathVariable Integer idinstalacion) 
	{
		ModelAndView modelAndView = new ModelAndView("instalaciones/editar");
		modelAndView.addObject("regiones",regionServicio.obtenerRegiones());

		Instalacion instalacion = instalacionServicio.obtenerInstalacionPorId(idinstalacion);
		Region reg = instalacion.getComuna().getRegion();
		
		modelAndView.addObject("listacomunas",comunaServicio.obtenerComunasPorRegion(reg));
		modelAndView.addObject("instalacion", instalacion);
		
		return modelAndView;
	}

	@PostMapping("/guardar")
	public ModelAndView guardarCliente(
			@RequestParam String slcRegion,	
			@RequestParam String slcComuna,
			@RequestParam String txtNombre,
			@RequestParam String txtRun,
			@RequestParam String hdnIdInstalacion
			) 
	{
		ModelAndView modelAndView = new ModelAndView("instalaciones/editar");

		String mensaje = "";
		String tipoMensaje = "";
		
		Instalacion instalacion = new Instalacion();
		instalacion = instalacionServicio.obtenerInstalacionPorId(Integer.parseInt(hdnIdInstalacion));
		
		instalacion.setNombreInstalacion(txtNombre);
		instalacion.setRutInstalacion(txtRun);
		
		Comuna comuna = comunaServicio.obtenerComunaPorId(Integer.parseInt(slcComuna));
		instalacion.setComuna(comuna);
		
		if (instalacionServicio.guardarInstalacion(instalacion)) {
			mensaje = "La instalación ha sido modificada exitosamente";
			tipoMensaje = "Ok";
		}
		else {
			mensaje = "Se produjo un error al actualizar el registro";
			tipoMensaje = "Error";
		}
		
		Region reg = instalacion.getComuna().getRegion();
		
		modelAndView.addObject("regiones",regionServicio.obtenerRegiones());
		modelAndView.addObject("listacomunas",comunaServicio.obtenerComunasPorRegion(reg));
		modelAndView.addObject("instalacion",instalacion);
		modelAndView.addObject("mensaje",mensaje);
		modelAndView.addObject("tipoMensaje",tipoMensaje);
		
		return modelAndView;
	}
	
	@PostMapping("/eliminar")
	public ModelAndView eliminarInstalacion(
			@RequestParam Integer idInstalacion
			) {
		
		ModelAndView modelAndView = new ModelAndView("instalaciones/listado");

		String mensaje = "";
		String tipoMensaje = "";

		Instalacion instalacion = new Instalacion();
		instalacion = instalacionServicio.obtenerInstalacionPorId(idInstalacion);
				
		if (entrevistadoServicio.obtenerEntrevistadosPorInstalacion(instalacion).size() == 0) {
			if (instalacionServicio.eliminarInstalacion(idInstalacion)) {
				mensaje = "La instalacion se ha eliminado exitosamente.";
				tipoMensaje = "Ok";
			}
			else {
				mensaje = "Ocurrió un error al eliminar la instalacion.";
				tipoMensaje = "Error";
			}
		}
		else {
			mensaje = "Existen entrevistas asociadas a la instalacion, no se puede eliminar.";
			tipoMensaje = "Error";
		}

		List<Instalacion> listainstalaciones = instalacionServicio.obtenerInstalaciones();
		
		modelAndView.addObject("listainstalaciones", listainstalaciones);
		modelAndView.addObject("mensaje",mensaje);
		modelAndView.addObject("tipoMensaje",tipoMensaje);
		
		return modelAndView;
	}
	
}
