package cl.puertoesperanza.entrevistassa.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cl.puertoesperanza.entrevistassa.modelo.Entrevistado;
import cl.puertoesperanza.entrevistassa.servicio.EntrevistadoService;

@RestController
@RequestMapping("/api/v1/entrevistados")
public class EntrevistadoRestController {

	@Autowired
	private EntrevistadoService entrevistadoServicio;
	
	@GetMapping("/{run}")
	@ResponseStatus(HttpStatus.OK)
	public List<Entrevistado> buscarPorRun(@PathVariable String run) {
		List<Entrevistado> lentrevistados = entrevistadoServicio.obtenerEntrevistadosPorRun(run);
		return lentrevistados;
	}

}
