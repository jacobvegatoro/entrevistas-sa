package cl.puertoesperanza.entrevistassa.controlador;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cl.puertoesperanza.entrevistassa.busqueda.BancoHashmap;
import cl.puertoesperanza.entrevistassa.busqueda.CanalHashmap;
import cl.puertoesperanza.entrevistassa.busqueda.CargoHashmap;
import cl.puertoesperanza.entrevistassa.busqueda.ClienteHashmap;
import cl.puertoesperanza.entrevistassa.busqueda.ComunaHashmap;
import cl.puertoesperanza.entrevistassa.busqueda.ContactadoHashmap;
import cl.puertoesperanza.entrevistassa.busqueda.EntrevistadoHashmap;
import cl.puertoesperanza.entrevistassa.busqueda.EntrevistadorHashmap;
import cl.puertoesperanza.entrevistassa.busqueda.EstadoHashmap;
import cl.puertoesperanza.entrevistassa.busqueda.InstalacionHashmap;
import cl.puertoesperanza.entrevistassa.busqueda.NacionalidadHashmap;
import cl.puertoesperanza.entrevistassa.busqueda.ParentescoHashmap;
import cl.puertoesperanza.entrevistassa.busqueda.PeriodoHashmap;
import cl.puertoesperanza.entrevistassa.busqueda.PresentacionHashmap;
import cl.puertoesperanza.entrevistassa.busqueda.PrevisionHashmap;
//import cl.puertoesperanza.entrevistassa.busqueda.ReclutadorHashmap;
import cl.puertoesperanza.entrevistassa.busqueda.SaludHashmap;
import cl.puertoesperanza.entrevistassa.busqueda.SeguroCovidHashmap;
import cl.puertoesperanza.entrevistassa.busqueda.ServicioHashmap;
import cl.puertoesperanza.entrevistassa.busqueda.TallaHashmap;
import cl.puertoesperanza.entrevistassa.busqueda.TipoCuentaHashmap;
import cl.puertoesperanza.entrevistassa.busqueda.UsuarioHashmap;
import cl.puertoesperanza.entrevistassa.busqueda.ValidadoHashmap;
import cl.puertoesperanza.entrevistassa.modelo.Cargo;
import cl.puertoesperanza.entrevistassa.modelo.Cliente;
import cl.puertoesperanza.entrevistassa.modelo.Comuna;
import cl.puertoesperanza.entrevistassa.modelo.Entrevistado;
import cl.puertoesperanza.entrevistassa.modelo.EntrevistadoEliminado;
import cl.puertoesperanza.entrevistassa.modelo.EntrevistadoVista;
import cl.puertoesperanza.entrevistassa.modelo.Entrevistador;
import cl.puertoesperanza.entrevistassa.modelo.Instalacion;
//import cl.puertoesperanza.entrevistassa.modelo.Reclutador;
import cl.puertoesperanza.entrevistassa.modelo.Region;
import cl.puertoesperanza.entrevistassa.servicio.BancoService;
import cl.puertoesperanza.entrevistassa.servicio.CanalService;
import cl.puertoesperanza.entrevistassa.servicio.CargoService;
import cl.puertoesperanza.entrevistassa.servicio.ClienteService;
import cl.puertoesperanza.entrevistassa.servicio.ComunaService;
import cl.puertoesperanza.entrevistassa.servicio.ContactadoService;
import cl.puertoesperanza.entrevistassa.servicio.EntrevistadoEliminadoService;
import cl.puertoesperanza.entrevistassa.servicio.EntrevistadoService;
import cl.puertoesperanza.entrevistassa.servicio.EntrevistadoVistaService;
import cl.puertoesperanza.entrevistassa.servicio.EntrevistadorService;
import cl.puertoesperanza.entrevistassa.servicio.EstadoService;
import cl.puertoesperanza.entrevistassa.servicio.InstalacionService;
import cl.puertoesperanza.entrevistassa.servicio.NacionalidadService;
import cl.puertoesperanza.entrevistassa.servicio.ParentescoService;
import cl.puertoesperanza.entrevistassa.servicio.PeriodoService;
import cl.puertoesperanza.entrevistassa.servicio.PresentacionService;
import cl.puertoesperanza.entrevistassa.servicio.PrevisionService;
import cl.puertoesperanza.entrevistassa.servicio.ReclutadorService;
import cl.puertoesperanza.entrevistassa.servicio.RegionService;
import cl.puertoesperanza.entrevistassa.servicio.SaludService;
import cl.puertoesperanza.entrevistassa.servicio.SeguroCovidService;
import cl.puertoesperanza.entrevistassa.servicio.ServicioService;
import cl.puertoesperanza.entrevistassa.servicio.TallaService;
import cl.puertoesperanza.entrevistassa.servicio.TipoCuentaService;
import cl.puertoesperanza.entrevistassa.servicio.UsuarioService;
import cl.puertoesperanza.entrevistassa.servicio.ValidadoService;
//import cl.puertoesperanza.entrevistassa.utilidades.EntrevistadoExcelExporter;
import cl.puertoesperanza.entrevistassa.utilidades.FastExcelSimpleWrite;
import cl.puertoesperanza.entrevistassa.utilidades.Util;

@Controller
@RequestMapping("/entrevistas")
public class EntrevistadoController {

	@Autowired
	private EntrevistadoService entrevistadoServicio;

	@Autowired
	private EntrevistadoVistaService entrevistadoVistaServicio;

	@Autowired
	private ClienteService clienteServicio;
	
	@Autowired
	private CargoService cargoServicio;
	
	@Autowired
	private CanalService canalServicio;

	@Autowired
	private ReclutadorService reclutadorServicio;

	@Autowired
	private RegionService regionServicio;
	
	@Autowired
	private ComunaService comunaServicio;

	@Autowired
	private InstalacionService instalacionServicio;
	
	@Autowired
	private EntrevistadorService entrevistadorServicio;	
	
	@Autowired
	private ValidadoService validadoServicio;

	@Autowired
	private EstadoService estadoServicio;

	@Autowired
	private PeriodoService periodoServicio;

	@Autowired
	private ServicioService servicioServicio;

	@Autowired
	private ContactadoService contactadoServicio;

	@Autowired
	private PresentacionService presentacionServicio;

	@Autowired
	private NacionalidadService nacionalidadServicio;

	@Autowired
	private PrevisionService previsionServicio;

	@Autowired
	private SaludService saludServicio;

	@Autowired
	private SeguroCovidService seguroCovidServicio;

	@Autowired
	private TipoCuentaService tipoCuentaServicio;

	@Autowired
	private BancoService bancoServicio;

	@Autowired
	private TallaService tallaServicio;

	@Autowired
	private ParentescoService parentescoServicio;

	@Autowired
	private EntrevistadoEliminadoService entrevistadoEliminadoServicio;
	
	@Autowired
	private UsuarioService usuarioServicio;
	
	private Integer paginacion = 10;
	
	private Integer diasexportacion = 30;
	
	@GetMapping("/listadobusqueda")
	public ModelAndView listaentrevistados(@RequestParam(defaultValue = "1") Integer p) 
	{
		//System.out.println("Inicio método listaentrevistados");
		
		ModelAndView modelAndView = new ModelAndView("entrevistas/listafiltro");

		
		String roluser = "ROLE_USER";		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.getAuthorities().toString().contains("ROLE_ADMIN")) {
			roluser = "ROLE_ADMIN";
		}
		else if (auth.getAuthorities().toString().contains("ROLE_SUPERADMIN")) {
			roluser = "ROLE_SUPERADMIN";
		}

		List<EntrevistadoVista> listaentrevistados = new ArrayList<EntrevistadoVista>();
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();		
		long cantTotal = 0;
		int inicio = (p-1) * this.paginacion;
		
		if (roluser.equals("ROLE_USER")) {
			//cl.puertoesperanza.entrevistassa.modelo.User usuarioActual = usuarioServicio.obtenerUsuarioPorNombre(user.getUsername());
			//cantTotal = entrevistadoServicio.obtenerEntrevistadosPorUsuario(usuarioActual).size();
			cantTotal = entrevistadoVistaServicio.obtenerCantidadRegistrosUsuario(user.getUsername());
			//listaentrevistados = entrevistadoServicio.getPageUser(p-1, this.paginacion, usuarioActual);
			listaentrevistados = entrevistadoVistaServicio.obtenerRegistrosPaginaUsuario(this.paginacion, inicio, user.getUsername());
		}
		else {
			//cantTotal = entrevistadoServicio.obtenerEntrevistados().size();
			//cantTotal = entrevistadoVistaServicio.obtenerEntrevistadosVista().size();
			cantTotal = entrevistadoVistaServicio.obtenerCantidadRegistros();
			//listaentrevistados = entrevistadoServicio.getPage(p-1, this.paginacion);
			//listaentrevistados = entrevistadoServicio.getPage(p-1, this.paginacion);
			listaentrevistados = entrevistadoVistaServicio.obtenerRegistrosPagina(this.paginacion, inicio);
		}

		modelAndView.addObject("paginas", Util.getArregloPaginas(p, (int) entrevistadoServicio.getPageCount(cantTotal, this.paginacion)));
		modelAndView.addObject("paginaActual",p);
		
		modelAndView.addObject("nombres","");
		modelAndView.addObject("appaterno","");
		modelAndView.addObject("apmaterno","");
		modelAndView.addObject("run","");
				
		//System.out.println("Variables inicializadas");
		
		//System.out.println("Lista de entrevistados generada");

		modelAndView.addObject("entrevistados",listaentrevistados);

		//System.out.println("Lista de entrevistados agregada al modelo");
		
		modelAndView.addObject("roluser",roluser);

