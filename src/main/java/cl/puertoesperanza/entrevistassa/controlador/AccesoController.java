package cl.puertoesperanza.entrevistassa.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccesoController {

	@GetMapping("/acceso")
	public ModelAndView login() {
		return new ModelAndView("acceso/login");
	}
	
	@GetMapping("/recurso-prohibido")
	public ModelAndView recurso403() {
		return new ModelAndView("error/403");
	}
}
