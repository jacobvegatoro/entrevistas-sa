package cl.puertoesperanza.entrevistassa.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cl.puertoesperanza.entrevistassa.modelo.Cargo;
import cl.puertoesperanza.entrevistassa.servicio.CargoService;
import cl.puertoesperanza.entrevistassa.servicio.EntrevistadoService;

@Controller
@RequestMapping("/cargos")
public class CargoController {

	@Autowired 
	private CargoService cargoServicio;

	@Autowired 
	private EntrevistadoService entrevistadoServicio;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping
	public ModelAndView listaCargos() 
	{
		ModelAndView modelAndView = new ModelAndView("cargos/listado");
		
		List<Cargo> listacargos = cargoServicio.obtenerCargos();
		
		modelAndView.addObject("listacargos", listacargos);

		return modelAndView;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/crear")
	public ModelAndView crearCargo() 
	{
		ModelAndView modelAndView = new ModelAndView("cargos/crear");

		return modelAndView;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/procesar")
	public ModelAndView procesarCargo(
			@RequestParam String txtNombre
			) 
	{
		ModelAndView modelAndView = new ModelAndView("cargos/crear");

		String mensaje = "";
		String tipoMensaje = "";
		
		Cargo cargo = new Cargo();
		
		cargo.setNombreCargo(txtNombre);
		
		if (cargoServicio.guardarCargo(cargo)) {
			mensaje = "El cargo ha sido almacenado exitosamente";
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
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/editar/{idcargo}")
	public ModelAndView editarCargo(@PathVariable Integer idcargo) 
	{
		ModelAndView modelAndView = new ModelAndView("cargos/editar");

		Cargo cargo = cargoServicio.obtenerCargoPorId(idcargo);
		
		modelAndView.addObject("cargo", cargo);
		
		return modelAndView;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/guardar")
	public ModelAndView guardarCargo(
			@RequestParam String txtNombre,
			@RequestParam String hdnIdCargo
			) 
	{
		ModelAndView modelAndView = new ModelAndView("cargos/editar");

		String mensaje = "";
		String tipoMensaje = "";
		
		Cargo cargo = new Cargo();
		cargo = cargoServicio.obtenerCargoPorId(Integer.parseInt(hdnIdCargo));
		
		cargo.setNombreCargo(txtNombre);
				
		if (cargoServicio.guardarCargo(cargo)) {
			mensaje = "El cargo ha sido modificado exitosamente";
			tipoMensaje = "Ok";
		}
		else {
			mensaje = "Se produjo un error al actualizar el registro";
			tipoMensaje = "Error";
		}
		
		modelAndView.addObject("cargo",cargo);
		modelAndView.addObject("mensaje",mensaje);
		modelAndView.addObject("tipoMensaje",tipoMensaje);
		
		return modelAndView;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/eliminar")
	public ModelAndView eliminarCargo(
			@RequestParam Integer idCargo
			) {
		
		ModelAndView modelAndView = new ModelAndView("cargos/listado");

		String mensaje = "";
		String tipoMensaje = "";

		Cargo cargo = new Cargo();
		cargo = cargoServicio.obtenerCargoPorId(idCargo);

		if (entrevistadoServicio.obtenerEntrevistadosPorCargo(cargo).size() == 0) {
			if (cargoServicio.eliminarCargo(idCargo)) {
				mensaje = "El cargo se ha eliminado exitosamente.";
				tipoMensaje = "Ok";
			}
			else {
				mensaje = "Ocurrió un error al eliminar el cargo.";
				tipoMensaje = "Error";
			}
		}
		else {
			mensaje = "Existen entrevistas asociadas al cargo, no se puede eliminar.";
			tipoMensaje = "Error";
		}

		List<Cargo> listacargos = cargoServicio.obtenerCargos();
		
		modelAndView.addObject("listacargos", listacargos);
		modelAndView.addObject("mensaje",mensaje);
		modelAndView.addObject("tipoMensaje",tipoMensaje);
		
		return modelAndView;

	}
	
}
