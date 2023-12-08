package cl.puertoesperanza.entrevistassa.controlador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cl.puertoesperanza.entrevistassa.modelo.User;
import cl.puertoesperanza.entrevistassa.servicio.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public ModelAndView listaUsuarios(@RequestParam(defaultValue = "1") Integer p) 
	{
		ModelAndView modelAndView = new ModelAndView("usuarios/listado");

		String mensaje = "";
		String tipoMensaje = "";

		//long cantTotal = usuarioServicio.obtenerUsuarios().size();
		//modelAndView.addObject("paginas", Util.getArregloPaginas(p, (int) usuarioServicio.getPageCount(cantTotal, this.paginacion)));
		//modelAndView.addObject("paginaActual",p);
		
		List<User> listadoUsuarios = new ArrayList<User>();
		listadoUsuarios = usuarioService.obtenerUsuarios();
		//listadoUsuarios = usuarioServicio.getPage(p-1, this.paginacion);

		modelAndView.addObject("mensaje",mensaje);
		modelAndView.addObject("tipoMensaje",tipoMensaje);

		modelAndView.addObject("usuarios",listadoUsuarios);
		return modelAndView;
	}
	
	@GetMapping("/crear")
	public ModelAndView crearUsuario() 
	{
		ModelAndView modelAndView = new ModelAndView("usuarios/crear");

		String mensaje = "";
		String tipoMensaje = "";

		modelAndView.addObject("mensaje", mensaje);
		modelAndView.addObject("tipoMensaje", tipoMensaje);
		
		return modelAndView;
	}

	@PostMapping("/procesa")
	public ModelAndView procesarUsuario(
			@RequestParam String txtNombreUsuario,
			@RequestParam String txtClave,
			@RequestParam Integer slcRol
			)
	{
		ModelAndView modelAndView = new ModelAndView("usuarios/crear");

		String mensaje = "";
		String tipoMensaje = "";
		
		User usuario = new User();
		usuario.setUsername(txtNombreUsuario);
		usuario.setEnabled(true);
		usuario.setPassword(txtClave);

		/*
		if (usuarioServicio.saveUser(usuario, slcRol)) {
			System.out.println("Registro agregado correctamente");
			mensaje = "El usuario ha sido agregado correctamente";
			tipoMensaje = "Ok";
		}
		else 
		{
			System.out.println("Error al agregar el usuario");
			mensaje = "Ocurrió un error al agregar el usuario";
			tipoMensaje = "Error";
		}*/
		
		modelAndView.addObject("mensaje", mensaje);
		modelAndView.addObject("tipoMensaje", tipoMensaje);

		return modelAndView;
	}
	
}
