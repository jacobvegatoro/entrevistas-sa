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
import cl.puertoesperanza.entrevistassa.modelo.Reclutador;
import cl.puertoesperanza.entrevistassa.modelo.Region;
import cl.puertoesperanza.entrevistassa.servicio.ComunaService;
import cl.puertoesperanza.entrevistassa.servicio.EntrevistadoService;
import cl.puertoesperanza.entrevistassa.servicio.ReclutadorService;
import cl.puertoesperanza.entrevistassa.servicio.RegionService;

@Controller
@RequestMapping("/reclutadores")
public class ReclutadorController {

	@Autowired
	private ReclutadorService reclutadorServicio;

	@Autowired
	private RegionService regionServicio;

	@Autowired
	private ComunaService comunaServicio;

	@Autowired
	private EntrevistadoService entrevistadoServicio;

	@GetMapping
	public ModelAndView listaReclutadores() 
	{
		ModelAndView modelAndView = new ModelAndView("reclutadores/listado");

		List<Reclutador> listareclutadores = reclutadorServicio.obtenerReclutadores();
		
		modelAndView.addObject("listareclutadores", listareclutadores);

		return modelAndView;
	}

	@GetMapping("/crear")
	public ModelAndView crearReclutador() 
	{
		ModelAndView modelAndView = new ModelAndView("reclutadores/crear");

		modelAndView.addObject("regiones",regionServicio.obtenerRegiones());
		modelAndView.addObject("idregionselec",0);

		return modelAndView;
	}

	@PostMapping("/procesar")
	public ModelAndView procesarReclutador(
			@RequestParam String slcRegion,	
			@RequestParam String slcComuna,
			@RequestParam String txtNombre) 
	{
		ModelAndView modelAndView = new ModelAndView("reclutadores/crear");

		modelAndView.addObject("regiones",regionServicio.obtenerRegiones());
		modelAndView.addObject("idregionselec",0);

		String mensaje = "";
		String tipoMensaje = "";
		
		Reclutador reclutador = new Reclutador();
		
		reclutador.setNombreReclutador(txtNombre);
		
		Comuna comuna = comunaServicio.obtenerComunaPorId(Integer.parseInt(slcComuna));
		reclutador.setComuna(comuna);
		
		if (reclutadorServicio.guardarReclutador(reclutador)) {
			mensaje = "El reclutador ha sido almacenado exitosamente";
			tipoMensaje = "Ok";
		}
		else {
			mensaje = "Se produjo un error al almacenar el reclutador";
			tipoMensaje = "Error";
		}
		
		modelAndView.addObject("mensaje",mensaje);
		modelAndView.addObject("tipoMensaje",tipoMensaje);
		
		return modelAndView;
	}
	
	@GetMapping("/editar/{idreclutador}")
	public ModelAndView editarReclutador(@PathVariable Integer idreclutador) 
	{
		ModelAndView modelAndView = new ModelAndView("reclutadores/editar");

		modelAndView.addObject("regiones",regionServicio.obtenerRegiones());

		Reclutador reclutador = reclutadorServicio.obtenerReclutadorPorId(idreclutador);
		Region reg = regionServicio.obtenerRegionPorId(reclutador.getComuna().getRegion().getId());
		
		modelAndView.addObject("listacomunas",comunaServicio.obtenerComunasPorRegion(reg));
		modelAndView.addObject("reclutador", reclutador);

		return modelAndView;
	}
	
	@PostMapping("/guardar")
	public ModelAndView guardarReclutador(
			@RequestParam String slcRegion,	
			@RequestParam String slcComuna,
			@RequestParam String txtNombre,
			@RequestParam String hdnIdReclutador
			) 
	{
		ModelAndView modelAndView = new ModelAndView("reclutadores/editar");

		String mensaje = "";
		String tipoMensaje = "";
		
		Reclutador reclutador = new Reclutador();
		reclutador = reclutadorServicio.obtenerReclutadorPorId(Integer.parseInt(hdnIdReclutador));
		
		reclutador.setNombreReclutador(txtNombre);
		//cliente.setId(Integer.parseInt(hdnIdCliente));
		
		Comuna comuna = comunaServicio.obtenerComunaPorId(Integer.parseInt(slcComuna));
		reclutador.setComuna(comuna);
		
		if (reclutadorServicio.guardarReclutador(reclutador)) {
			mensaje = "El reclutador ha sido modificado exitosamente";
			tipoMensaje = "Ok";
		}
		else {
			mensaje = "Se produjo un error al actualizar el registro";
			tipoMensaje = "Error";
		}
		
		Region reg = reclutador.getComuna().getRegion();

		modelAndView.addObject("regiones",regionServicio.obtenerRegiones());
		modelAndView.addObject("listacomunas",comunaServicio.obtenerComunasPorRegion(reg));
		modelAndView.addObject("reclutador",reclutador);
		modelAndView.addObject("mensaje",mensaje);
		modelAndView.addObject("tipoMensaje",tipoMensaje);
		
		return modelAndView;
	}

	@PostMapping("/eliminar")
	public ModelAndView eliminarReclutador(
			@RequestParam Integer idReclutador
			) {
		
		ModelAndView modelAndView = new ModelAndView("reclutadores/listado");

		String mensaje = "";
		String tipoMensaje = "";

		Reclutador reclutador = new Reclutador();
		reclutador = reclutadorServicio.obtenerReclutadorPorId(idReclutador);
				
		if (entrevistadoServicio.obtenerEntrevistadosPorReclutador(reclutador).size() == 0) {
			if (reclutadorServicio.eliminarReclutador(idReclutador)) {
				mensaje = "El reclutador se ha eliminado exitosamente.";
				tipoMensaje = "Ok";
			}
			else {
				mensaje = "Ocurrió un error al eliminar el reclutador.";
				tipoMensaje = "Error";
			}
		}
		else {
			mensaje = "Existen entrevistas asociadas al reclutador, no se puede eliminar.";
			tipoMensaje = "Error";
		}

		List<Reclutador> listareclutador = reclutadorServicio.obtenerReclutadores();
		
		modelAndView.addObject("listareclutadores", listareclutador);
		modelAndView.addObject("mensaje",mensaje);
		modelAndView.addObject("tipoMensaje",tipoMensaje);
		
		return modelAndView;
	}
	
}
