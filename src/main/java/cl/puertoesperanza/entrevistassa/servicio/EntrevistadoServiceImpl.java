package cl.puertoesperanza.entrevistassa.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cl.puertoesperanza.entrevistassa.busqueda.SearchCriteria;
import cl.puertoesperanza.entrevistassa.busqueda.SearchOperation;
import cl.puertoesperanza.entrevistassa.busqueda.SearchSpecifications;
import cl.puertoesperanza.entrevistassa.modelo.Cargo;
import cl.puertoesperanza.entrevistassa.modelo.Cliente;
import cl.puertoesperanza.entrevistassa.modelo.Entrevistado;
import cl.puertoesperanza.entrevistassa.modelo.Instalacion;
import cl.puertoesperanza.entrevistassa.modelo.Reclutador;
import cl.puertoesperanza.entrevistassa.modelo.User;
import cl.puertoesperanza.entrevistassa.repositorio.EntrevistadoRepository;

@Service
public class EntrevistadoServiceImpl implements EntrevistadoService {

	@Autowired
	private EntrevistadoRepository entrevistadoRepositorio;
	
	@Override
	public List<Entrevistado> obtenerEntrevistados() {
		return (List<Entrevistado>) entrevistadoRepositorio.findAll();
	}

	@Override
	public boolean agregarEntrevistado(Entrevistado entrevistado) {
		if (entrevistadoRepositorio.save(entrevistado) != null)
			return true;
		else
			return false;
	}

	@Override
	public boolean editarEntrevistado(Entrevistado entrevistado) {
		if (entrevistadoRepositorio.save(entrevistado) != null)
			return true;
		else
			return false;
	}

	@Override
	public boolean eliminarEntrevistado(Integer idEntrevistado) {
		boolean retorno = false;
		try 
		{
			entrevistadoRepositorio.deleteById(idEntrevistado);
			return true;
		}
		catch(Exception ex) {
			System.out.println("Error al eliminar el registro: " + ex.getMessage());
		}
		return retorno;
	}

	@Override
	public List<Entrevistado> obtenerEntrevistadosPorRun(String run) {
		return entrevistadoRepositorio.findByRun(run);
	}

	@Override
	public long contarEntrevistadosPorRun(String run) {
		return entrevistadoRepositorio.countByRun(run);
	}

	@Override
	public Entrevistado obtenerEntrevistadoPorId(Integer idEntrevistado) {
		Optional<Entrevistado> entrevistado = entrevistadoRepositorio.findById(idEntrevistado);
		return entrevistado.get();
	}

	@Override
	public long getPageCount(long registrosTotales, long registrosPorPagina) 
	{
		long respuesta = 0;
		//long registros = entrevistadoRepositorio.count();
		long registros = registrosTotales;
		if (registrosPorPagina == 0 && registros == 0) 
		{
			respuesta = 1;
		} 
		else 
		{
			respuesta = (registros / registrosPorPagina) + (registros % registrosPorPagina == 0 ? 0 : 1);
		}
		return respuesta;
	}

	@Override
	public List<Entrevistado> getPage(Integer pagina, Integer cantidad) {
		Pageable pageable = PageRequest.of(pagina, cantidad);
		Page<Entrevistado> responsePage = entrevistadoRepositorio.findAll(pageable);
		return responsePage.getContent();
	}

	@Override
	public List<Entrevistado> getPageUser(Integer pagina, Integer cantidad, User usuario) {
		Pageable pageable = PageRequest.of(pagina, cantidad);
		Page<Entrevistado> responsePage = entrevistadoRepositorio.findByUsuario(usuario, pageable);
		return responsePage.getContent();
	}

