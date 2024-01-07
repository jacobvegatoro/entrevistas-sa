package cl.puertoesperanza.entrevistassa.controlador;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("inicio")
public class InicioController {

	@GetMapping
	public ModelAndView inicioGenerico() 
	{
		ModelAndView modelAndView = new ModelAndView("inicio/estadisticas");
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//List<User> usuarios = userService.findUserByUserName(auth.getName());
		//String mensaje = "";
		/*try {
			User usuario = usuarios.get(0);
			mensaje = usuario.getName() + " " + usuario.getLastName();
		}catch(Exception e) {
			System.out.println("Error al recuperar el usuario");
		}*/
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		modelAndView.addObject("username", user.getUsername());
		//modelAndView.addObject("mensaje",mensaje);
		return modelAndView;
	}

}
