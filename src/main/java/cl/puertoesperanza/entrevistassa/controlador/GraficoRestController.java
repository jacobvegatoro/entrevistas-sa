package cl.puertoesperanza.entrevistassa.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cl.puertoesperanza.entrevistassa.modelo.GraficoCuatro;
import cl.puertoesperanza.entrevistassa.modelo.GraficoDos;
import cl.puertoesperanza.entrevistassa.modelo.GraficoTres;
import cl.puertoesperanza.entrevistassa.modelo.GraficoUno;
import cl.puertoesperanza.entrevistassa.servicio.GraficoCuatroService;
import cl.puertoesperanza.entrevistassa.servicio.GraficoDosService;
import cl.puertoesperanza.entrevistassa.servicio.GraficoTresService;
import cl.puertoesperanza.entrevistassa.servicio.GraficoUnoService;

@RestController
@RequestMapping("/api/v1/graficos")
public class GraficoRestController {

	@Autowired
	private GraficoUnoService graficoUnoServicio;

	@Autowired
	private GraficoDosService graficoDosServicio;

	@Autowired
	private GraficoTresService graficoTresServicio;

	@Autowired
	private GraficoCuatroService graficoCuatroServicio;

	@GetMapping("/graficouno")
	@ResponseStatus(HttpStatus.OK)
	public List<GraficoUno> obtenerGraficoUno() {
		List<GraficoUno> lgraficouno = graficoUnoServicio.obtenerGraficoUno();
		return lgraficouno;
	}

	@GetMapping("/graficodos")
	@ResponseStatus(HttpStatus.OK)
	public List<GraficoDos> obtenerGraficoDos() {
		List<GraficoDos> lgraficodos = graficoDosServicio.obtenerGraficoDos();
		return lgraficodos;
	}

	@GetMapping("/graficotres")
	@ResponseStatus(HttpStatus.OK)
	public List<GraficoTres> obtenerGraficoTres() {
		List<GraficoTres> lgraficotres = graficoTresServicio.obtenerGraficoTres();
		return lgraficotres;
	}

	@GetMapping("/graficocuatro")
	@ResponseStatus(HttpStatus.OK)
	public List<GraficoCuatro> obtenerGraficoCuatro() {
		List<GraficoCuatro> lgraficocuatro = graficoCuatroServicio.obtenerGraficoCuatro();
		return lgraficocuatro;
	}

}