	/*
	@Override
	public List<Entrevistado> buscarEntrevistados
	(
			String empresa,
			Reclutador reclutador, 
			String fechamin,
			String fechamax,
			Integer estado, 
			Cargo cargo,
			Canal canal,
			Integer validado
	) {
		
		SearchSpecifications<Entrevistado> searchSpecifications = new SearchSpecifications<>();
		
		if (empresa.length() > 0) 
		{
			searchSpecifications.add(new SearchCriteria("empresa",empresa, SearchOperation.MATCH));
		}
		
		if (reclutador.getId() != null) 
		{
			searchSpecifications.add(new SearchCriteria("reclutador",reclutador, SearchOperation.EQUAL));
		}
		
		if (fechamin.length() > 0) {
			//LocalDateTime fechaMinima = LocalDateTime.now();
			Date fechaMinima = new Date();
			//Date fechaHoy = new Date();
			System.out.println("Fecha minima string: " + fechamin);
			DateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
			try {
				fechaMinima = ft.parse(fechamin);				
			}
			catch(ParseException e) 
			{
				System.out.println("Imposible transformar fecha usando formato " + ft);
			}

			java.sql.Date fechaMinimaSql = new java.sql.Date(fechaMinima.getTime());

			System.out.println("Fecha minima date: " + fechaMinima);
			System.out.println("Fecha minima sql: " + fechaMinimaSql);
			searchSpecifications.add(new SearchCriteria("fechaIngreso",fechaMinimaSql, SearchOperation.GREATER_THAN));
		}

		if (fechamax.length() > 0) {
			Date fechaMaxima = new Date();
			SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
			try {
				fechaMaxima = ft.parse(fechamax);
			}
			catch(ParseException e) 
			{
				System.out.println("Imposible transformar fecha usando formato " + ft);
			}
			searchSpecifications.add(new SearchCriteria("fechaIngreso",fechaMaxima, SearchOperation.LESS_THAN_EQUAL));
		}

		if (estado > 0) 
		{
			searchSpecifications.add(new SearchCriteria("estado",estado, SearchOperation.EQUAL));
		}
		
		if (cargo.getId() != null) 
		{
			searchSpecifications.add(new SearchCriteria("cargo",cargo, SearchOperation.EQUAL));
		}

		if (canal.getId() != null) 
		{
			searchSpecifications.add(new SearchCriteria("canal",canal, SearchOperation.EQUAL));
		}

		if (validado > 0) 
		{
			searchSpecifications.add(new SearchCriteria("validado",validado, SearchOperation.EQUAL));
		}

		return entrevistadoRepositorio.findAll(searchSpecifications);
		//return (List<Entrevistado>) entrevistadoRepositorio.findAll();
	}

	@Override
	public List<Entrevistado> buscarEntrevistadosPagina(String empresa, 
			Reclutador reclutador, String fechamin, String fechamax, Integer estado, 
			Cargo cargo, Canal canal, Integer validado, Integer pagina,
			Integer cantidad) {

		SearchSpecifications<Entrevistado> searchSpecifications = new SearchSpecifications<>();
		
		if (empresa.length() > 0) 
		{
			searchSpecifications.add(new SearchCriteria("empresa",empresa, SearchOperation.MATCH));
		}
		
		if (reclutador.getId() != null) 
		{
			searchSpecifications.add(new SearchCriteria("reclutador",reclutador, SearchOperation.EQUAL));
		}
		
		if (fechamin.length() > 0) {
			//LocalDateTime fechaMinima = LocalDateTime.now();
			Date fechaMinima = new Date();
			//Date fechaHoy = new Date();
			DateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
			try {
				fechaMinima = ft.parse(fechamin);				
			}
			catch(ParseException e) 
			{
				System.out.println("Imposible transformar fecha usando formato " + ft);
			}
			searchSpecifications.add(new SearchCriteria("fechaIngreso",fechaMinima, SearchOperation.GREATER_THAN));
		}

		if (fechamax.length() > 0) {
			Date fechaMaxima = new Date();
			SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
			try {
				fechaMaxima = ft.parse(fechamax);
			}
			catch(ParseException e) 
			{
				System.out.println("Imposible transformar fecha usando formato " + ft);
			}
			searchSpecifications.add(new SearchCriteria("fechaIngreso",fechaMaxima, SearchOperation.LESS_THAN_EQUAL));
		}

		if (estado > 0) 
		{
			searchSpecifications.add(new SearchCriteria("estado",estado, SearchOperation.EQUAL));
		}
		
		if (cargo.getId() != null) 
		{
			searchSpecifications.add(new SearchCriteria("cargo",cargo, SearchOperation.EQUAL));
		}

		if (canal.getId() != null) 
		{
			searchSpecifications.add(new SearchCriteria("canal",canal, SearchOperation.EQUAL));
		}

		if (validado > 0) 
		{
			searchSpecifications.add(new SearchCriteria("validado",validado, SearchOperation.EQUAL));
		}

		//return entrevistadoRepositorio.findAll(searchSpecifications);
		//return (List<Entrevistado>) entrevistadoRepositorio.findAll();
		System.out.println("Pagina: " + pagina + ", cantidad: " + cantidad);
		Pageable pageable = PageRequest.of(pagina, cantidad);
		Page<Entrevistado> responsePage = entrevistadoRepositorio.findAll(searchSpecifications, pageable);
		return responsePage.getContent();
		
	}
*/
	
