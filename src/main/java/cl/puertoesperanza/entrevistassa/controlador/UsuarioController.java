package cl.puertoesperanza.entrevistassa.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cl.puertoesperanza.entrevistassa.modelo.User;
import cl.puertoesperanza.entrevistassa.modelo.UserRole;
import cl.puertoesperanza.entrevistassa.servicio.UsuarioRolService;
import cl.puertoesperanza.entrevistassa.servicio.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioRolService usuarioRolService;

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

		if (usuarioService.obtenerUsuarioPorNombre(txtNombreUsuario) == null) {
			
			BCryptPasswordEncoder pe = new BCryptPasswordEncoder();

			User usuario = new User();
			usuario.setUsername(txtNombreUsuario);
			usuario.setEnabled(true);
			usuario.setPassword(pe.encode(txtClave));

			User newUser = usuarioService.crearUsuario(usuario);
			if (newUser != null) {
				System.out.println("Registro agregado correctamente");
				
				String rol = "";
				if (slcRol == 1) {
					rol = "ROLE_ADMIN";
				}
				else if (slcRol == 2) {
					rol = "ROLE_USER";
				}
				
				UserRole usuarioRol = new UserRole(newUser, rol);
				
				if (usuarioRolService.crearUsuarioRol(usuarioRol)) {
					mensaje = "El usuario ha sido agregado correctamente";
					tipoMensaje = "Ok";
				}
				else {
					mensaje = "El usuario se agregó en sistema, pero no su rol";
					tipoMensaje = "Error";
				}
				
			}
			else 
			{
				System.out.println("Error al agregar el usuario");
				mensaje = "Ocurrió un error al agregar el usuario";
				tipoMensaje = "Error";
			}			
		}
		else {
			System.out.println("Error al agregar el usuario - Usuario ya existe");
			mensaje = "El usuario ya existe en plataforma, no se puede volver a crear";
			tipoMensaje = "Error";
		}
		
		modelAndView.addObject("mensaje", mensaje);
		modelAndView.addObject("tipoMensaje", tipoMensaje);

		return modelAndView;
	}

	
	@GetMapping("/editar/{username}")
	public ModelAndView editarUsuario(@PathVariable String username) 
	{
		ModelAndView modelAndView = new ModelAndView("usuarios/editar");

		String mensaje = "";
		String tipoMensaje = "";
		String rol = "";
		
		User usuario = usuarioService.obtenerUsuarioPorNombre(username);
		
		if (usuario == null) {
			System.out.println("Error al editar el usuario - Usuario no existe");
			mensaje = "El usuario no existe en plataforma, no se puede realizar la operación";
			tipoMensaje = "Error";
		}
		else {
			Set<UserRole> roles = usuario.getUserRole();
			for (UserRole r:roles) {
				rol = r.getRole();
			}
		}
		
		modelAndView.addObject("usuario", usuario);
		modelAndView.addObject("rol", rol);
		modelAndView.addObject("mensaje", mensaje);
		modelAndView.addObject("tipoMensaje", tipoMensaje);
		
		return modelAndView;
	}
	

	@PostMapping("/clave")
	public ModelAndView cambiarClave(
			@RequestParam String txtClave,
			@RequestParam String txtClaveDos,
			@RequestParam String hdnId
			)
	{
		ModelAndView modelAndView = new ModelAndView("usuarios/editar");

		String mensaje = "";
		String tipoMensaje = "";
		String rol = "";

		User usuario = usuarioService.obtenerUsuarioPorNombre(hdnId);
		
		if (usuario != null) {
			
			BCryptPasswordEncoder pe = new BCryptPasswordEncoder();

			usuario.setPassword(pe.encode(txtClave));

			User newUser = usuarioService.crearUsuario(usuario);
			if (newUser != null) {
				System.out.println("Usuario editado correctamente");
				mensaje = "El usuario ha sido editado correctamente";
				tipoMensaje = "Ok";
			}
			else 
			{
				System.out.println("Error al editar el usuario");
				mensaje = "Ocurrió un error al editar el usuario";
				tipoMensaje = "Error";
			}
			
			modelAndView.addObject("usuario", newUser);
			Set<UserRole> rolesUpdated = newUser.getUserRole();
			for (UserRole r:rolesUpdated) {
				rol = r.getRole();
			}
		}
		else {
			System.out.println("Error al editar el usuario - Usuario no existe");
			mensaje = "El usuario no existe en plataforma, no se puede editar";
			tipoMensaje = "Error";
			modelAndView.addObject("usuario", usuario);
		}
		
		modelAndView.addObject("rol", rol);
		modelAndView.addObject("mensaje", mensaje);
		modelAndView.addObject("tipoMensaje", tipoMensaje);

		return modelAndView;
	}


	@PostMapping("/guardar")
	public ModelAndView datosUsuario(
			@RequestParam String slcRol,
			@RequestParam String slcActivo,
			@RequestParam String txtUsername
			)
	{
		ModelAndView modelAndView = new ModelAndView("usuarios/editar");

		String mensaje = "";
		String tipoMensaje = "";
		String rol = "";

		User usuario = usuarioService.obtenerUsuarioPorNombre(txtUsername);
		
		if (usuario != null) {
			
			if (slcActivo.equals("1") ) {
				usuario.setEnabled(true);
			}
			else if (slcActivo.equals("2")) {
				usuario.setEnabled(false);
			}
				
			User newUser = usuarioService.crearUsuario(usuario);
			if (newUser != null) {
				
				if (slcRol.equals("1")) {
					rol = "ROLE_ADMIN";
				}
				else if (slcRol.equals("2")) {
					rol = "ROLE_USER";
				}
				
				System.out.println("Cantidad de roles: " + newUser.getUserRole().size());
				System.out.println("Nuevo rol: " + rol);
				
				Set<UserRole> roles = newUser.getUserRole();
				for (UserRole r:roles) {
					System.out.println("ID: " + r.getUserRoleId() + ", nombre: " + r.getRole());
				}
				
				if (roles.size() == 1) {
					List<UserRole> rolesList = new ArrayList<>(roles);
					UserRole newRol = rolesList.get(0);
					newRol.setRole(rol);
					
					if (usuarioRolService.crearUsuarioRol(newRol)) {
						mensaje = "El usuario y sus roles han sido editado correctamente";
						tipoMensaje = "Ok";
					}
					else {
						mensaje = "El usuario se editó en sistema, pero no su rol";
						tipoMensaje = "Error";
					}
				}
				else {
					mensaje = "El usuario se editó en sistema, pero no su rol. Tiene más de un rol asignado.";
					tipoMensaje = "Error";
				}
				
				User updatedUser = usuarioService.obtenerUsuarioPorNombre(txtUsername);
				Set<UserRole> rolesUpdated = updatedUser.getUserRole();
				for (UserRole r:rolesUpdated) {
					rol = r.getRole();
				}
				modelAndView.addObject("usuario", updatedUser);
			}
			else 
			{
				System.out.println("Error al editar el usuario");
				mensaje = "Ocurrió un error al editar el usuario";
				tipoMensaje = "Error";
				
				Set<UserRole> roles = usuario.getUserRole();
				for (UserRole r:roles) {
					rol = r.getRole();
				}				
				
				modelAndView.addObject("usuario", usuario);
			}
		}
		else {
			System.out.println("Error al editar el usuario - Usuario no existe");
			mensaje = "El usuario no existe en plataforma, no se puede editar";
			tipoMensaje = "Error";
			modelAndView.addObject("usuario", usuario);
		}		

		modelAndView.addObject("rol", rol);
		modelAndView.addObject("mensaje", mensaje);
		modelAndView.addObject("tipoMensaje", tipoMensaje);

		return modelAndView;
	}
	
	@PostMapping("/eliminar")
	public ModelAndView eliminarUsuario(
			@RequestParam String idUsuario
			)
	{
		ModelAndView modelAndView = new ModelAndView("usuarios/listado");

		String mensaje = "";
		String tipoMensaje = "";

		List<User> listadoUsuarios = new ArrayList<User>();
		
		User usuario = usuarioService.obtenerUsuarioPorNombre(idUsuario);
		
		if (usuario != null) {
			
			//try {
			//	usuarioRolService.eliminarRolesUsuario(usuario);
				
				if (usuarioService.eliminarUsuario(idUsuario)) {
					System.out.println("El usuario fue eliminado correctamente. ID: " + idUsuario);
					mensaje = "El usuario " + idUsuario + " fue eliminado correctamente";
					tipoMensaje = "Ok";
				}
				else {
					System.out.println("Error al eliminar el usuario. ID: " + idUsuario);
					mensaje = "El usuario no se puede eliminar";
					tipoMensaje = "Error";
				}

			//} catch(Exception e) {
			//	System.out.println("Error al eliminar el usuario. ID: " + idUsuario);
			//	System.out.println(e.getMessage());
			//	mensaje = "Error al eliminar los roles asociados al usuario";
			//	tipoMensaje = "Error";
			//}
			
		}
		else {
			System.out.println("Error al eliminar el usuario - Usuario no existe");
			mensaje = "El usuario no existe en plataforma, no se puede eliminar";
			tipoMensaje = "Error";
		}
		
		listadoUsuarios = usuarioService.obtenerUsuarios();

		modelAndView.addObject("usuarios",listadoUsuarios);
		modelAndView.addObject("mensaje", mensaje);
		modelAndView.addObject("tipoMensaje", tipoMensaje);

		return modelAndView;
	}	
	
}
