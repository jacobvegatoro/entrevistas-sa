package cl.puertoesperanza.entrevistassa.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cl.puertoesperanza.entrevistassa.modelo.Comuna;
import cl.puertoesperanza.entrevistassa.modelo.Region;
import cl.puertoesperanza.entrevistassa.servicio.ComunaService;
import cl.puertoesperanza.entrevistassa.servicio.RegionService;

@RestController
@RequestMapping("/api/v1/comunas")
public class ComunaRestController {

	@Autowired
	private ComunaService comunaServicio;

	@Autowired
	private RegionService regionServicio;

	@GetMapping("/{idregion}")
	@ResponseStatus(HttpStatus.OK)
	public List<Comuna> buscarPorRegion(@PathVariable Integer idregion) {
		Region region = regionServicio.obtenerRegionPorId(idregion);
		return comunaServicio.obtenerComunasPorRegion(region);
	}

}