	@Override
	public List<Entrevistado> buscarEntrevistadosFiltro(
			String nombres, 
			String appaterno, 
			String apmaterno,
			String run) {

		SearchSpecifications<Entrevistado> searchSpecifications = new SearchSpecifications<>();
		
		if (nombres.length() > 0) 
		{
			searchSpecifications.add(new SearchCriteria("nombres",nombres, SearchOperation.MATCH));
		}
		
		if (appaterno.length() > 0) 
		{
			searchSpecifications.add(new SearchCriteria("apPaterno",appaterno, SearchOperation.MATCH));
		}

		if (apmaterno.length() > 0) 
		{
			searchSpecifications.add(new SearchCriteria("apMaterno",apmaterno, SearchOperation.MATCH));
		}

		if (run.length() > 0) 
		{
			searchSpecifications.add(new SearchCriteria("run",run, SearchOperation.MATCH));
		}

		return entrevistadoRepositorio.findAll(searchSpecifications);
	}

	@Override
	public List<Entrevistado> buscarEntrevistadosFiltroUsuario(
			String nombres, 
			String appaterno, 
			String apmaterno,
			String run,
			User usuario) {

		SearchSpecifications<Entrevistado> searchSpecifications = new SearchSpecifications<>();
		
		if (nombres.length() > 0) 
		{
			searchSpecifications.add(new SearchCriteria("nombres",nombres, SearchOperation.MATCH));
		}
		
		if (appaterno.length() > 0) 
		{
			searchSpecifications.add(new SearchCriteria("apPaterno",appaterno, SearchOperation.MATCH));
		}

		if (apmaterno.length() > 0) 
		{
			searchSpecifications.add(new SearchCriteria("apMaterno",apmaterno, SearchOperation.MATCH));
		}

		if (run.length() > 0) 
		{
			searchSpecifications.add(new SearchCriteria("run",run, SearchOperation.MATCH));
		}

		if (usuario.getUsername().length() > 0) 
		{
			searchSpecifications.add(new SearchCriteria("usuario",usuario, SearchOperation.EQUAL));
		}

		return entrevistadoRepositorio.findAll(searchSpecifications);
	}

