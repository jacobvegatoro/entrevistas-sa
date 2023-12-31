package cl.puertoesperanza.entrevistassa.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	/*@GetMapping("/")
	public String redirectToLogin() {
		return "redirect:/login";
	}*/
	
	@GetMapping("/login")
	public String showLoginForm(Model model,
			@RequestParam(name="error", required=false) String error,
			@RequestParam(name="logout", required=false) String logout) {
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		//model.addAttribute("userCredentials", new UserCredential());
		return "acceso/login";
	}
	
	/*@PostMapping("/logincheck")
	public String loginCheck(@ModelAttribute(name="userCredentials") UserCredential userCredential ) {
		if (userCredential.getUsername().equals("user") && userCredential.getPassword().equals("user")) {
			return "contacts";
		}
		
		return "redirect:/login?error";
	}*/

	@GetMapping({"/loginsuccess","/"})
	public String loginCheck() {
		return "redirect:/inicio";
	}

}