		return modelAndView;
	}

	@GetMapping("/listadoexportar")
	public ModelAndView listaexportar(@RequestParam(defaultValue = "1") Integer p) 
	{
		//System.out.println("Inicio método entrevistadosfiltro");
		
		ModelAndView modelAndView = new ModelAndView("entrevistas/listado");

		String roluser = "ROLE_USER";
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth.getAuthorities().toString().contains("ROLE_ADMIN")) {
			roluser = "ROLE_ADMIN";
		}
		else if (auth.getAuthorities().toString().contains("ROLE_SUPERADMIN")) {
			roluser = "ROLE_SUPERADMIN";
		}

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		List<EntrevistadoVista> listaentrevistados = new ArrayList<EntrevistadoVista>();
		
		long cantTotal = 0;
		int inicio = (p-1)*this.paginacion;
		
		if (roluser.equals("ROLE_USER")) {
			//cantTotal = entrevistadoVistaServicio.obtenerEntrevistadosVistaUsuario(user.getUsername()).size();
			cantTotal = entrevistadoVistaServicio.obtenerCantidadRegistrosUsuario(user.getUsername());
			//listaentrevistados = entrevistadoVistaServicio.getPageUsuario(p-1, this.paginacion, user.getUsername());
			listaentrevistados = entrevistadoVistaServicio.obtenerRegistrosPaginaUsuario(this.paginacion, inicio, user.getUsername());
		}
		else {
			//cantTotal = entrevistadoVistaServicio.obtenerEntrevistadosVista().size();
			cantTotal = entrevistadoVistaServicio.obtenerCantidadRegistros();
			//listaentrevistados = entrevistadoVistaServicio.getPage(p-1, this.paginacion);
			listaentrevistados = entrevistadoVistaServicio.obtenerRegistrosPagina(this.paginacion, inicio);
		}
		
		modelAndView.addObject("paginas", Util.getArregloPaginas(p, (int) entrevistadoVistaServicio.getPageCount(cantTotal, this.paginacion)));
		modelAndView.addObject("paginaActual",p);
		
		modelAndView.addObject("idcargoselec",0);
		modelAndView.addObject("cargos",cargoServicio.obtenerCargos());
		modelAndView.addObject("idcanalselec",0);
		modelAndView.addObject("canales",canalServicio.obtenerCanales());

		//modelAndView.addObject("idreclutadorselec",0);
		//modelAndView.addObject("reclutadores",reclutadorServicio.obtenerReclutadores());
		
		List<cl.puertoesperanza.entrevistassa.modelo.User> usuarios = new ArrayList<cl.puertoesperanza.entrevistassa.modelo.User>();
		
		if (roluser.equals("ROLE_USER")) {
			usuarios.add(usuarioServicio.obtenerUsuarioPorNombre(user.getUsername()));
		}
		else {
			usuarios = usuarioServicio.obtenerUsuarios();
		}
		
		modelAndView.addObject("usuarios",usuarios);
		modelAndView.addObject("usernameselec","");
		
		modelAndView.addObject("idestadoselec",0);
		modelAndView.addObject("estados",estadoServicio.obtenerEstados());
		modelAndView.addObject("fechamin","");
		modelAndView.addObject("fechamax","");
		modelAndView.addObject("fechaestmin","");
		modelAndView.addObject("fechaestmax","");
		modelAndView.addObject("empresa","");
		modelAndView.addObject("idvalidadoselec",0);
		modelAndView.addObject("validados",validadoServicio.obtenerValidados());

		//System.out.println("Variables inicializadas");
		
		//System.out.println("Lista de entrevistados generada");

		modelAndView.addObject("entrevistados",listaentrevistados);

		//System.out.println("Lista de entrevistados agregada al modelo");

		modelAndView.addObject("roluser",roluser);		

		return modelAndView;
	}
	
	@GetMapping("/busqueda")
	public ModelAndView filtrarentrevistados(
			@RequestParam(defaultValue = "1") Integer p, 
			@RequestParam(defaultValue = "") String e, 
			@RequestParam(defaultValue = "") String u, 
			@RequestParam(defaultValue = "") String fn, 
			@RequestParam(defaultValue = "") String fx,	
			@RequestParam(defaultValue = "") String fne, 
			@RequestParam(defaultValue = "") String fxe,	
			@RequestParam(defaultValue = "0") Integer t, 
			@RequestParam(defaultValue = "0") Integer c, 
			@RequestParam(defaultValue = "0") Integer n, 
			@RequestParam(defaultValue = "0") Integer v 
			) 
	{
		System.out.println("Se inicia búsqueda de registros");
		ModelAndView modelAndView = new ModelAndView("entrevistas/listabusqueda");

		String roluser = "ROLE_USER";
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (auth.getAuthorities().toString().contains("ROLE_ADMIN")) {
			roluser = "ROLE_ADMIN";
		}
		else if (auth.getAuthorities().toString().contains("ROLE_SUPERADMIN")) {
			roluser = "ROLE_SUPERADMIN";
		}

		String empresa = "";
		if (e.trim().length() > 0) 
			empresa = e;
		modelAndView.addObject("empresa",empresa);

		Integer idvalidadoselec = 0;
		if (v > 0) 
			idvalidadoselec = v;
		modelAndView.addObject("idvalidadoselec",idvalidadoselec);
		modelAndView.addObject("validados",validadoServicio.obtenerValidados());

		Integer idcargoselec = 0;
		//Cargo cargo = new Cargo();
		if (c > 0) {
			idcargoselec = c;
			//cargo = cargoServicio.obtenerCargoPorId(idcargoselec);
		} 
		modelAndView.addObject("idcargoselec",idcargoselec);
		modelAndView.addObject("cargos",cargoServicio.obtenerCargos());

		Integer idcanalselec = 0;
		//Canal canal = new Canal();
		if (n > 0) {
			idcanalselec = n;
			//canal = canalServicio.obtenerCanalPorId(idcanalselec);
		} 
		modelAndView.addObject("idcanalselec",idcanalselec);
		modelAndView.addObject("canales",canalServicio.obtenerCanales());

		/*
		Integer idreclutadorselec = 0;
		//Reclutador reclutador = new Reclutador();
		if (r > 0) {
			idreclutadorselec = r;
			//reclutador = reclutadorServicio.obtenerReclutadorPorId(idreclutadorselec);
		} 
		modelAndView.addObject("idreclutadorselec",idreclutadorselec);
		modelAndView.addObject("reclutadores",reclutadorServicio.obtenerReclutadores());
		*/
		
		String usernameselec = "";
		if (u.trim().length() > 0) {
			usernameselec = u;
		}
		
		List<cl.puertoesperanza.entrevistassa.modelo.User> usuarios = new ArrayList<cl.puertoesperanza.entrevistassa.modelo.User>();
		
		if (roluser.equals("ROLE_USER")) {
			usuarios.add(usuarioServicio.obtenerUsuarioPorNombre(user.getUsername()));
		}
		else {
			usuarios = usuarioServicio.obtenerUsuarios();
		}

		modelAndView.addObject("usernameselec",usernameselec);
		modelAndView.addObject("usuarios",usuarios);
		
		Integer idestadoselec = 0;
		if (t > 0) 
			idestadoselec = t;
		modelAndView.addObject("idestadoselec",idestadoselec);
		
		//modelAndView.addObject("estados",Util.getEstados());
		modelAndView.addObject("estados",estadoServicio.obtenerEstados());
		
		String fechamin = "";
		if (fn.trim().length() > 0)
			fechamin = fn;
		modelAndView.addObject("fechamin",fechamin);

		String fechamax = "";
		if (fx.trim().length() > 0)
			fechamax = fx;
		modelAndView.addObject("fechamax",fechamax);

		String fechaestmin = "";
		if (fne.trim().length() > 0)
			fechaestmin = fne;
		modelAndView.addObject("fechaestmin",fechaestmin);

		String fechaestmax = "";
		if (fxe.trim().length() > 0)
			fechaestmax = fxe;
		modelAndView.addObject("fechaestmax",fechaestmax);

		System.out.println("Variables iniciales seteadas");
		
		//List<EntrevistadoVista> listaentrevistas = new ArrayList<EntrevistadoVista>();

		long cantRegistros = 0;
		
		if (roluser.equals("ROLE_USER")) {
			cantRegistros = entrevistadoVistaServicio.contarBuscarEntrevistadosUsuario(
					empresa, usernameselec, fechamin, fechamax, fechaestmin, 
					fechaestmax, idestadoselec, 
					idcargoselec, idcanalselec, idvalidadoselec, user.getUsername());
		}
		else {
			cantRegistros = entrevistadoVistaServicio.contarBuscarEntrevistados(
					empresa, usernameselec, fechamin, fechamax, fechaestmin, 
					fechaestmax, idestadoselec, 
					idcargoselec, idcanalselec, idvalidadoselec);
		}

		System.out.println("Listado completo de entrevistados filtrado obtenido");

		Integer cantPaginas = (int) entrevistadoVistaServicio.getPageCount(cantRegistros, this.paginacion);
		if (p > cantPaginas && cantPaginas > 0)
			p = cantPaginas;
		modelAndView.addObject("paginas",Util.getArregloPaginas(p, cantPaginas));
		modelAndView.addObject("paginaActual",p);

		List<EntrevistadoVista> listaentrevistaspagina = new ArrayList<EntrevistadoVista>();

		if (roluser.equals("ROLE_USER")) {
			listaentrevistaspagina = entrevistadoVistaServicio.buscarEntrevistadosUsuarioPagina(
					empresa, usernameselec, fechamin, fechamax, fechaestmin, 
					fechaestmax, idestadoselec, 
					idcargoselec, idcanalselec, idvalidadoselec, p-1, this.paginacion, user.getUsername());
		}
		else {
			listaentrevistaspagina = entrevistadoVistaServicio.buscarEntrevistadosPagina(
					empresa, usernameselec, fechamin, fechamax, fechaestmin, 
					fechaestmax, idestadoselec, 
					idcargoselec, idcanalselec, idvalidadoselec, p-1, this.paginacion);
		}

		System.out.println("Listado completo de entrevistados filtrado para la página " + p + " obtenido");

		//modelAndView.addObject("entrevistados",entrevistadoServicio.getPage(p-1, 10));
		//modelAndView.addObject("entrevistados",listaentrevistas);
		modelAndView.addObject("entrevistados",listaentrevistaspagina);
		
		System.out.println("Listado de entrevistas enviado a la vista");
		
		modelAndView.addObject("roluser",roluser);
		
		return modelAndView;
	}
	
	@GetMapping("/filtro")
	public ModelAndView filtrarbusquedaentrevistados(
			@RequestParam(defaultValue = "1") Integer p, 
			@RequestParam(defaultValue = "") String n, 
			@RequestParam(defaultValue = "") String ap, 
			@RequestParam(defaultValue = "") String am, 
			@RequestParam(defaultValue = "") String r 
			) 
	{
		System.out.println("Se inicia búsqueda de registros con filtro");
		ModelAndView modelAndView = new ModelAndView("entrevistas/listafiltrobusqueda");

		String nombres = "";
		if (n.trim().length() > 0) 
			nombres = n;
		modelAndView.addObject("nombres",n);
		
		String appaterno = "";
		if (ap.trim().length() > 0)
			appaterno = ap;
		modelAndView.addObject("appaterno",appaterno);

		String apmaterno = "";
		if (am.trim().length() > 0)
			apmaterno = am;
		modelAndView.addObject("apmaterno",apmaterno);

		String run = "";
		if (r.trim().length() > 0)
			run = r;
		modelAndView.addObject("run",run);

		System.out.println("Variables iniciales seteadas");
		
		String roluser = "ROLE_USER";
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.getAuthorities().toString().contains("ROLE_ADMIN")) {
			roluser = "ROLE_ADMIN";
		}
		else if (auth.getAuthorities().toString().contains("ROLE_SUPERADMIN")) {
			roluser = "ROLE_SUPERADMIN";
		}
		
		//List<Entrevistado> listaentrevistas = new ArrayList<Entrevistado>();

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//cl.puertoesperanza.entrevistassa.modelo.User usuarioActual = usuarioServicio.obtenerUsuarioPorNombre(user.getUsername());
		
		//System.out.println("Usuario obtenido: " + usuarioActual.getUsername());

		long cantRegistros = 0;
		if (roluser.equals("ROLE_USER")) {
			cantRegistros = entrevistadoVistaServicio.contarFiltrarRegistrosUsuario(
					nombres, appaterno, apmaterno, run, user.getUsername());
		}
		else {
			cantRegistros = entrevistadoVistaServicio.contarFiltrarRegistros(
					nombres, appaterno, apmaterno, run);
		}

		System.out.println("Listado completo de entrevistados filtrado obtenido");

		Integer cantPaginas = (int) entrevistadoServicio.getPageCount(cantRegistros,this.paginacion);
		if (p > cantPaginas && cantPaginas > 0)
			p = cantPaginas;
		modelAndView.addObject("paginas",Util.getArregloPaginas(p, cantPaginas));
		modelAndView.addObject("paginaActual",p);

		List<EntrevistadoVista> listaentrevistaspagina = new ArrayList<EntrevistadoVista>();

		if (roluser.equals("ROLE_USER")) {
			//listaentrevistaspagina = entrevistadoServicio.buscarEntrevistadosFiltroUsuarioPagina(
			//		nombres, appaterno, apmaterno, run, p-1, this.paginacion, usuarioActual);
			listaentrevistaspagina = entrevistadoVistaServicio.filtrarRegistrosPaginaUsuario(
					nombres, appaterno, apmaterno, run, (p-1), this.paginacion, user.getUsername());
			
		}else {
			listaentrevistaspagina = entrevistadoVistaServicio.filtrarRegistrosPagina(
					nombres, appaterno, apmaterno, run, (p-1), this.paginacion);
			
		}


		//System.out.println("Listado completo de entrevistados filtrado para la página " + p + " obtenido");

		modelAndView.addObject("entrevistados",listaentrevistaspagina);
		
		//System.out.println("Listado de entrevistas enviado a la vista");

		modelAndView.addObject("roluser",roluser);
		
		return modelAndView;
	}
	
	@GetMapping("/registro")
	public ModelAndView registroUnico() 
	{
		System.out.println("Se inicia creación de entrevista");
		
		ModelAndView modelAndView = new ModelAndView("entrevistas/registro");
		modelAndView.addObject("clientes",clienteServicio.obtenerClientes());
		modelAndView.addObject("cargos",cargoServicio.obtenerCargos());
		modelAndView.addObject("canales",canalServicio.obtenerCanales());
		//modelAndView.addObject("reclutadores",reclutadorServicio.obtenerReclutadores());
		modelAndView.addObject("regiones",regionServicio.obtenerRegiones());

		System.out.println("Se envian listas de elementos a la vista");
		
		Entrevistado entrevistado = new Entrevistado();
		entrevistado.setFechaIngreso(new Date());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fechaIngresoStr = sdf.format(new Date());
		
		modelAndView.addObject("fechaIngresoStr", fechaIngresoStr);

		String mensaje = "";
		String tipoMensaje = "";
		Integer idregionselec = 0;
		Integer idcomunaselec = 0;
		modelAndView.addObject("idregionselec", idregionselec);
		modelAndView.addObject("idcomunaselec", idcomunaselec);
		modelAndView.addObject("mensaje", mensaje);
		modelAndView.addObject("tipoMensaje", tipoMensaje);

		modelAndView.addObject("entrevistado", entrevistado);

		System.out.println("Se almacenan los objetos y variables en el modelo");

		return modelAndView;
	}
	
	@PostMapping("/crear")
	public ModelAndView crearRegistroUnico(@ModelAttribute Entrevistado entrevistado, 
			@RequestParam String slcRegion,			
			@RequestParam String slcComuna,
			@RequestParam String txtFechaIngreso) 
	{
		System.out.println("Se inicia la recepción de datos para la creación de un registro");
		
		ModelAndView modelAndView = new ModelAndView("entrevistas/registro");

		modelAndView.addObject("clientes",clienteServicio.obtenerClientes());
		modelAndView.addObject("cargos",cargoServicio.obtenerCargos());
		modelAndView.addObject("canales",canalServicio.obtenerCanales());
		modelAndView.addObject("reclutadores",reclutadorServicio.obtenerReclutadores());
		modelAndView.addObject("regiones",regionServicio.obtenerRegiones());

		System.out.println("Se envían las listas de registros");

		String mensaje = "";
		String tipoMensaje = "";
		
		//System.out.println("Fecha: " + txtFechaIngreso);
		
		Date fechaIngreso = new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");		
		String fechaIngresoStr = ft.format(new Date());
		
		try 
		{
			fechaIngreso = ft.parse(txtFechaIngreso);
		}
		catch(ParseException e) 
		{
			System.out.println("Imposible transformar fecha usando formato " + ft);
		}
		
		entrevistado.setFechaIngreso(fechaIngreso);

		System.out.println("Se agrega fecha de ingreso al objeto");

		List<Entrevistado> buscaEntrevistado = entrevistadoServicio.obtenerEntrevistadosPorRun(entrevistado.getRun());

		System.out.println("Se obtiene entrevistados que tienen el mismo RUN ingresado");

		List<Comuna> listacomunas = new ArrayList<Comuna>();
		Comuna comuna = comunaServicio.obtenerComunaPorId(Integer.parseInt(slcComuna));
		entrevistado.setComuna(comuna);

		System.out.println("Lista de comunas obtenida");
		System.out.println("Cargo: " + entrevistado.getCargo());
		System.out.println("Canal: " + entrevistado.getCanal());
		entrevistado.setCanal(canalServicio.obtenerCanalPorId(entrevistado.getCanal().getId()));
		entrevistado.setCargo(cargoServicio.obtenerCargoPorId(entrevistado.getCargo().getId()));

		System.out.println("Cliente: " + entrevistado.getCliente());
		System.out.println("Reclutador: " + entrevistado.getReclutador());
		entrevistado.setCliente(clienteServicio.obtenerClientePorId(entrevistado.getCliente().getId()));
		//entrevistado.setReclutador(reclutadorServicio.obtenerReclutadorPorId(entrevistado.getReclutador().getId()));

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		System.out.println("Usuario: " + user.getUsername());
		
		entrevistado.setUsuario(usuarioServicio.obtenerUsuarioPorNombre(user.getUsername()));
		
		if (buscaEntrevistado.size() == 0) 
		{
			entrevistado = Util.transformarTextos(entrevistado);
			if (entrevistadoServicio.agregarEntrevistado(entrevistado)) 
			{
				System.out.println("Registro agregado correctamente");
				mensaje = "El entrevistado ha sido agregado correctamente";
				tipoMensaje = "Ok";
			}
			else 
			{
				System.out.println("Error al agregar el registro");
				mensaje = "Ocurrió un error al agregar el entrevistado";
				tipoMensaje = "Error";
			}
		}
		else 
		{
			System.out.println("El RUN está repetido");
			mensaje = "El RUN ingresado ya se encuentra en la base de datos";
			tipoMensaje = "Error";
		}

		Integer idregionselec = 0;
		Integer idcomunaselec = 0;
		
		if (tipoMensaje == "Error") 
		{
			modelAndView.addObject("entrevistado", entrevistado);
			if (!slcRegion.trim().equals("") && slcRegion.length() > 0) 
			{
				idregionselec = Integer.parseInt(slcRegion);
			}

			if (!slcComuna.trim().equals("") && slcComuna.length() > 0) 
			{
				idcomunaselec = Integer.parseInt(slcComuna);
			}
			Region regionSelec = regionServicio.obtenerRegionPorId(idregionselec);
			listacomunas = comunaServicio.obtenerComunasPorRegion(regionSelec);	
			fechaIngresoStr = ft.format(fechaIngreso);
			System.out.println("Se detecta error y se envían los datos al formulario");
		}
		else 
		{
			modelAndView.addObject("entrevistado", new Entrevistado());			
			fechaIngresoStr = ft.format(new Date());
			System.out.println("Registro correcto, se inicia un nuevo registro");
		}

		modelAndView.addObject("fechaIngresoStr", fechaIngresoStr);
		modelAndView.addObject("listacomunas", listacomunas);
		modelAndView.addObject("idregionselec", idregionselec);
		modelAndView.addObject("idcomunaselec", idcomunaselec);
		
		modelAndView.addObject("mensaje", mensaje);
		modelAndView.addObject("tipoMensaje", tipoMensaje);

		System.out.println("Enviados los datos a la vista");

		return modelAndView;		
	}

	@GetMapping("/editar/{identrevistado}")
	public ModelAndView edicionRegistro(@PathVariable Integer identrevistado) 
	{
		ModelAndView modelAndView = new ModelAndView("entrevistas/editar");
		Entrevistado entrevistado = entrevistadoServicio.obtenerEntrevistadoPorId(identrevistado);

		entrevistado = Util.transformarTextos(entrevistado);

		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
		String fechaIngresoStr = ft.format(entrevistado.getFechaIngreso());

		Integer idregionselec = entrevistado.getComuna().getRegion().getId();
		Integer idcomunaselec = entrevistado.getComuna().getId();
		List<Comuna> listacomunas = new ArrayList<Comuna>();
		Region regionSelec = regionServicio.obtenerRegionPorId(idregionselec);
		listacomunas = comunaServicio.obtenerComunasPorRegion(regionSelec);	

		Integer idregcontactoselec = 0;
		Integer idcomcontactoselec = 0;
		List<Comuna> listacomcontacto = new ArrayList<Comuna>();
		
		if (entrevistado.getComunaContacto() != null) 
		{
			idregcontactoselec = entrevistado.getComunaContacto().getRegion().getId();
			idcomcontactoselec = entrevistado.getComunaContacto().getId();
			Region regionContactoSelec = regionServicio.obtenerRegionPorId(idregcontactoselec);
			listacomcontacto = comunaServicio.obtenerComunasPorRegion(regionContactoSelec);
		}
		
		String fechaEstadoStr = "";
		String fechaContratacionStr = "";
		String fechaNacimientoStr = "";
		
		if (entrevistado.getFechaEstado() != null) 
		{
			fechaEstadoStr = ft.format(entrevistado.getFechaEstado());
		}

		if (entrevistado.getFechaContratacion() != null) 
		{
			fechaContratacionStr = ft.format(entrevistado.getFechaContratacion());
		}

		String edadStr = "";
		
		if (entrevistado.getFechaNacimiento() != null) 
		{
			fechaNacimientoStr = ft.format(entrevistado.getFechaNacimiento());
			LocalDate fechaNacimientoLocal = LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(entrevistado.getFechaNacimiento()));
			LocalDate fechaHoyLocal = LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			Period periodo = Period.between(fechaNacimientoLocal, fechaHoyLocal);

			edadStr = periodo.getYears() + " años, " 
			+ periodo.getMonths() + " meses y " + periodo.getDays() + " días";
		}
		
		Integer idestadoselec = 0;
		if (entrevistado.getEstado() != null && entrevistado.getEstado() > 0) 
		{
			idestadoselec = entrevistado.getEstado();			
		}
		
		Integer identrevistadorselec = 0;
		if (entrevistado.getEntrevistador() != null) 
		{
			identrevistadorselec = entrevistado.getEntrevistador().getId();
		}

		Integer idinstalacionselec = 0;
		if (entrevistado.getInstalacion() != null) 
		{
			idinstalacionselec = entrevistado.getInstalacion().getId();
		}

		String mensaje = "";
		String tipoMensaje = "";
		
		modelAndView.addObject("mensaje", mensaje);
		modelAndView.addObject("tipoMensaje", tipoMensaje);
		
		modelAndView.addObject("clientes",clienteServicio.obtenerClientes());
		modelAndView.addObject("cargos",cargoServicio.obtenerCargos());
		modelAndView.addObject("canales",canalServicio.obtenerCanales());
		//modelAndView.addObject("reclutadores",reclutadorServicio.obtenerReclutadores());
		modelAndView.addObject("regiones",regionServicio.obtenerRegiones());
		modelAndView.addObject("instalaciones",instalacionServicio.obtenerInstalaciones());
		//modelAndView.addObject("estados",Util.getEstados());
		modelAndView.addObject("estados",estadoServicio.obtenerEstados());
		modelAndView.addObject("entrevistadores",entrevistadorServicio.obtenerEntrevistadores());
		modelAndView.addObject("validados",validadoServicio.obtenerValidados());

		modelAndView.addObject("periodos",periodoServicio.obtenerPeriodos());
		modelAndView.addObject("servicios",servicioServicio.obtenerServicios());
		modelAndView.addObject("contactados",contactadoServicio.obtenerContactados());
		modelAndView.addObject("presentaciones",presentacionServicio.obtenerPresentaciones());
		modelAndView.addObject("nacionalidades",nacionalidadServicio.obtenerNacionalidades());
		modelAndView.addObject("previsiones",previsionServicio.obtenerPrevisiones());
		modelAndView.addObject("saludes",saludServicio.obtenerSaludes());
		modelAndView.addObject("seguroscovid",seguroCovidServicio.obtenerSegurosCovid());
		modelAndView.addObject("tiposcuenta",tipoCuentaServicio.obtenerTiposCuenta());
		modelAndView.addObject("bancos",bancoServicio.obtenerBancos());
		modelAndView.addObject("tallas",tallaServicio.obtenerTallas());
		modelAndView.addObject("parentescos",parentescoServicio.obtenerParentescos());
		
		modelAndView.addObject("idregionselec", idregionselec);
		modelAndView.addObject("idcomunaselec", idcomunaselec);
		modelAndView.addObject("idestadoselec", idestadoselec);
		modelAndView.addObject("listacomunas", listacomunas);
		modelAndView.addObject("fechaIngresoStr", fechaIngresoStr);
		modelAndView.addObject("fechaEstadoStr", fechaEstadoStr);
		modelAndView.addObject("fechaContratacionStr", fechaContratacionStr);
		modelAndView.addObject("fechaNacimientoStr", fechaNacimientoStr);
		modelAndView.addObject("edadStr", edadStr);

		modelAndView.addObject("idregcontactoselec", idregcontactoselec);
		modelAndView.addObject("idcomcontactoselec", idcomcontactoselec);
		modelAndView.addObject("listacomcontacto", listacomcontacto);

		modelAndView.addObject("identrevistadorselec", identrevistadorselec);
		modelAndView.addObject("idinstalacionselec", idinstalacionselec);
		
		modelAndView.addObject("entrevistado", entrevistado);

		return modelAndView;
	}
	
	@PostMapping("/procesa")
	public ModelAndView editarRegistroUnico(@ModelAttribute Entrevistado entrevistado, 
			@RequestParam String slcRegion,
			@RequestParam String slcComuna,
			@RequestParam String slcRegionEmergencia,
			@RequestParam String slcComunaEmergencia,
			@RequestParam String slcInstalacion,
			@RequestParam String slcEntrevistador,
			@RequestParam String slcEstado,
			@RequestParam String txtFechaIngreso,
			@RequestParam String txtFechaEstado,
			@RequestParam String txtFechaContratacion,
			@RequestParam String txtFechaNacimiento,
			@RequestParam String hdnUsername
			) 
	{
		System.out.println("Comienza procesamiento de edición de registro de entrevista");
		
		ModelAndView modelAndView = new ModelAndView("entrevistas/editar");

		//Integer idregionselec = entrevistado.getComuna().getRegion().getId();
		Integer idregionselec = Integer.parseInt(slcRegion);
		
		//Integer idcomunaselec = entrevistado.getComuna().getId();
		Integer idcomunaselec = Integer.parseInt(slcComuna);
		Comuna comunaSelec = new Comuna();
		comunaSelec = comunaServicio.obtenerComunaPorId(idcomunaselec);
		entrevistado.setComuna(comunaSelec);

		System.out.println("Obtengo la comuna y la región seleccionadas");
		
		List<Comuna> listacomunas = new ArrayList<Comuna>();
		Region regionSelec = new Region();
		regionSelec = regionServicio.obtenerRegionPorId(idregionselec);
		listacomunas = comunaServicio.obtenerComunasPorRegion(regionSelec);	

		Integer idregcontactoselec = 0;
		Integer idcomcontactoselec = 0;
		List<Comuna> listacomcontacto = new ArrayList<Comuna>();
		
		if (slcRegionEmergencia != null && slcRegionEmergencia.length() > 0) 
		{
			idregcontactoselec = Integer.parseInt(slcRegionEmergencia);
			Region regContactoSelec = regionServicio.obtenerRegionPorId(idregcontactoselec);
			listacomcontacto = comunaServicio.obtenerComunasPorRegion(regContactoSelec);
		}

		if (slcComunaEmergencia != null && slcComunaEmergencia.length() > 0) 
		{
			idcomcontactoselec = Integer.parseInt(slcComunaEmergencia);
			Comuna comContactoSelec = comunaServicio.obtenerComunaPorId(idcomcontactoselec);
			entrevistado.setComunaContacto(comContactoSelec);
		}
		else 
		{
			entrevistado.setComunaContacto(null);
		}
		
		Integer idestadoselec = 0;

		if (slcEstado != null && slcEstado.length() > 0) 
		{
			idestadoselec = Integer.parseInt(slcEstado);
			entrevistado.setEstado(idestadoselec);
		}
		else 
		{
			entrevistado.setEstado(null);
		}
		
		System.out.println("Obtengo la comuna y región para los datos de contacto");
		
		Date fechaIngreso = new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
		String fechaIngresoStr = txtFechaIngreso;

		try 
		{
			fechaIngreso = ft.parse(txtFechaIngreso);
		}
		catch(ParseException e) 
		{
			System.out.println("Imposible transformar fecha usando formato " + ft);
		}
		
		entrevistado.setFechaIngreso(fechaIngreso);

		System.out.println("Agrego fecha de ingreso al objeto");

		String fechaEstadoStr = "";
		String fechaContratacionStr = "";
		String fechaNacimientoStr = "";
		
		if (txtFechaEstado != null && txtFechaEstado.length() > 0) 
		{
			Date fechaEstado = new Date();

			try 
			{
				fechaEstado = ft.parse(txtFechaEstado);
			}
			catch(ParseException e) 
			{
				System.out.println("Imposible transformar fecha usando formato " + ft);
			}

			fechaEstadoStr = ft.format(fechaEstado);
			entrevistado.setFechaEstado(fechaEstado);
		}
		else 
		{
			entrevistado.setFechaEstado(null);
		}

		if (txtFechaContratacion != null && txtFechaContratacion.length() > 0) 
		{
			Date fechaContratacion = new Date();

			try 
			{
				fechaContratacion = ft.parse(txtFechaContratacion);
			}
			catch(ParseException e) 
			{
				System.out.println("Imposible transformar fecha usando formato " + ft);
			}

			fechaContratacionStr = ft.format(fechaContratacion);
			entrevistado.setFechaContratacion(fechaContratacion);
		}
		else 
		{
			entrevistado.setFechaContratacion(null);
		}

		String edadStr = "";
		
		if (txtFechaNacimiento != null && txtFechaNacimiento.length() > 0) 
		{
			Date fechaNacimiento = new Date();

			try 
			{
				fechaNacimiento = ft.parse(txtFechaNacimiento);
			}
			catch(ParseException e) 
			{
				System.out.println("Imposible transformar fecha usando formato " + ft);
			}

			fechaNacimientoStr = ft.format(fechaNacimiento);
			entrevistado.setFechaNacimiento(fechaNacimiento);

			LocalDate fechaNacimientoLocal = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(entrevistado.getFechaNacimiento()));
			LocalDate fechaHoyLocal = LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			Period periodo = Period.between(fechaNacimientoLocal, fechaHoyLocal);

			edadStr = periodo.getYears() + " años, " 
			+ periodo.getMonths() + " meses y " + periodo.getDays() + " días";
		}
		else 
		{
			entrevistado.setFechaNacimiento(null);
		}

		System.out.println("Agrego al objetos fechas de nacimiento, cambio estado y contratación");
				
		//Agrego cliente
		Cliente cliente = new Cliente();
		cliente = clienteServicio.obtenerClientePorId(entrevistado.getCliente().getId());
		entrevistado.setCliente(cliente);
		
		Cargo cargo = new Cargo();
		cargo = cargoServicio.obtenerCargoPorId(entrevistado.getCargo().getId());
		entrevistado.setCargo(cargo);

		cl.puertoesperanza.entrevistassa.modelo.User usuarioCreador = usuarioServicio.obtenerUsuarioPorNombre(hdnUsername);
		entrevistado.setUsuario(usuarioCreador);
		
		/*
		Reclutador reclutador = new Reclutador();
		reclutador = reclutadorServicio.obtenerReclutadorPorId(entrevistado.getReclutador().getId());
		entrevistado.setReclutador(reclutador);
		*/

		System.out.println("Agrego cliente, cargo y reclutador");
		
		Integer idinstalacionselec = 0;
		
		if (slcInstalacion != null && slcInstalacion.length() > 0) 
		{
			System.out.println("Existe una instalacion seleccionada");
			idinstalacionselec = Integer.parseInt(slcInstalacion);
			Instalacion instalacion = new Instalacion();
			instalacion = instalacionServicio.obtenerInstalacionPorId(idinstalacionselec);
			entrevistado.setInstalacion(instalacion);
		}
		else {
			System.out.println("No existe instalacion");
			entrevistado.setInstalacion(null);
		}

		System.out.println("Definida la instalación del objeto");

		Integer identrevistadorselec = 0;
		
		if (slcEntrevistador != null && slcEntrevistador.length() > 0) 
		{
			System.out.println("Existe un entrevistador seleccionado");
			identrevistadorselec = Integer.parseInt(slcEntrevistador);
			Entrevistador entrevistador = new Entrevistador();
			entrevistador = entrevistadorServicio.obtenerEntrevistadorPorId(identrevistadorselec);
			entrevistado.setEntrevistador(entrevistador);
		}
		else {
			System.out.println("No existe entrevistador");
			entrevistado.setEntrevistador(null);
		}

		System.out.println("Definido el entrevistador del objeto");
		
		String mensaje = "";
		String tipoMensaje = "";

		System.out.println("Comuna registro: " + entrevistado.getComuna().getId());
		System.out.println("Comuna registro: " + entrevistado.getComuna().getNombreComuna());
		
		System.out.println("ID: " + entrevistado.getIdEntrevistado());
		System.out.println("Fecha ingreso: " + entrevistado.getFechaIngreso());
		System.out.println("Run: " + entrevistado.getRun());
		System.out.println("Nombres: " + entrevistado.getNombres());
		System.out.println("Cliente: " + entrevistado.getCliente().getNombreCliente());
		
		entrevistado = Util.transformarTextos(entrevistado);
		
		if (entrevistadoServicio.editarEntrevistado(entrevistado)) 
		{
			mensaje = "El entrevistado ha sido modificado correctamente";
			tipoMensaje = "Ok";
			System.out.println("Edición de registro correcta");
		}
		else 
		{
			mensaje = "Ocurrió un error al editar el entrevistado";
			tipoMensaje = "Error";
			System.out.println("Error al editar el registro");
		}

		modelAndView.addObject("mensaje", mensaje);
		modelAndView.addObject("tipoMensaje", tipoMensaje);

		modelAndView.addObject("clientes",clienteServicio.obtenerClientes());
		modelAndView.addObject("cargos",cargoServicio.obtenerCargos());
		modelAndView.addObject("canales",canalServicio.obtenerCanales());
		modelAndView.addObject("reclutadores",reclutadorServicio.obtenerReclutadores());
		modelAndView.addObject("regiones",regionServicio.obtenerRegiones());
		modelAndView.addObject("instalaciones",instalacionServicio.obtenerInstalaciones());
		modelAndView.addObject("entrevistadores",entrevistadorServicio.obtenerEntrevistadores());
		//modelAndView.addObject("estados",Util.getEstados());
		modelAndView.addObject("estados",estadoServicio.obtenerEstados());
		modelAndView.addObject("validados",validadoServicio.obtenerValidados());

		modelAndView.addObject("periodos",periodoServicio.obtenerPeriodos());
		modelAndView.addObject("servicios",servicioServicio.obtenerServicios());
		modelAndView.addObject("contactados",contactadoServicio.obtenerContactados());
		modelAndView.addObject("presentaciones",presentacionServicio.obtenerPresentaciones());
		modelAndView.addObject("nacionalidades",nacionalidadServicio.obtenerNacionalidades());
		modelAndView.addObject("previsiones",previsionServicio.obtenerPrevisiones());
		modelAndView.addObject("saludes",saludServicio.obtenerSaludes());
		modelAndView.addObject("seguroscovid",seguroCovidServicio.obtenerSegurosCovid());
		modelAndView.addObject("tiposcuenta",tipoCuentaServicio.obtenerTiposCuenta());
		modelAndView.addObject("bancos",bancoServicio.obtenerBancos());
		modelAndView.addObject("tallas",tallaServicio.obtenerTallas());
		modelAndView.addObject("parentescos",parentescoServicio.obtenerParentescos());

		modelAndView.addObject("idregionselec", idregionselec);
		modelAndView.addObject("idcomunaselec", idcomunaselec);
		modelAndView.addObject("idestadoselec", idestadoselec);
		modelAndView.addObject("listacomunas", listacomunas);
		modelAndView.addObject("fechaIngresoStr", fechaIngresoStr);
		modelAndView.addObject("fechaEstadoStr", fechaEstadoStr);
		modelAndView.addObject("fechaContratacionStr", fechaContratacionStr);
		modelAndView.addObject("fechaNacimientoStr", fechaNacimientoStr);
		modelAndView.addObject("edadStr", edadStr);

		modelAndView.addObject("idregcontactoselec", idregcontactoselec);
		modelAndView.addObject("idcomcontactoselec", idcomcontactoselec);
		modelAndView.addObject("listacomcontacto", listacomcontacto);
		
		modelAndView.addObject("idinstalacionselec", idinstalacionselec);
		modelAndView.addObject("identrevistadorselec", identrevistadorselec);

		modelAndView.addObject("entrevistado", entrevistado);

		System.out.println("Envio datos al modelo");

		System.out.println("Finaliza la edición de un registro de entrevista");
		
		return modelAndView;
	}

	@GetMapping("/cargamasiva")
	public ModelAndView registroMasivo() 
	{
		ModelAndView modelAndView = new ModelAndView("entrevistas/masivo");

		String mensajeNuevo = "";
		String tipoMensajeNuevo = "";

		String mensajeModificado = "";
		String tipoMensajeModificado = "";

		modelAndView.addObject("mensajeNuevo", mensajeNuevo);
		modelAndView.addObject("tipoMensajeNuevo", tipoMensajeNuevo);
		
		modelAndView.addObject("mensajeModificado", mensajeModificado);
		modelAndView.addObject("tipoMensajeModificado", tipoMensajeModificado);
		
		return modelAndView;
	}
	
	@PostMapping("/proccargabasica")
	public ModelAndView procesaCargaBasica(MultipartFile flCargaBasica, 
			HttpServletRequest request) 
	{
		ModelAndView modelAndView = new ModelAndView("entrevistas/rescargabasica");

        List<Entrevistado> entModificados = new ArrayList<Entrevistado>();
        List<Entrevistado> entNuevos = new ArrayList<Entrevistado>();
        List<EntrevistadoVista> entErroneos = new ArrayList<EntrevistadoVista>();

        EntrevistadoHashmap lentrevistados = new EntrevistadoHashmap();
        lentrevistados.llenar(entrevistadoServicio.obtenerEntrevistados());

        //ReclutadorHashmap lreclutadores = new ReclutadorHashmap();
        //lreclutadores.llenar(reclutadorServicio.obtenerReclutadores());

        UsuarioHashmap lusuarios = new UsuarioHashmap();
        lusuarios.llenar(usuarioServicio.obtenerUsuarios());

        ClienteHashmap lclientes = new ClienteHashmap();
        lclientes.llenar(clienteServicio.obtenerClientes());

        CargoHashmap lcargos = new CargoHashmap();
        lcargos.llenar(cargoServicio.obtenerCargos());

        ComunaHashmap lcomunas = new ComunaHashmap();
        lcomunas.llenar(comunaServicio.obtenerComunas());

        CanalHashmap lcanales = new CanalHashmap();
        lcanales.llenar(canalServicio.obtenerCanales());

        int regTotales = 0;
        int regAntiguos = 0;
        int regNuevos = 0;
        int regErroneos = 0;
        int regBlancos = 0;
        //int regSinModificar = 0;
        //int valorNoExiste = 0;

		String strFechaIngreso = "";
		//String strReclutador = "";
		String strUsuario = "";
		String run = "";
		String strCliente = "";
		String nombres = "";
		String apPaterno = "";
		String apMaterno = "";
		String telefono = "";
		String obsRegistro = "";
		String strCargo = "";
		String strComuna = "";
		String strCanal = "";
		
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(flCargaBasica.getInputStream());
    		XSSFSheet sheet = workbook.getSheetAt(0);
    		
    		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    		cl.puertoesperanza.entrevistassa.modelo.User usuarioAcceso = usuarioServicio.obtenerUsuarioPorNombre(user.getUsername());

    		String roluser = "ROLE_USER";
    		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    		
    		if (auth.getAuthorities().toString().contains("ROLE_ADMIN")) {
    			roluser = "ROLE_ADMIN";
    		}
    		else if (auth.getAuthorities().toString().contains("ROLE_SUPERADMIN")) {
    			roluser = "ROLE_SUPERADMIN";
    		}

    		for(int i=1; i<sheet.getPhysicalNumberOfRows();i++) {
    			regTotales++;
				XSSFRow row = sheet.getRow(i);

				//System.out.println("Vamos en la fila: " + i);
				//int entrevistadoId = Math.round(Float.parseFloat(row.getCell(0).toString()));
				
				if (row.getCell(3) != null) {
					run = row.getCell(3).toString().trim();						
					if (run.trim().length() == 0) {
						regBlancos++;
						continue;
					}
				}
				else {
					run = "";
					regBlancos++;
					continue;
				}
				
				if (row.getCell(1) != null)
					strFechaIngreso = row.getCell(1).toString().trim();
				else
					strFechaIngreso = "";
				
				/*if (row.getCell(2) != null)
					strReclutador = row.getCell(2).toString().trim();
				else
					strReclutador = "";*/
				
				if (row.getCell(2) != null)
					strUsuario = row.getCell(2).toString().trim();
				else
					strUsuario = "";
				
				if (row.getCell(4) != null)
					strCliente = row.getCell(4).toString().trim();
				else
					strCliente = "";
				
				if (row.getCell(5) != null)
					nombres = row.getCell(5).toString().trim();
				else
					nombres = "";
				
				if (row.getCell(6) != null)
					apPaterno = row.getCell(6).toString().trim();
				else
					apPaterno = "";
				
				if (row.getCell(7) != null)
					apMaterno = row.getCell(7).toString().trim();
				else
					apMaterno = "";
				
				if (row.getCell(8) != null)
					telefono = row.getCell(8).toString().trim();
				else
					telefono = "";
				
				if (row.getCell(9) != null)
					obsRegistro = row.getCell(9).toString().trim();
				else
					obsRegistro = "";
				
				if (row.getCell(10) != null)
					strCargo = row.getCell(10).toString().trim();
				else
					strCargo = "";
				
				if (row.getCell(11) != null)
					strComuna = row.getCell(11).toString().trim();
				else
					strComuna = "";
				
				if (row.getCell(12) != null)
					strCanal = row.getCell(12).toString().trim();
				else
					strCanal = "";
				
				Date fechaIngreso = new Date();
				//SimpleDateFormat ft = new SimpleDateFormat ("dd-MMM-yyyy");
				SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy");
				
				try 
				{
					if (strFechaIngreso.length() > 0)
						fechaIngreso = ft.parse(strFechaIngreso);
				}
				catch(ParseException e) 
				{
					System.out.println("Imposible transformar fecha " + strFechaIngreso + " usando formato " + ft);
				}

				boolean registroOk = true;
				Entrevistado entr = new Entrevistado();
				entr = lentrevistados.busca(run);

				if (entr != null) {
					
					entr.setFechaIngreso(fechaIngreso);
					
					if (!entr.getUsuario().getUsername().equals(user.getUsername()) && roluser.equals("ROLE_USER")) {
						registroOk = false;
					}
					
					/*if (lreclutadores.busca(strReclutador) != null)
						entr.setReclutador(lreclutadores.busca(strReclutador));
					else
						registroOk = false;*/
					
					if (roluser.equals("ROLE_USER")) {
						if (entr.getUsuario().getUsername().equals(user.getUsername())) {
							entr.setUsuario(usuarioAcceso);
						}
						else {
							registroOk = false;
						}
					}
					else {
						if (lusuarios.busca(strUsuario) != null)
							entr.setUsuario(lusuarios.busca(strUsuario));
						else
							registroOk = false;
					}

					if (Util.validarRut(run)) {
						if (Util.formatear(run).equals(run)) {
							entr.setRun(run);							
						}
						else {
							registroOk = false;
							System.out.println("El run " + run + " no tiene el formato esperado");
						}
					}
					else {
						registroOk = false;
						System.out.println("El run " + run + " no es válido");
					}
					
					if (lclientes.busca(strCliente) != null)
						entr.setCliente(lclientes.busca(strCliente));
					else
						registroOk = false;
					
					if (nombres.length() > 0)
						entr.setNombres(nombres);
					else
						registroOk = false;
					
					if (apPaterno.length() > 0)
						entr.setApPaterno(apPaterno);
					else
						registroOk = false;
					
					if (apMaterno.length() > 0)
						entr.setApMaterno(apMaterno);
					else
						entr.setApMaterno(null);
					
					if (lcomunas.busca(strComuna) != null)
						entr.setComuna(lcomunas.busca(strComuna));
					else
						registroOk = false;
					
					if (telefono.length() > 0)
						entr.setTelefono(telefono);
					else
						entr.setTelefono(null);
					
					if (lcanales.busca(strCanal) != null)
						entr.setCanal(lcanales.busca(strCanal));
					else
						registroOk = false;
					
					if (lcargos.busca(strCargo) != null)
						entr.setCargo(lcargos.busca(strCargo));
					else
						registroOk = false;
					
					/*if (lreclutadores.busca(strReclutador) != null)
						entr.setReclutador(lreclutadores.busca(strReclutador));
					else
						registroOk = false;*/

					if (obsRegistro.length() > 0)
						entr.setObservacionRegistro(obsRegistro);
					else
						entr.setObservacionRegistro(null);
					
					if (registroOk) {
						regAntiguos++;
						entModificados.add(entr);
					}
				}
				else {
					
					entr = new Entrevistado();
					entr.setFechaIngreso(fechaIngreso);
					entr.setUsuario(usuarioAcceso);
					
					/*if (lreclutadores.busca(strReclutador) != null)
						entr.setReclutador(lreclutadores.busca(strReclutador));
					else
						registroOk = false;*/
					
					if (roluser.equals("ROLE_USER")) {
						entr.setUsuario(usuarioAcceso);
					}
					else {
						if (lusuarios.busca(strUsuario) != null)
							entr.setUsuario(lusuarios.busca(strUsuario));
						else
							registroOk = false;
					}
					
					if (Util.validarRut(run)) {
						if (Util.formatear(run).equals(run)) {
							entr.setRun(run);							
						}
						else {
							registroOk = false;
							System.out.println("El run " + run + " no tiene el formato esperado");
						}
					}
					else {
						registroOk = false;
						System.out.println("El run " + run + " no es válido");
					}

					if (lclientes.busca(strCliente) != null)
						entr.setCliente(lclientes.busca(strCliente));
					else
						registroOk = false;
					
					if (nombres.length() > 0)
						entr.setNombres(nombres);
					else
						registroOk = false;
					
					if (apPaterno.length() > 0)
						entr.setApPaterno(apPaterno);
					else
						registroOk = false;
					
					if (apMaterno.length() > 0)
						entr.setApMaterno(apMaterno);
					else
						entr.setApMaterno(null);
					
					if (lcomunas.busca(strComuna) != null)
						entr.setComuna(lcomunas.busca(strComuna));
					else
						registroOk = false;
					
					if (telefono.length() > 0)
						entr.setTelefono(telefono);
					else
						entr.setTelefono(null);
					
					if (lcanales.busca(strCanal) != null)
						entr.setCanal(lcanales.busca(strCanal));
					else
						registroOk = false;
					
					if (lcargos.busca(strCargo) != null)
						entr.setCargo(lcargos.busca(strCargo));
					else
						registroOk = false;
					
					/*if (lreclutadores.busca(strReclutador) != null)
						entr.setReclutador(lreclutadores.busca(strReclutador));
					else
						registroOk = false;*/

					if (obsRegistro.length() > 0)
						entr.setObservacionRegistro(obsRegistro);
					else
						entr.setObservacionRegistro(null);

					if (registroOk) {
						regNuevos++;
						entNuevos.add(entr);
					}
				}
				
				if (!registroOk) {
					EntrevistadoVista entrx = new EntrevistadoVista();
					entrx.setFechaIngreso(strFechaIngreso);
					//entrx.setNombreReclutador(strReclutador);
					entrx.setRun(run);
					entrx.setNombreCliente(strCliente);
					entrx.setNombres(nombres);
					entrx.setApPaterno(apPaterno);
					entrx.setApMaterno(apMaterno);
					entrx.setTelefono(telefono);
					entrx.setObservacionRegistro(obsRegistro);
					entrx.setNombreCargo(strCargo);
					entrx.setNombreComuna(strComuna);
					entrx.setNombreCanal(strCanal);
					entrx.setUsername(strUsuario);
					entErroneos.add(entrx);
					regErroneos++;
				}
			}
    		
    		//System.out.println("Registros no modificados: " + regSinModificar);
    		//System.out.println("Estado no existente: " + valorNoExiste);
    		
    		//modelAndView.addObject("runNoExiste",runNoExiste);
    		//modelAndView.addObject("runModificados",runModificados);
    		//modelAndView.addObject("regSinModificar",regSinModificar);
    		//modelAndView.addObject("valorNoExiste",valorNoExiste);
    		
    		//modelAndView.addObject("lvalidados",lvalidados);
    		
    		//session.setAttribute("listaModificados", entModificados);
    		//request.getSession().setAttribute("listaModificados",entModificados);
        }
        catch(IOException e) {
        	System.out.println("Error al leer la planilla");
        }

		System.out.println("Registros totales: " + regTotales);
		System.out.println("Registros antiguos: " + regAntiguos);
		System.out.println("Registros nuevos: " + regNuevos);
		System.out.println("Registros erróneos: " + regErroneos);
		System.out.println("Registros en blanco: " + regBlancos);

		modelAndView.addObject("regTotales",regTotales);
		modelAndView.addObject("regAntiguos",regAntiguos);
		modelAndView.addObject("regNuevos",regNuevos);
		modelAndView.addObject("regErroneos",regErroneos);
		modelAndView.addObject("regBlancos",regBlancos);

		modelAndView.addObject("entModificados",entModificados);
		modelAndView.addObject("entNuevos",entNuevos);
		modelAndView.addObject("entErroneos",entErroneos);

		request.getSession().setAttribute("listaModificados",entModificados);
		request.getSession().setAttribute("listaNuevos",entNuevos);
		request.getSession().setAttribute("listaErroneos",entErroneos);

		return modelAndView;
	}

	@PostMapping("/fincarga")
	public ModelAndView finCarga(HttpSession session) 
	{
		ModelAndView modelAndView = new ModelAndView("entrevistas/masivo");

		String mensajeNuevo = "";
		String tipoMensajeNuevo = "";

		String mensajeModificado = "";
		String tipoMensajeModificado = "";
		
		@SuppressWarnings("unchecked")
		List<Entrevistado> entrevistasNuevas = (List<Entrevistado>) session.getAttribute("listaNuevos");
		
		if (entrevistasNuevas != null) {

			if (entrevistadoServicio.editarEntrevistadoMasivo(entrevistasNuevas)) {
				mensajeNuevo = "La inserción de registros nuevos se realizó de manera exitosa";
				tipoMensajeNuevo = "Ok";
			}
			else {
				mensajeNuevo = "Ocurrió un error al ingresar masivamente los registros";
				tipoMensajeNuevo = "Error";				
			}
		}
		else {
			mensajeNuevo = "No se pudo recuperar el listado de elementos a ingresar";
			tipoMensajeNuevo = "Error";
		}
		
		@SuppressWarnings("unchecked")
		List<Entrevistado> entrevistasModificadas = (List<Entrevistado>) session.getAttribute("listaModificados");
		
		if (entrevistasModificadas != null) {

			if (entrevistadoServicio.editarEntrevistadoMasivo(entrevistasModificadas)) {
				mensajeModificado = "La modificación de registros se realizó de manera exitosa";
				tipoMensajeModificado = "Ok";
			}
			else {
				mensajeModificado = "Ocurrió un error al modificar masivamente los registros";
				tipoMensajeModificado = "Error";
			}
		}
		else {
			mensajeModificado = "No se pudo recuperar el listado de elementos a modificar";
			tipoMensajeModificado = "Error";
		}
		
		modelAndView.addObject("mensajeNuevo", mensajeNuevo);
		modelAndView.addObject("tipoMensajeNuevo", tipoMensajeNuevo);
		
		modelAndView.addObject("mensajeModificado", mensajeModificado);
		modelAndView.addObject("tipoMensajeModificado", tipoMensajeModificado);
		
		return modelAndView;
	}
	
	@PostMapping("/proccargacompleta")
	public ModelAndView procesaCargaCompleta(MultipartFile flCargaCompleta, 
			HttpServletRequest request) 
	{
		ModelAndView modelAndView = new ModelAndView("entrevistas/rescargacompleta");

        List<Entrevistado> entModificados = new ArrayList<Entrevistado>();
        List<Entrevistado> entNuevos = new ArrayList<Entrevistado>();
        List<EntrevistadoVista> entErroneos = new ArrayList<EntrevistadoVista>();

        EntrevistadoHashmap lentrevistados = new EntrevistadoHashmap();
        lentrevistados.llenar(entrevistadoServicio.obtenerEntrevistados());

        //ReclutadorHashmap lreclutadores = new ReclutadorHashmap();
        //lreclutadores.llenar(reclutadorServicio.obtenerReclutadores());
        
        UsuarioHashmap lusuarios = new UsuarioHashmap();
        lusuarios.llenar(usuarioServicio.obtenerUsuarios());

        ClienteHashmap lclientes = new ClienteHashmap();
        lclientes.llenar(clienteServicio.obtenerClientes());

        CargoHashmap lcargos = new CargoHashmap();
        lcargos.llenar(cargoServicio.obtenerCargos());

        ComunaHashmap lcomunas = new ComunaHashmap();
        lcomunas.llenar(comunaServicio.obtenerComunas());

        CanalHashmap lcanales = new CanalHashmap();
        lcanales.llenar(canalServicio.obtenerCanales());

        ValidadoHashmap lvalidados = new ValidadoHashmap();
        lvalidados.llenar(validadoServicio.obtenerValidados());

        EstadoHashmap lestados = new EstadoHashmap();
        lestados.llenar(estadoServicio.obtenerEstados());

        InstalacionHashmap linstalaciones = new InstalacionHashmap();
        linstalaciones.llenar(instalacionServicio.obtenerInstalaciones());

        PeriodoHashmap lperiodos = new PeriodoHashmap();
        lperiodos.llenar(periodoServicio.obtenerPeriodos());

        ServicioHashmap lservicios = new ServicioHashmap();
        lservicios.llenar(servicioServicio.obtenerServicios());

        ContactadoHashmap lcontactados = new ContactadoHashmap();
        lcontactados.llenar(contactadoServicio.obtenerContactados());

        PresentacionHashmap lpresentaciones = new PresentacionHashmap();
        lpresentaciones.llenar(presentacionServicio.obtenerPresentaciones());

        NacionalidadHashmap lnacionalidades = new NacionalidadHashmap();
        lnacionalidades.llenar(nacionalidadServicio.obtenerNacionalidades());
        
        PrevisionHashmap lprevisiones = new PrevisionHashmap();
        lprevisiones.llenar(previsionServicio.obtenerPrevisiones());
        
        SaludHashmap lsaludes = new SaludHashmap();
        lsaludes.llenar(saludServicio.obtenerSaludes());
        
        SeguroCovidHashmap lseguroscovid = new SeguroCovidHashmap();
        lseguroscovid.llenar(seguroCovidServicio.obtenerSegurosCovid());
        
        TipoCuentaHashmap ltiposcuenta = new TipoCuentaHashmap();
        ltiposcuenta.llenar(tipoCuentaServicio.obtenerTiposCuenta());
        
        BancoHashmap lbancos = new BancoHashmap();
        lbancos.llenar(bancoServicio.obtenerBancos());
        
        TallaHashmap ltallas = new TallaHashmap();
        ltallas.llenar(tallaServicio.obtenerTallas());

        EntrevistadorHashmap lentrevistadores = new EntrevistadorHashmap();
        lentrevistadores.llenar(entrevistadorServicio.obtenerEntrevistadores());

        ParentescoHashmap lparentescos = new ParentescoHashmap();
        lparentescos.llenar(parentescoServicio.obtenerParentescos());

        int regTotales = 0;
        int regAntiguos = 0;
        int regNuevos = 0;
        int regErroneos = 0;
        int regBlancos = 0;

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(flCargaCompleta.getInputStream());
    		XSSFSheet sheet = workbook.getSheetAt(0);

    		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    		cl.puertoesperanza.entrevistassa.modelo.User usuarioAcceso = usuarioServicio.obtenerUsuarioPorNombre(user.getUsername());

    		String roluser = "ROLE_USER";
    		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    		
    		if (auth.getAuthorities().toString().contains("ROLE_ADMIN")) {
    			roluser = "ROLE_ADMIN";
    		}
    		else if (auth.getAuthorities().toString().contains("ROLE_SUPERADMIN")) {
    			roluser = "ROLE_SUPERADMIN";
    		}

    		String strFechaIngreso = "";
    		//String strReclutador = "";
    		String strUsuario = "";
    		String run = "";
    		String strCliente = "";
    		String nombres = "";
    		String apPaterno = "";
    		String apMaterno = "";
    		String correoElectronico = "";
    		String telefono = "";
    		String obsRegistro = "";
    		
    		String empresa = "";
    		String strValidado = "";
    		String strFechaEstado = "";
    		String strEstado = "";
			String strInstalacion = "";
			String strCargo = "";
			//String strRegion = "";
			String strComuna = "";
			String strPeriodo = "";
			String strTipoServicio = "";

			String strFechaContratacion = "";
			String obsComplementaria = "";
			String strCanal = "";
			String strContactado = "";
			String strSePresenta = "";
			String obsContacto = "";
			String strNacionalidad = "";
			String strFechaNacimiento = "";
			//String strEdad = "";
			String strDireccion = "";

			String ciudad = "";
			String numeroDireccion = "";
			String strPrevision = "";
			String strSalud = "";
			String strSeguroCovid = "";
			String strTipoCuenta = "";
			String strBanco = "";
			String numeroCuenta = "";
			String calzado = "";
			String strPolera = "";

			String strPoleron = "";
			String strPantalon = "";
			String nombreContacto = "";
			String telefonoContacto = "";
			String parentescoContacto = "";
			String direccionContacto = "";
			//String strRegionContacto = "";
			String strComunaContacto = "";
			String strEntrevistador = "";

    		for(int i=1; i<sheet.getPhysicalNumberOfRows();i++) {
    			regTotales++;
				XSSFRow row = sheet.getRow(i);

				//Parte 1 de 5
				
				//int entrevistadoId = Math.round(Float.parseFloat(row.getCell(0).toString()));
				
				if (row.getCell(3) != null) {
					run = row.getCell(3).toString().trim();
					if (run.trim().length() == 0) {
						regBlancos++;
						continue;
					}
				}
				else {
					run = "";
				}

				if (row.getCell(1) != null)
					strFechaIngreso = row.getCell(1).toString().trim();
				else
					strFechaIngreso = "";

				/*if (row.getCell(2) != null)
					strReclutador = row.getCell(2).toString().trim();
				else
					strReclutador = "";*/

				if (row.getCell(2) != null)
					strUsuario = row.getCell(2).toString().trim();
				else
					strUsuario = "";

				if (row.getCell(4) != null)
					strCliente = row.getCell(4).toString().trim();
				else
					strCliente = "";
				
				if (row.getCell(5) != null)
					nombres = row.getCell(5).toString().trim();
				else
					nombres = "";
				
				if (row.getCell(6) != null)
					apPaterno = row.getCell(6).toString().trim();
				else
					apPaterno = "";
				
				if (row.getCell(7) != null)
					apMaterno = row.getCell(7).toString().trim();
				else
					apMaterno = "";
				
				if (row.getCell(8) != null)
					correoElectronico = row.getCell(8).toString().trim();
				else
					correoElectronico = "";
				
				if (row.getCell(9) != null)
					telefono = Util.limpiarCadena(row.getCell(9).toString().trim());
				else
					telefono = "";
				
				if (row.getCell(10) != null)
					obsRegistro = row.getCell(10).toString().trim();
				else
					obsRegistro = "";
				
				//Parte 2 de 5
				
				if (row.getCell(11) != null)
					empresa = row.getCell(11).toString().trim();
				else
					empresa = "";
				
				if (row.getCell(12) != null)
					strValidado = row.getCell(12).toString().trim();
				else
					strValidado = "";
				
				if (row.getCell(13) != null)
					strFechaEstado = row.getCell(13).toString().trim();
				else
					strFechaEstado = "";
				
				if (row.getCell(14) != null)
					strEstado = row.getCell(14).toString().trim();
				else
					strEstado = "";
				
				if (row.getCell(15) != null)
					strInstalacion = row.getCell(15).toString().trim();
				else
					strInstalacion = "";
				
				if (row.getCell(16) != null)
					strCargo = row.getCell(16).toString().trim();
				else
					strCargo = "";
				
				//La región se rescata desde la planilla, pero no se utiliza
				//String strRegion = row.getCell(17).toString().trim();
				
				if (row.getCell(18) != null)
					strComuna = row.getCell(18).toString().trim();
				else
					strComuna = "";
				
				if (row.getCell(19) != null)
					strPeriodo = Util.limpiarCadena(row.getCell(19).toString().trim());
				else
					strPeriodo = "";
				
				if (row.getCell(20) != null)
					strTipoServicio = row.getCell(20).toString().trim();
				else
					strTipoServicio = "";

				//Parte 3 de 5 
				
				if (row.getCell(21) != null)
					strFechaContratacion = row.getCell(21).toString().trim();
				else
					strFechaContratacion = "";
				
				if (row.getCell(22) != null)
					obsComplementaria = row.getCell(22).toString().trim();
				else
					obsComplementaria = "";
				
				if (row.getCell(23) != null)
					strCanal = row.getCell(23).toString().trim();
				else
					strCanal = "";
				
				if (row.getCell(24) != null)
					strContactado = row.getCell(24).toString().trim();
				else
					strContactado = "";
				
				if (row.getCell(25) != null)
					strSePresenta = row.getCell(25).toString().trim();
				else
					strSePresenta = "";
				
				if (row.getCell(26) != null)
					obsContacto = row.getCell(26).toString().trim();
				else
					obsContacto = "";
				
				if (row.getCell(27) != null)
					strNacionalidad = row.getCell(27).toString().trim();
				else
					strNacionalidad = "";
				
				if (row.getCell(28) != null)
					strFechaNacimiento = row.getCell(28).toString().trim();
				else
					strFechaNacimiento = "";
				
				//La edad de la persona se recupera, pero no se utiliza
				//String strEdad = row.getCell(29).toString().trim();
				
				if (row.getCell(30) != null)
					strDireccion = row.getCell(30).toString().trim();
				else
					strDireccion = "";

				//Parte 4 de 5
				
				if (row.getCell(31) != null)
					ciudad = row.getCell(31).toString().trim();
				else
					ciudad = "";

				if (row.getCell(32) != null)
					numeroDireccion = Util.limpiarCadena(row.getCell(32).toString().trim());
				else
					numeroDireccion = "";

				if (row.getCell(33) != null)
					strPrevision = row.getCell(33).toString().trim();
				else
					strPrevision = "";

				if (row.getCell(34) != null)
					strSalud = row.getCell(34).toString().trim();
				else
					strSalud = "";

				if (row.getCell(35) != null)
					strSeguroCovid = row.getCell(35).toString().trim();
				else
					strSeguroCovid = "";

				if (row.getCell(36) != null)
					strTipoCuenta = row.getCell(36).toString().trim();
				else
					strTipoCuenta = "";

				if (row.getCell(37) != null)
					strBanco = row.getCell(37).toString().trim();
				else
					strBanco = "";

				if (row.getCell(38) != null)
					numeroCuenta = Util.limpiarCadena(row.getCell(38).toString().trim());
				else
					numeroCuenta = "";

				if (row.getCell(39) != null)
					calzado = Util.limpiarCadena(row.getCell(39).toString().trim());
				else
					calzado = "";

				if (row.getCell(40) != null)
					strPolera = row.getCell(40).toString().trim();
				else
					strPolera = "";

				//Parte 5 de 5 
				
				if (row.getCell(41) != null)
					strPoleron = row.getCell(41).toString().trim();
				else
					strPoleron = "";
				
				if (row.getCell(42) != null)
					strPantalon = row.getCell(42).toString().trim();
				else
					strPantalon = "";
				
				if (row.getCell(43) != null)
					nombreContacto = row.getCell(43).toString().trim();
				else
					nombreContacto = "";
				
				if (row.getCell(44) != null)
					telefonoContacto = Util.limpiarCadena(row.getCell(44).toString().trim());
				else
					telefonoContacto = "";
				
				if (row.getCell(45) != null)
					parentescoContacto = row.getCell(45).toString().trim();
				else
					parentescoContacto = "";
				
				if (row.getCell(46) != null)
					direccionContacto = row.getCell(46).toString().trim();
				else
					direccionContacto = "";
				
				//La región de contacto se recupera, pero no se utiliza
				//String strRegionContacto = row.getCell(47).toString().trim();
				
				if (row.getCell(48) != null)
					strComunaContacto = row.getCell(48).toString().trim();
				else
					strComunaContacto = "";
				
				if (row.getCell(49) != null)
					strEntrevistador = row.getCell(49).toString().trim();
				else
					strEntrevistador = "";
				
				Date fechaIngreso = new Date();
				//SimpleDateFormat ft = new SimpleDateFormat ("dd-MMM-yyyy");
				SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy");
				
				try 
				{
					if (strFechaIngreso.length() > 0)
						fechaIngreso = ft.parse(strFechaIngreso);
				}
				catch(ParseException e) 
				{
					System.out.println("Imposible transformar fecha " + strFechaIngreso + " usando formato " + ft);
				}

				boolean registroOk = true;
				Entrevistado entr = new Entrevistado();
				entr = lentrevistados.busca(run);

				if (entr != null) {
					
					/*if (!entr.getUsuario().getUsername().equals(user.getUsername()) && roluser.equals("ROLE_USER")) {
						registroOk = false;
					}*/

					//Parte 1 de 5 (11 campos)
					
					entr.setFechaIngreso(fechaIngreso);
					
					/*if (lreclutadores.busca(strReclutador) != null)
						entr.setReclutador(lreclutadores.busca(strReclutador));
					else
						registroOk = false;*/
					
					if (roluser.equals("ROLE_USER")) {
						if (entr.getUsuario().getUsername().equals(user.getUsername())) {
							entr.setUsuario(usuarioAcceso);
						}
						else {
							registroOk = false;
						}
					}
					else {
						if (lusuarios.busca(strUsuario) != null)
							entr.setUsuario(lusuarios.busca(strUsuario));
						else
							registroOk = false;
					}
					
					//No se actualiza el RUN de un registro ya existente
					/*if (Util.validarRut(run)) {
						if (Util.formatear(run).equals(run)) {
							entr.setRun(run);							
						}
						else {
							registroOk = false;
							System.out.println("El run " + run + " no tiene el formato esperado");
						}
					}
					else {
						registroOk = false;
						System.out.println("El run " + run + " no es válido");
					}*/
					
					if (lclientes.busca(strCliente) != null)
						entr.setCliente(lclientes.busca(strCliente));
					else
						registroOk = false;
					
					if (nombres.length() > 0)
						entr.setNombres(nombres);
					else
						registroOk = false;
					
					if (apPaterno.length() > 0)
						entr.setApPaterno(apPaterno);
					else
						registroOk = false;
					
					if (apMaterno.length() > 0)
						entr.setApMaterno(apMaterno);
					else
						entr.setApMaterno(null);

					if (correoElectronico.length() > 0)
						entr.setCorreoElectronico(correoElectronico);
					else
						entr.setCorreoElectronico(null);
					
					if (telefono.length() > 0)
						entr.setTelefono(telefono);
					else
						entr.setTelefono(null);

					if (obsRegistro.length() > 0)
						entr.setObservacionRegistro(obsRegistro);
					else
						entr.setObservacionRegistro(null);

					//Parte 2 de 5 (10 campos)
					
					if (empresa.length() > 0)
						entr.setEmpresa(empresa);
					else
						entr.setEmpresa(null);

					if (strValidado.length() == 0)
						entr.setValidado(null);
					else if (lvalidados.busca(strValidado) != null)
						entr.setValidado(lvalidados.busca(strValidado).getId());
					else
						registroOk = false;

					if (strFechaEstado.length() == 0) {
						entr.setFechaEstado(null);
					} 
					else {
						boolean bFechaEstado = false;
						Date fechaEstado = new Date();
						try {
							fechaEstado = ft.parse(strFechaEstado);
							bFechaEstado = true;
						}
						catch(Exception e) {
							System.out.println("No se pudo convertir la fecha de estado " + strFechaEstado);
						}
						
						if (bFechaEstado)
							entr.setFechaEstado(fechaEstado);
						else
							registroOk = false;
					}
					
					if (strEstado.length() == 0)
						entr.setEstado(null);
					else if (lestados.busca(strEstado) != null)
						entr.setEstado(lestados.busca(strEstado).getId());
					else
						registroOk = false;

					if (strInstalacion.length() == 0)
						entr.setInstalacion(null);
					else if (linstalaciones.busca(strInstalacion) != null)
						entr.setInstalacion(linstalaciones.busca(strInstalacion));
					else
						registroOk = false;
					
					if (lcargos.busca(strCargo) != null)
						entr.setCargo(lcargos.busca(strCargo));
					else
						registroOk = false;

					//Región se rescata, pero no se utiliza
					
					if (lcomunas.busca(strComuna) != null)
						entr.setComuna(lcomunas.busca(strComuna));
					else
						registroOk = false;
					
					if (strPeriodo.length() == 0) {
						entr.setPeriodo(null);
					}
					else if (lperiodos.busca(strPeriodo) != null) {
						entr.setPeriodo(lperiodos.busca(strPeriodo).getId());
					}
					else {
						System.out.println("Periodo: " + strPeriodo);
						System.out.println(lperiodos.toString());
						registroOk = false;
					}
					
					if (strTipoServicio.length() == 0)
						entr.setTipoServicio(null);
					else if (lservicios.busca(strTipoServicio) != null)
						entr.setTipoServicio(lservicios.busca(strTipoServicio).getId());
					else
						registroOk = false;
					
					//Parte 3 de 5 (10 campos)

					if (strFechaContratacion.length() == 0) {
						entr.setFechaContratacion(null);
					} 
					else {
						boolean bFechaContratacion = false;
						Date fechaContratacion = new Date();
						try {
							fechaContratacion = ft.parse(strFechaContratacion);
							bFechaContratacion = true;
						}
						catch(Exception e) {
							System.out.println("No se pudo convertir la fecha de contratacion " + strFechaContratacion);
						}
						
						if (bFechaContratacion)
							entr.setFechaContratacion(fechaContratacion);
						else
							registroOk = false;
					}

					if (obsComplementaria.length() > 0)
						entr.setObservacionComplementaria(obsComplementaria);
					else
						entr.setObservacionComplementaria(null);

					if (lcanales.busca(strCanal) != null)
						entr.setCanal(lcanales.busca(strCanal));
					else
						registroOk = false;
					
					if (strContactado.length() == 0) {
						entr.setContactado(null);
					}
					else if (lcontactados.busca(strContactado) != null) {
						entr.setContactado(lcontactados.busca(strContactado).getId());
					}
					else {
						registroOk = false;
					}

					if (strSePresenta.length() == 0) {
						entr.setPresentacion(null);
					}
					else if (lpresentaciones.busca(strSePresenta) != null) {
						entr.setPresentacion(lpresentaciones.busca(strSePresenta).getId());
					}
					else {
						registroOk = false;
					}
					
					if (obsContacto.length() > 0)
						entr.setObservacionContacto(obsContacto);
					else
						entr.setObservacionContacto(null);
					
					if (strNacionalidad.length() == 0) {
						entr.setNacionalidad(null);
					}
					else if (lnacionalidades.busca(strNacionalidad) != null) {
						entr.setNacionalidad(lnacionalidades.busca(strNacionalidad).getId());
					}
					else {
						registroOk = false;
					}

					if (strFechaNacimiento.length() == 0) {
						entr.setFechaNacimiento(null);
					} 
					else {
						boolean bFechaNacimiento = false;
						Date fechaNacimiento = new Date();
						try {
							fechaNacimiento = ft.parse(strFechaNacimiento);
							bFechaNacimiento = true;
						}
						catch(Exception e) {
							System.out.println("No se pudo convertir la fecha de nacimiento " + strFechaNacimiento);
						}
						
						if (bFechaNacimiento)
							entr.setFechaNacimiento(fechaNacimiento);
						else
							registroOk = false;
					}

					//La edad se rescata pero no se utiliza
					
					if (strDireccion.length() > 0)
						entr.setDireccion(strDireccion);
					else
						entr.setDireccion(null);

					//Parte 4 de 5 (10 campos)

					if (ciudad.length() > 0)
						entr.setCiudad(ciudad);
					else
						entr.setCiudad(null);

					if (numeroDireccion.length() > 0)
						entr.setNumeroDireccion(numeroDireccion);
					else
						entr.setNumeroDireccion(null);

					if (strPrevision.length() == 0) {
						entr.setPrevision(null);
					}
					else if (lprevisiones.busca(strPrevision) != null) {
						entr.setPrevision(lprevisiones.busca(strPrevision).getId());
					}
					else {
						registroOk = false;
					}

					if (strSalud.length() == 0) {
						entr.setSalud(null);
					}
					else if (lsaludes.busca(strSalud) != null) {
						entr.setSalud(lsaludes.busca(strSalud).getId());
					}
					else {
						registroOk = false;
					}

					if (strSeguroCovid.length() == 0) {
						entr.setSeguroCovid(null);
					}
					else if (lseguroscovid.busca(strSeguroCovid) != null) {
						entr.setSeguroCovid(lseguroscovid.busca(strSeguroCovid).getId());
					}
					else {
						registroOk = false;
					}

					if (strTipoCuenta.length() == 0) {
						entr.setTipoCuenta(null);
					}
					else if (ltiposcuenta.busca(strTipoCuenta) != null) {
						entr.setTipoCuenta(ltiposcuenta.busca(strTipoCuenta).getId());
					}
					else {
						registroOk = false;
					}

					if (strBanco.length() == 0) {
						entr.setBancoCuenta(null);
					}
					else if (lbancos.busca(strBanco) != null) {
						entr.setBancoCuenta(lbancos.busca(strBanco).getId());
					}
					else {
						registroOk = false;
					}

					if (numeroCuenta.length() > 0)
						entr.setNumeroCuenta(numeroCuenta);
					else
						entr.setNumeroCuenta(null);

					if (calzado.length() > 0)
						entr.setCalzado(calzado);
					else
						entr.setCalzado(null);

					if (strPolera.length() == 0) {
						entr.setPolera(null);
					}
					else if (ltallas.busca(strPolera) != null) {
						entr.setPolera(ltallas.busca(strPolera).getId());
					}
					else {
						registroOk = false;
					}
					
					//Parte 5 de 5
					
					if (strPoleron.length() == 0) {
						entr.setPoleron(null);
					}
					else if (ltallas.busca(strPoleron) != null) {
						entr.setPoleron(ltallas.busca(strPoleron).getId());
					}
					else {
						registroOk = false;
					}

					if (strPantalon.length() == 0) {
						entr.setPantalon(null);
					}
					else if (ltallas.busca(strPantalon) != null) {
						entr.setPantalon(ltallas.busca(strPantalon).getId());
					}
					else {
						registroOk = false;
					}

					if (nombreContacto.length() > 0)
						entr.setNombreContacto(nombreContacto);
					else
						entr.setNombreContacto(null);

					if (telefonoContacto.length() > 0)
						entr.setTelefonoContacto(telefonoContacto);
					else
						entr.setTelefonoContacto(null);

					if (parentescoContacto.length() == 0) {
						entr.setParentezcoContacto(null);
					}
					else if (lparentescos.busca(parentescoContacto) != null) {
						entr.setParentezcoContacto(lparentescos.busca(parentescoContacto).getId());
					}
					else {
						registroOk = false;
					}

					if (direccionContacto.length() > 0)
						entr.setDireccionContacto(direccionContacto);
					else
						entr.setDireccionContacto(null);
					
					//La región de contacto se rescata, pero no se utiliza

					if (strComunaContacto.length() == 0) {
						entr.setComunaContacto(null);
					}
					else if (lcomunas.busca(strComunaContacto) != null) {
						entr.setComunaContacto(lcomunas.busca(strComunaContacto));
					}
					else {
						registroOk = false;
					}

					if (strEntrevistador.length() == 0) {
						entr.setEntrevistador(null);
					}
					else if (lentrevistadores.busca(strEntrevistador) != null) {
						entr.setEntrevistador(lentrevistadores.busca(strEntrevistador));
					}
					else {
						registroOk = false;
					}

					//Verificación de registro modificado
					
					if (registroOk) {
						regAntiguos++;
						entModificados.add(entr);
					}
				}
				else {
					
					entr = new Entrevistado();
					
					//Parte 1 de 4 (11 campos)
					
					entr.setFechaIngreso(fechaIngreso);
					//entr.setUsuario(usuarioAcceso);
					
					/*if (lreclutadores.busca(strReclutador) != null)
						entr.setReclutador(lreclutadores.busca(strReclutador));
					else
						registroOk = false;*/
					
					if (roluser.equals("ROLE_USER")) {
						entr.setUsuario(usuarioAcceso);
					}
					else {
						if (lusuarios.busca(strUsuario) != null)
							entr.setUsuario(lusuarios.busca(strUsuario));
						else
							registroOk = false;
					}

					if (Util.validarRut(run)) {
						if (Util.formatear(run).equals(run)) {
							entr.setRun(run);							
						}
						else {
							registroOk = false;
							System.out.println("El run " + run + " no tiene el formato esperado");
						}
					}
					else {
						registroOk = false;
						System.out.println("El run " + run + " no es válido");
					}
					
					if (lclientes.busca(strCliente) != null)
						entr.setCliente(lclientes.busca(strCliente));
					else
						registroOk = false;
					
					if (nombres.length() > 0)
						entr.setNombres(nombres);
					else
						registroOk = false;
					
					if (apPaterno.length() > 0)
						entr.setApPaterno(apPaterno);
					else
						registroOk = false;
					
					if (apMaterno.length() > 0)
						entr.setApMaterno(apMaterno);
					else
						entr.setApMaterno(null);

					if (correoElectronico.length() > 0)
						entr.setCorreoElectronico(correoElectronico);
					else
						entr.setCorreoElectronico(null);
					
					if (telefono.length() > 0)
						entr.setTelefono(telefono);
					else
						entr.setTelefono(null);

					if (obsRegistro.length() > 0)
						entr.setObservacionRegistro(obsRegistro);
					else
						entr.setObservacionRegistro(null);

					//Parte 2 de 4 (10 campos)
					
					if (empresa.length() > 0)
						entr.setEmpresa(empresa);
					else
						entr.setEmpresa(null);

					if (strValidado.length() == 0)
						entr.setValidado(null);
					else if (lvalidados.busca(strValidado) != null)
						entr.setValidado(lvalidados.busca(strValidado).getId());
					else
						registroOk = false;

					if (strFechaEstado.length() == 0) {
						entr.setFechaEstado(null);
					} 
					else {
						boolean bFechaEstado = false;
						Date fechaEstado = new Date();
						try {
							fechaEstado = ft.parse(strFechaEstado);
							bFechaEstado = true;
						}
						catch(Exception e) {
							System.out.println("No se pudo convertir la fecha de estado " + strFechaEstado);
						}
						
						if (bFechaEstado)
							entr.setFechaEstado(fechaEstado);
						else
							registroOk = false;
					}
					
					if (strEstado.length() == 0)
						entr.setEstado(null);
					else if (lestados.busca(strEstado) != null)
						entr.setEstado(lestados.busca(strEstado).getId());
					else
						registroOk = false;

					if (strInstalacion.length() == 0)
						entr.setInstalacion(null);
					else if (linstalaciones.busca(strInstalacion) != null)
						entr.setInstalacion(linstalaciones.busca(strInstalacion));
					else
						registroOk = false;
					
					if (lcargos.busca(strCargo) != null)
						entr.setCargo(lcargos.busca(strCargo));
					else
						registroOk = false;

					//Región se rescata, pero no se utiliza
					
					if (lcomunas.busca(strComuna) != null)
						entr.setComuna(lcomunas.busca(strComuna));
					else
						registroOk = false;
					
					if (strPeriodo.length() == 0)
						entr.setPeriodo(null);
					else if (lperiodos.busca(strPeriodo) != null)
						entr.setPeriodo(lperiodos.busca(strPeriodo).getId());
					else
						registroOk = false;
					
					if (strTipoServicio.length() == 0)
						entr.setTipoServicio(null);
					else if (lservicios.busca(strTipoServicio) != null)
						entr.setTipoServicio(lservicios.busca(strTipoServicio).getId());
					else
						registroOk = false;
					
					//Parte 3 de 5 (10 campos)

					if (strFechaContratacion.length() == 0) {
						entr.setFechaContratacion(null);
					} 
					else {
						boolean bFechaContratacion = false;
						Date fechaContratacion = new Date();
						try {
							fechaContratacion = ft.parse(strFechaContratacion);
							bFechaContratacion = true;
						}
						catch(Exception e) {
							System.out.println("No se pudo convertir la fecha de contratacion " + strFechaContratacion);
						}
						
						if (bFechaContratacion)
							entr.setFechaContratacion(fechaContratacion);
						else
							registroOk = false;
					}

					if (obsComplementaria.length() > 0)
						entr.setObservacionComplementaria(obsComplementaria);
					else
						entr.setObservacionComplementaria(null);

					if (lcanales.busca(strCanal) != null)
						entr.setCanal(lcanales.busca(strCanal));
					else
						registroOk = false;
					
					if (strContactado.length() == 0) {
						entr.setContactado(null);
					}
					else if (lcontactados.busca(strContactado) != null) {
						entr.setContactado(lcontactados.busca(strContactado).getId());
					}
					else {
						registroOk = false;
					}

					if (strSePresenta.length() == 0) {
						entr.setPresentacion(null);
					}
					else if (lpresentaciones.busca(strSePresenta) != null) {
						entr.setPresentacion(lpresentaciones.busca(strSePresenta).getId());
					}
					else {
						registroOk = false;
					}
					
					if (obsContacto.length() > 0)
						entr.setObservacionContacto(obsContacto);
					else
						entr.setObservacionContacto(null);
					
					if (strNacionalidad.length() == 0) {
						entr.setNacionalidad(null);
					}
					else if (lnacionalidades.busca(strNacionalidad) != null) {
						entr.setNacionalidad(lnacionalidades.busca(strNacionalidad).getId());
					}
					else {
						registroOk = false;
					}

					if (strFechaNacimiento.length() == 0) {
						entr.setFechaNacimiento(null);
					} 
					else {
						boolean bFechaNacimiento = false;
						Date fechaNacimiento = new Date();
						try {
							fechaNacimiento = ft.parse(strFechaNacimiento);
							bFechaNacimiento = true;
						}
						catch(Exception e) {
							System.out.println("No se pudo convertir la fecha de nacimiento " + strFechaNacimiento);
						}
						
						if (bFechaNacimiento)
							entr.setFechaNacimiento(fechaNacimiento);
						else
							registroOk = false;
					}

					//La edad se rescata pero no se utiliza
					
					if (strDireccion.length() > 0)
						entr.setDireccion(strDireccion);
					else
						entr.setDireccion(null);

					//Parte 4 de 5 (10 campos)

					if (ciudad.length() > 0)
						entr.setCiudad(ciudad);
					else
						entr.setCiudad(null);

					if (numeroDireccion.length() > 0)
						entr.setNumeroDireccion(numeroDireccion);
					else
						entr.setNumeroDireccion(null);

					if (strPrevision.length() == 0) {
						entr.setPrevision(null);
					}
					else if (lprevisiones.busca(strPrevision) != null) {
						entr.setPrevision(lprevisiones.busca(strPrevision).getId());
					}
					else {
						registroOk = false;
					}

					if (strSalud.length() == 0) {
						entr.setSalud(null);
					}
					else if (lsaludes.busca(strSalud) != null) {
						entr.setSalud(lsaludes.busca(strSalud).getId());
					}
					else {
						registroOk = false;
					}

					if (strSeguroCovid.length() == 0) {
						entr.setSeguroCovid(null);
					}
					else if (lseguroscovid.busca(strSeguroCovid) != null) {
						entr.setSeguroCovid(lseguroscovid.busca(strSeguroCovid).getId());
					}
					else {
						registroOk = false;
					}

					if (strTipoCuenta.length() == 0) {
						entr.setTipoCuenta(null);
					}
					else if (ltiposcuenta.busca(strTipoCuenta) != null) {
						entr.setTipoCuenta(ltiposcuenta.busca(strTipoCuenta).getId());
					}
					else {
						registroOk = false;
					}

					if (strBanco.length() == 0) {
						entr.setBancoCuenta(null);
					}
					else if (lbancos.busca(strBanco) != null) {
						entr.setBancoCuenta(lbancos.busca(strBanco).getId());
					}
					else {
						registroOk = false;
					}

					if (numeroCuenta.length() > 0)
						entr.setNumeroCuenta(numeroCuenta);
					else
						entr.setNumeroCuenta(null);

					if (calzado.length() > 0)
						entr.setCalzado(calzado);
					else
						entr.setCalzado(null);

					if (strPolera.length() == 0) {
						entr.setPolera(null);
					}
					else if (ltallas.busca(strPolera) != null) {
						entr.setPolera(ltallas.busca(strPolera).getId());
					}
					else {
						registroOk = false;
					}

					//Parte 5 de 5
					
					if (strPoleron.length() == 0) {
						entr.setPoleron(null);
					}
					else if (ltallas.busca(strPoleron) != null) {
						entr.setPoleron(ltallas.busca(strPoleron).getId());
					}
					else {
						registroOk = false;
					}

					if (strPantalon.length() == 0) {
						entr.setPantalon(null);
					}
					else if (ltallas.busca(strPantalon) != null) {
						entr.setPantalon(ltallas.busca(strPantalon).getId());
					}
					else {
						registroOk = false;
					}

					if (nombreContacto.length() > 0)
						entr.setNombreContacto(nombreContacto);
					else
						entr.setNombreContacto(null);

					if (telefonoContacto.length() > 0)
						entr.setTelefonoContacto(telefonoContacto);
					else
						entr.setTelefonoContacto(null);

					if (parentescoContacto.length() == 0) {
						entr.setParentezcoContacto(null);
					}
					else if (lparentescos.busca(parentescoContacto) != null) {
						entr.setParentezcoContacto(lparentescos.busca(parentescoContacto).getId());
					}
					else {
						registroOk = false;
					}

					if (direccionContacto.length() > 0)
						entr.setDireccionContacto(direccionContacto);
					else
						entr.setDireccionContacto(null);
					
					//La región de contacto se rescata, pero no se utiliza

					if (strComunaContacto.length() == 0) {
						entr.setComunaContacto(null);
					}
					else if (lcomunas.busca(strComunaContacto) != null) {
						entr.setComunaContacto(lcomunas.busca(strComunaContacto));
					}
					else {
						registroOk = false;
					}

					if (strEntrevistador.length() == 0) {
						entr.setEntrevistador(null);
					}
					else if (lentrevistadores.busca(strEntrevistador) != null) {
						entr.setEntrevistador(lentrevistadores.busca(strEntrevistador));
					}
					else {
						registroOk = false;
					}					
					
					//Verificación de registro modificado
					
					if (registroOk) {
						regNuevos++;
						entNuevos.add(entr);
					}
				}
				
				if (!registroOk) {
					EntrevistadoVista entrx = new EntrevistadoVista();
					entrx.setFechaIngreso(strFechaIngreso);
					//entrx.setNombreReclutador(strReclutador);
					entrx.setRun(run);
					entrx.setNombreCliente(strCliente);
					entrx.setNombres(nombres);
					entrx.setApPaterno(apPaterno);
					entrx.setApMaterno(apMaterno);
					entrx.setTelefono(telefono);
					entrx.setObservacionRegistro(obsRegistro);
					entrx.setNombreCargo(strCargo);
					entrx.setNombreComuna(strComuna);
					entrx.setNombreCanal(strCanal);
					entrx.setUsername(strUsuario);
					entErroneos.add(entrx);
					regErroneos++;
				}
				
			}
    		
        }
        catch(IOException e) {
        	System.out.println("Error al leer la planilla");
        }

		System.out.println("Registros totales: " + regTotales);
		System.out.println("Registros antiguos: " + regAntiguos);
		System.out.println("Registros nuevos: " + regNuevos);
		System.out.println("Registros erróneos: " + regErroneos);
		System.out.println("Registros blancos: " + regBlancos);

		modelAndView.addObject("regTotales",regTotales);
		modelAndView.addObject("regAntiguos",regAntiguos);
		modelAndView.addObject("regNuevos",regNuevos);
		modelAndView.addObject("regErroneos",regErroneos);
		modelAndView.addObject("regBlancos",regBlancos);

		modelAndView.addObject("entModificados",entModificados);
		modelAndView.addObject("entNuevos",entNuevos);
		modelAndView.addObject("entErroneos",entErroneos);

		request.getSession().setAttribute("listaModificados",entModificados);
		request.getSession().setAttribute("listaNuevos",entNuevos);
		request.getSession().setAttribute("listaErroneos",entErroneos);

		return modelAndView;
	}
	
	@GetMapping("/actmasiva")
	public ModelAndView cambioEstadoMasivo() 
	{
		ModelAndView modelAndView = new ModelAndView("entrevistas/actestado");

		return modelAndView;
	}

	@PostMapping("/procactmasiva")
	public ModelAndView procesaEstadoMasivo(MultipartFile flCambiaEstado, 
			HttpServletRequest request) 
	{
		ModelAndView modelAndView = new ModelAndView("entrevistas/recactestado");

        //String filename=flCambiaEstado.getOriginalFilename();
        //String suffix=filename.substring(filename.lastIndexOf(".")+1);

        List<Entrevistado> entModificados = new ArrayList<Entrevistado>();
        
        EntrevistadoHashmap lentrevistados = new EntrevistadoHashmap();
        lentrevistados.llenar(entrevistadoServicio.obtenerEntrevistados());
        
        ValidadoHashmap lvalidados = new ValidadoHashmap();
        lvalidados.llenar(validadoServicio.obtenerValidados());
        
        int regTotales = 0;
        int runNoExiste = 0;
        int runModificados = 0;
        int regSinModificar = 0;
        int valorNoExiste = 0;
        int runNoPertenece = 0;
        
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(flCambiaEstado.getInputStream());
    		XSSFSheet sheet = workbook.getSheetAt(0);

    		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    		//cl.puertoesperanza.entrevistassa.modelo.User usuarioAcceso = usuarioServicio.obtenerUsuarioPorNombre(user.getUsername());

    		String roluser = "ROLE_USER";
    		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    		
    		if (auth.getAuthorities().toString().contains("ROLE_ADMIN")) {
    			roluser = "ROLE_ADMIN";
    		}
    		else if (auth.getAuthorities().toString().contains("ROLE_SUPERADMIN")) {
    			roluser = "ROLE_SUPERADMIN";
    		}

    		for(int i=1; i<sheet.getPhysicalNumberOfRows();i++) {
    			regTotales++;
				XSSFRow row = sheet.getRow(i);
				
				//int entrevistadoId = Math.round(Float.parseFloat(row.getCell(0).toString()));
				String run = row.getCell(1).toString();
				String strValidado = row.getCell(2).toString();
				
				Entrevistado entr = new Entrevistado();
				entr = lentrevistados.busca(run);
				
				if (entr != null) {
					//System.out.println(entr.getIdEntrevistado() + " " + entr.getValidado() + " " + lvalidados.busca(strValidado).getId());
					
					if (lvalidados.busca(strValidado).getId() > 0) {
						
						if (user.getUsername().equals(entr.getUsuario().getUsername()) || !roluser.equals("ROLE_USER")) {

							if (entr.getValidado() != lvalidados.busca(strValidado).getId()) {
								entr.setValidado(lvalidados.busca(strValidado).getId());
								entModificados.add(entr);
								runModificados++;
							}
							else {
								regSinModificar++;
							}
							
						}
						else {
							runNoPertenece++;
						}
					}
					else {
						valorNoExiste++;
					}
				}
				else {
					runNoExiste++;
				}
			}
    		
    		System.out.println("Registros totales: " + regTotales);
    		System.out.println("RUN no existe: " + runNoExiste);
    		System.out.println("RUN no le pertenece: " + runNoPertenece);
    		System.out.println("RUN modificados: " + runModificados);
    		System.out.println("Registros no modificados: " + regSinModificar);
    		System.out.println("Estado no existente: " + valorNoExiste);
    		
    		modelAndView.addObject("regTotales",regTotales);
    		modelAndView.addObject("runNoExiste",runNoExiste);
    		modelAndView.addObject("runNoPertenece",runNoPertenece);
    		modelAndView.addObject("runModificados",runModificados);
    		modelAndView.addObject("regSinModificar",regSinModificar);
    		modelAndView.addObject("valorNoExiste",valorNoExiste);
    		
    		modelAndView.addObject("entModificados",entModificados);
    		modelAndView.addObject("lvalidados",lvalidados);
    		
    		//session.setAttribute("listaModificados", entModificados);
    		request.getSession().setAttribute("listaModificados",entModificados);
        }
        catch(IOException e) {
        	System.out.println("Error al leer la planilla");
        }
		        
        //System.out.println(filename + " " + suffix);
		return modelAndView;
        //return "redirect:/entrevistado/resactmasiva";
	}

	@PostMapping("/finactmasiva")
	public ModelAndView finEstadoMasivo(Model model, HttpSession session) 
	{
		ModelAndView modelAndView = new ModelAndView("entrevistas/actestado");

		String mensaje = "";
		String tipoMensaje = "";

		//Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
		
		@SuppressWarnings("unchecked")
		List<Entrevistado> entrevistas = (List<Entrevistado>) session.getAttribute("listaModificados");
		//ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");
		
		if (entrevistas != null) {
			//@SuppressWarnings("unchecked")
			//List<Entrevistado> entrevistados = (List<Entrevistado>) inputFlashMap.get("listaModificados");
			/*for (Entrevistado e:entrevistas) {
				System.out.println(e.getRun() + " " + e.getNombres() + " " + e.getApPaterno() + " " + e.getValidado());
			}*/

			if (entrevistadoServicio.editarEntrevistadoMasivo(entrevistas)) {
				mensaje = "La actualización se realizó de manera exitosa";
				tipoMensaje = "Ok";
			}
			else {
				mensaje = "Ocurrió un error al actualizar masivamente los registros";
				tipoMensaje = "Error";				
			}
		}
		else {
			mensaje = "No se pudo recuperar el listado de elementos a modificar";
			tipoMensaje = "Error";
		}
		
		modelAndView.addObject("mensaje", mensaje);
		modelAndView.addObject("tipoMensaje", tipoMensaje);
		
		return modelAndView;
	}

    @GetMapping("/exportar")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        
        System.out.println("Inicio: " + currentDateTime);
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=postulantes_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
         
		//List<Entrevistado> listaentrevistados = new ArrayList<Entrevistado>();
		//listaentrevistados = entrevistadoServicio.obtenerEntrevistados();

		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		//System.out.println(auth.getAuthorities().toString());

		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, (diasexportacion * -1));
		Date fechalimite = c.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fechalimitestr = sdf.format(fechalimite);

		System.out.println("Fecha límite: " + fechalimitestr);

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//cl.puertoesperanza.entrevistassa.modelo.User usuarioAcceso = usuarioServicio.obtenerUsuarioPorNombre(user.getUsername());

		String roluser = "ROLE_USER";
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth.getAuthorities().toString().contains("ROLE_ADMIN")) {
			roluser = "ROLE_ADMIN";
		}
		else if (auth.getAuthorities().toString().contains("ROLE_SUPERADMIN")) {
			roluser = "ROLE_SUPERADMIN";
		}

        String fecha2 = dateFormatter.format(new Date());        
        System.out.println("Antes del listado: " + fecha2);

		List<EntrevistadoVista> lentrevistadosvista = new ArrayList<EntrevistadoVista>();

		if (roluser.equals("ROLE_USER")) {
			lentrevistadosvista = entrevistadoVistaServicio.obtenerEntrevistadosVistaUsuario(user.getUsername());
		}
		else {
			lentrevistadosvista = entrevistadoVistaServicio.obtenerEntrevistadosVista();
		}

        String fecha3 = dateFormatter.format(new Date());
        System.out.println("Despues del listado: " + fecha3);

        //EntrevistadoExcelExporter excelExporter = new EntrevistadoExcelExporter(lentrevistadosvista);
        //excelExporter.export(response);
        
        FastExcelSimpleWrite excelExporter = new FastExcelSimpleWrite(lentrevistadosvista);
        excelExporter.writeExcel(response);
        
        String fecha4 = dateFormatter.format(new Date());
        System.out.println("Fin: " + fecha4);
        
    }

    @GetMapping("/exportar/filtro")
    public void exportToExcelFilter(
    		HttpServletResponse response,
			@RequestParam(defaultValue = "") String e, 
			@RequestParam(defaultValue = "") String u, 
			@RequestParam(defaultValue = "") String fn, 
			@RequestParam(defaultValue = "") String fx,	
			@RequestParam(defaultValue = "") String fne, 
			@RequestParam(defaultValue = "") String fxe,	
			@RequestParam(defaultValue = "0") Integer t, 
			@RequestParam(defaultValue = "0") Integer c, 
			@RequestParam(defaultValue = "0") Integer n, 
			@RequestParam(defaultValue = "0") Integer v 
    	) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=postulantes_filtro_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        System.out.println("Comienzo búsqueda de datos");
        
		String empresa = "";
		if (e.trim().length() > 0) 
			empresa = e;

		Integer idvalidadoselec = 0;
		if (v > 0) 
			idvalidadoselec = v;

		Integer idcargoselec = 0;
		//Cargo cargo = new Cargo();
		if (c > 0) {
			idcargoselec = c;
			//cargo = cargoServicio.obtenerCargoPorId(idcargoselec);
		} 

		Integer idcanalselec = 0;
		//Canal canal = new Canal();
		if (n > 0) {
			idcanalselec = n;
			//canal = canalServicio.obtenerCanalPorId(idcanalselec);
		} 
		
		String usernameselec = "";
		if (u.trim().length() > 0) {
			usernameselec = u;
		}

		/*
		Integer idreclutadorselec = 0;
		//Reclutador reclutador = new Reclutador();
		if (r > 0) {
			idreclutadorselec = r;
			//reclutador = reclutadorServicio.obtenerReclutadorPorId(idreclutadorselec);
		}*/ 
		
		Integer idestadoselec = 0;
		if (t > 0) 
			idestadoselec = t;
		
		String fechamin = "";
		if (fn.trim().length() > 0)
			fechamin = fn;

		String fechamax = "";
		if (fx.trim().length() > 0)
			fechamax = fx;
		
		String fechaestmin = "";
		if (fne.trim().length() > 0)
			fechaestmin = fne;

		String fechaestmax = "";
		if (fxe.trim().length() > 0)
			fechaestmax = fxe;
		
		System.out.println("Variables iniciales seteadas");
		
		/*List<Entrevistado> listaentrevistasfiltro = new ArrayList<Entrevistado>();

		listaentrevistasfiltro = entrevistadoServicio.buscarEntrevistados(
				empresa, reclutador, fechamin, fechamax, idestadoselec, cargo, canal, 
				idvalidadoselec);*/

		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		//System.out.println(auth.getAuthorities().toString());

		Calendar d = Calendar.getInstance();
		d.add(Calendar.DATE, (diasexportacion * -1));
		Date fechalimite = d.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fechalimitestr = sdf.format(fechalimite);

		System.out.println("Fecha límite: " + fechalimitestr);


		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//cl.puertoesperanza.entrevistassa.modelo.User usuarioAcceso = usuarioServicio.obtenerUsuarioPorNombre(user.getUsername());

		String roluser = "ROLE_USER";
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth.getAuthorities().toString().contains("ROLE_ADMIN")) {
			roluser = "ROLE_ADMIN";
		}
		else if (auth.getAuthorities().toString().contains("ROLE_SUPERADMIN")) {
			roluser = "ROLE_SUPERADMIN";
		}
		
		List<EntrevistadoVista> lentrevistasvistafiltro = new ArrayList<EntrevistadoVista>();

		if (roluser.equals("ROLE_USER")) {
			
			lentrevistasvistafiltro = entrevistadoVistaServicio.buscarEntrevistadosUsuario(
					empresa, usernameselec, fechamin, fechamax, fechaestmin, 
					fechaestmax, idestadoselec, idcargoselec, idcanalselec, 
					idvalidadoselec, user.getUsername());
			
		}
		else {
			
			lentrevistasvistafiltro = entrevistadoVistaServicio.buscarEntrevistados(
					empresa, usernameselec, fechamin, fechamax, fechaestmin, 
					fechaestmax, idestadoselec, idcargoselec, idcanalselec, 
					idvalidadoselec);
			
		}

		/*
		if (auth.getAuthorities().toString().contains("ROLE_ADMIN")) {
			lentrevistasvistafiltro = entrevistadoVistaServicio.buscarEntrevistadosVista(
					empresa, idreclutadorselec, fechamin, fechamax, fechaestmin, 
					fechaestmax, idestadoselec, idcargoselec, idcanalselec, 
					idvalidadoselec);
			System.out.println("Ingresé como administrador");
		}
		else {
			lentrevistasvistafiltro = entrevistadoVistaServicio.buscarEntrevistadosVistaFiltroDias(
					empresa, idreclutadorselec, fechamin, fechamax, fechaestmin, 
					fechaestmax, idestadoselec, idcargoselec, idcanalselec, 
					idvalidadoselec, fechalimitestr);
			System.out.println("Ingresé como usuario normal");
		}
		*/

		System.out.println("Listado completo de entrevistados filtrado obtenido");
        
        //EntrevistadoExcelExporter excelExporter = new EntrevistadoExcelExporter(lentrevistasvistafiltro);
        //excelExporter.export(response);
        
        FastExcelSimpleWrite excelExporter = new FastExcelSimpleWrite(lentrevistasvistafiltro);
        excelExporter.writeExcel(response);

    }

	@PostMapping("/eliminar")
	public ModelAndView eliminarEntrevistado(
			@RequestParam Integer idEntrevistado,
			@RequestParam(defaultValue = "1") Integer p
			) {
		
		ModelAndView modelAndView = new ModelAndView("entrevistas/listafiltro");

		String mensaje = "";
		String tipoMensaje = "";

		//Nacionalidad nacionalidad = new Nacionalidad();
		//nacionalidad = nacionalidadServicio.obtenerNacionalidadPorId(idNacionalidad);

		EntrevistadoVista entrvista = entrevistadoVistaServicio.obtenerEntrevistadosVistaPorId(idEntrevistado);
		
		if (entrvista != null) {

			EntrevistadoEliminado entrelim = Util.transformarEntrevistadoVista(entrvista);
			
			if (entrevistadoEliminadoServicio.agregarEntrevistadoEliminado(entrelim)) {
				if (entrevistadoServicio.eliminarEntrevistado(idEntrevistado)) {
					mensaje = "La postulación se ha eliminado exitosamente.";
					tipoMensaje = "Ok";
				}
				else {
					mensaje = "Ocurrió un error al eliminar la postulación.";
					tipoMensaje = "Error";
				}
			}
			else {
				mensaje = "Ocurrió un error al respaldar la postulación.";
				tipoMensaje = "Error";
			}
		}
		else {
			mensaje = "No existe una postulación con el identificador ingresado.";
			tipoMensaje = "Error";
		}

		String roluser = "ROLE_USER";
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		cl.puertoesperanza.entrevistassa.modelo.User usuarioAcceso = usuarioServicio.obtenerUsuarioPorNombre(user.getUsername());
		
		if (auth.getAuthorities().toString().contains("ROLE_ADMIN")) {
			roluser = "ROLE_ADMIN";
		}
		else if (auth.getAuthorities().toString().contains("ROLE_SUPERADMIN")) {
			roluser = "ROLE_SUPERADMIN";
		}

		long cantTotal = 0;
		
		if (roluser.equals("ROLE_USER")) {
			cantTotal = entrevistadoServicio.obtenerEntrevistadosPorUsuario(usuarioAcceso).size();
		}
		else {
			cantTotal = entrevistadoServicio.obtenerEntrevistados().size();
		}
		
		modelAndView.addObject("paginas", Util.getArregloPaginas(p, (int) entrevistadoServicio.getPageCount(cantTotal, this.paginacion)));
		modelAndView.addObject("paginaActual",p);
		
		List<Entrevistado> listaentrevistados = new ArrayList<Entrevistado>();
		
		if (roluser.equals("ROLE_USER")) {
			listaentrevistados = entrevistadoServicio.getPageUser(p-1, this.paginacion, usuarioAcceso);
		}
		else {
			listaentrevistados = entrevistadoServicio.getPage(p-1, this.paginacion);			
		}
		
		
		modelAndView.addObject("roluser",roluser);

		modelAndView.addObject("nombres","");
		modelAndView.addObject("appaterno","");
		modelAndView.addObject("apmaterno","");
		modelAndView.addObject("run","");
		modelAndView.addObject("entrevistados",listaentrevistados);
		modelAndView.addObject("mensaje",mensaje);
		modelAndView.addObject("tipoMensaje",tipoMensaje);

		return modelAndView;

	}
	
	@PostMapping("/eliminarfiltro")
	public ModelAndView eliminarEntrevistadoFiltro(
			@RequestParam Integer idEntrevistado,
			@RequestParam(defaultValue = "1") Integer p
			) {
		
		ModelAndView modelAndView = new ModelAndView("entrevistas/listado");

		String mensaje = "";
		String tipoMensaje = "";

		String roluser = "ROLE_USER";
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//cl.puertoesperanza.entrevistassa.modelo.User usuarioAcceso = usuarioServicio.obtenerUsuarioPorNombre(user.getUsername());
		
		if (auth.getAuthorities().toString().contains("ROLE_ADMIN")) {
			roluser = "ROLE_ADMIN";
		}
		else if (auth.getAuthorities().toString().contains("ROLE_SUPERADMIN")) {
			roluser = "ROLE_SUPERADMIN";
		}
		
		//Nacionalidad nacionalidad = new Nacionalidad();
		//nacionalidad = nacionalidadServicio.obtenerNacionalidadPorId(idNacionalidad);

		EntrevistadoVista entrvista = entrevistadoVistaServicio.obtenerEntrevistadosVistaPorId(idEntrevistado);
		
		if (entrvista != null) {

			EntrevistadoEliminado entrelim = Util.transformarEntrevistadoVista(entrvista);
			
			if (entrevistadoEliminadoServicio.agregarEntrevistadoEliminado(entrelim)) {
				if (entrevistadoServicio.eliminarEntrevistado(idEntrevistado)) {
					mensaje = "La postulación se ha eliminado exitosamente.";
					tipoMensaje = "Ok";
				}
				else {
					mensaje = "Ocurrió un error al eliminar la postulación.";
					tipoMensaje = "Error";
				}
			}
			else {
				mensaje = "Ocurrió un error al respaldar la postulación.";
				tipoMensaje = "Error";
			}
		}
		else {
			mensaje = "No existe una postulación con el identificador ingresado.";
			tipoMensaje = "Error";
		}

		long cantTotal = 0;
		
		if (roluser.equals("ROLE_USER")) {
			//cantTotal = entrevistadoVistaServicio.obtenerEntrevistadosVistaUsuario(user.getUsername()).size();
			cantTotal = entrevistadoVistaServicio.obtenerCantidadRegistrosUsuario(user.getUsername());
		}
		else {
			//cantTotal = entrevistadoVistaServicio.obtenerEntrevistadosVista().size();
			cantTotal = entrevistadoVistaServicio.obtenerCantidadRegistros();
		}
		
		modelAndView.addObject("paginas", Util.getArregloPaginas(p, (int) entrevistadoVistaServicio.getPageCount(cantTotal, this.paginacion)));
		modelAndView.addObject("paginaActual",p);
		
		List<EntrevistadoVista> listaentrevistados = new ArrayList<EntrevistadoVista>();
		//listaentrevistados = entrevistadoVistaServicio.getPage(p-1, this.paginacion);
		
		if (roluser.equals("ROLE_USER")) {
			listaentrevistados = entrevistadoVistaServicio.getPageUsuario(p-1, this.paginacion, user.getUsername());
		}
		else {
			listaentrevistados = entrevistadoVistaServicio.getPage(p-1, this.paginacion);
		}

		/*
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.getAuthorities().toString().contains("ROLE_ADMIN")) {
			roluser = "ROLE_ADMIN";
		}
		*/
		
		modelAndView.addObject("roluser",roluser);

		modelAndView.addObject("idcargoselec",0);
		modelAndView.addObject("cargos",cargoServicio.obtenerCargos());
		modelAndView.addObject("idcanalselec",0);
		modelAndView.addObject("canales",canalServicio.obtenerCanales());
		modelAndView.addObject("idreclutadorselec",0);
		modelAndView.addObject("reclutadores",reclutadorServicio.obtenerReclutadores());
		modelAndView.addObject("idestadoselec",0);
		modelAndView.addObject("estados",estadoServicio.obtenerEstados());
		modelAndView.addObject("fechamin","");
		modelAndView.addObject("fechamax","");
		modelAndView.addObject("fechaestmin","");
		modelAndView.addObject("fechaestmax","");
		modelAndView.addObject("empresa","");
		modelAndView.addObject("idvalidadoselec",0);
		modelAndView.addObject("validados",validadoServicio.obtenerValidados());
		
		modelAndView.addObject("entrevistados",listaentrevistados);
		modelAndView.addObject("mensaje",mensaje);
		modelAndView.addObject("tipoMensaje",tipoMensaje);
		
		return modelAndView;

	}
	
}