	@Override
	public List<Entrevistado> buscarEntrevistadosFiltroPagina(
			String nombres, 
			String appaterno, 
			String apmaterno,
			String run, 
			Integer pagina, 
			Integer cantidad) {

		SearchSpecifications<Entrevistado> searchSpecifications = new SearchSpecifications<>();
		
		if (nombres.length() > 0) 
		{
			searchSpecifications.add(new SearchCriteria("nombres",nombres, SearchOperation.MATCH));
		}
		
		if (appaterno.length() > 0) 
		{
			searchSpecifications.add(new SearchCriteria("apPaterno",appaterno, SearchOperation.MATCH));
		}

		if (apmaterno.length() > 0) 
		{
			searchSpecifications.add(new SearchCriteria("apMaterno",apmaterno, SearchOperation.MATCH));
		}

		if (run.length() > 0) 
		{
			searchSpecifications.add(new SearchCriteria("run",run, SearchOperation.MATCH));
		}

		System.out.println("Pagina: " + pagina + ", cantidad: " + cantidad);
		Pageable pageable = PageRequest.of(pagina, cantidad);
		Page<Entrevistado> responsePage = entrevistadoRepositorio.findAll(searchSpecifications, pageable);
		return responsePage.getContent();	
	}

	@Override
	public List<Entrevistado> buscarEntrevistadosFiltroUsuarioPagina(
			String nombres, 
			String appaterno, 
			String apmaterno,
			String run, 
			Integer pagina, 
			Integer cantidad,
			User usuario) {

		SearchSpecifications<Entrevistado> searchSpecifications = new SearchSpecifications<>();
		
		if (nombres.length() > 0) 
		{
			searchSpecifications.add(new SearchCriteria("nombres",nombres, SearchOperation.MATCH));
		}
		
		if (appaterno.length() > 0) 
		{
			searchSpecifications.add(new SearchCriteria("apPaterno",appaterno, SearchOperation.MATCH));
		}

		if (apmaterno.length() > 0) 
		{
			searchSpecifications.add(new SearchCriteria("apMaterno",apmaterno, SearchOperation.MATCH));
		}

		if (run.length() > 0) 
		{
			searchSpecifications.add(new SearchCriteria("run",run, SearchOperation.MATCH));
		}

		if (usuario.getUsername().length() > 0) 
		{
			searchSpecifications.add(new SearchCriteria("usuario",usuario, SearchOperation.EQUAL));
		}

		System.out.println("Pagina: " + pagina + ", cantidad: " + cantidad);
		Pageable pageable = PageRequest.of(pagina, cantidad);
		Page<Entrevistado> responsePage = entrevistadoRepositorio.findAll(searchSpecifications, pageable);
		return responsePage.getContent();	
	}

	@Override
	public boolean editarEntrevistadoMasivo(List<Entrevistado> listaEntrevistado) {
		boolean resultado = false;
		try {
			entrevistadoRepositorio.saveAll(listaEntrevistado);
			resultado = true;
		}
		catch(Exception e) {
			System.out.println("Error al actualizar masivamente: " + e.getMessage());
		}
		return resultado;
	}

	@Override
	public List<Entrevistado> obtenerEntrevistadosPorCliente(Cliente cliente) {
		return entrevistadoRepositorio.findByCliente(cliente);
	}

	@Override
	public List<Entrevistado> obtenerEntrevistadosPorReclutador(Reclutador reclutador) {
		return entrevistadoRepositorio.findByReclutador(reclutador);
	}

	@Override
	public List<Entrevistado> obtenerEntrevistadosPorInstalacion(Instalacion instalacion) {
		return entrevistadoRepositorio.findByInstalacion(instalacion);
	}

	@Override
	public List<Entrevistado> obtenerEntrevistadosPorCargo(Cargo cargo) {
		return entrevistadoRepositorio.findByCargo(cargo);
	}

	@Override
	public List<Entrevistado> obtenerEntrevistadosPorNacionalidad(Integer idnacionalidad) {
		return entrevistadoRepositorio.findByNacionalidad(idnacionalidad);
	}

	@Override
	public List<Entrevistado> obtenerEntrevistadosPorEstado(Integer idestado) {
		return entrevistadoRepositorio.findByEstado(idestado);
	}

	@Override
	public List<Entrevistado> obtenerEntrevistadosPorUsuario(User usuario) {
		return entrevistadoRepositorio.findByUsuario(usuario);
	}

}
