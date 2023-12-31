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
import cl.puertoesperanza.entrevistassa.modelo.EntrevistadoVista;
import cl.puertoesperanza.entrevistassa.repositorio.EntrevistadoVistaRepository;

@Service
public class EntrevistadoVistaServiceImpl implements EntrevistadoVistaService {

	@Autowired
	private EntrevistadoVistaRepository entrevistadoVistaRepositorio;

	@Override
	public List<EntrevistadoVista> obtenerEntrevistadosVista() {
		return (List<EntrevistadoVista>) entrevistadoVistaRepositorio.findAll();
	}

	@Override
	public List<EntrevistadoVista> buscarEntrevistadosVista(String empresa, 
			Integer idreclutador, String fechamin, String fechamax, String fechaestmin, 
			String fechaestmax, Integer idestado, Integer idcargo, Integer idcanal, 
			Integer idvalidado) {
		SearchSpecifications<EntrevistadoVista> searchSpecifications = new SearchSpecifications<>();
		
		if (empresa.length() > 0) 
		{
			searchSpecifications.add(new SearchCriteria("empresa",empresa, SearchOperation.MATCH));
		}
		
		if (idreclutador > 0) 
		{
			searchSpecifications.add(new SearchCriteria("reclutadorId", idreclutador, SearchOperation.EQUAL));
		}
		
		if (fechamin.length() > 0) {
			searchSpecifications.add(new SearchCriteria("fechaIngresoRv",fechamin, SearchOperation.GREATER_THAN_EQUAL));
		}

		if (fechamax.length() > 0) {
			searchSpecifications.add(new SearchCriteria("fechaIngresoRv",fechamax, SearchOperation.LESS_THAN_EQUAL));
		}

		if (fechaestmin.length() > 0) {
			searchSpecifications.add(new SearchCriteria("fechaEstadoRv",fechaestmin, SearchOperation.GREATER_THAN_EQUAL));
		}

		if (fechaestmax.length() > 0) {
			searchSpecifications.add(new SearchCriteria("fechaEstadoRv",fechaestmax, SearchOperation.LESS_THAN_EQUAL));
		}

		if (idestado > 0) 
		{
			searchSpecifications.add(new SearchCriteria("estadoId", idestado, SearchOperation.EQUAL));
		}
		
		if (idcargo > 0) 
		{
			searchSpecifications.add(new SearchCriteria("cargoId", idcargo, SearchOperation.EQUAL));
		}

		if (idcanal > 0) 
		{
			searchSpecifications.add(new SearchCriteria("canalId", idcanal, SearchOperation.EQUAL));
		}

		if (idvalidado > 0) 
		{
			searchSpecifications.add(new SearchCriteria("validadoId", idvalidado, SearchOperation.EQUAL));
		}

		return entrevistadoVistaRepositorio.findAll(searchSpecifications);
	}

	@Override
	public long getPageCount(long registrosTotales, long registrosPorPagina) {
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
	public List<EntrevistadoVista> getPage(Integer pagina, Integer cantidad) {
		Pageable pageable = PageRequest.of(pagina, cantidad);
		Page<EntrevistadoVista> responsePage = entrevistadoVistaRepositorio.findAll(pageable);
		return responsePage.getContent();
	}

	@Override
	public List<EntrevistadoVista> buscarEntrevistados(String empresa, 
			Integer idreclutador, String fechamin, String fechamax, String fechaestmin, 
			String fechaestmax, Integer idestado, Integer idcargo, 
			Integer idcanal, Integer idvalidado) {
		SearchSpecifications<EntrevistadoVista> searchSpecifications = new SearchSpecifications<>();
		
		if (empresa.length() > 0) 
		{
			searchSpecifications.add(new SearchCriteria("empresa",empresa, SearchOperation.MATCH));
		}
		
		if (idreclutador > 0) 
		{
			searchSpecifications.add(new SearchCriteria("reclutadorId",idreclutador, SearchOperation.EQUAL));
		}
		
		if (fechamin.length() > 0) {
			//LocalDateTime fechaMinima = LocalDateTime.now();
			//Date fechaMinima = new Date();
			//Date fechaHoy = new Date();
			//System.out.println("Fecha minima string: " + fechamin);
			//DateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
			//try {
			//	fechaMinima = ft.parse(fechamin);				
			//}
			//catch(ParseException e) 
			//{
			//	System.out.println("Imposible transformar fecha usando formato " + ft);
			//}

			//java.sql.Date fechaMinimaSql = new java.sql.Date(fechaMinima.getTime());

			//System.out.println("Fecha minima date: " + fechaMinima);
			//System.out.println("Fecha minima sql: " + fechaMinimaSql);
			searchSpecifications.add(new SearchCriteria("fechaIngresoRv",fechamin, SearchOperation.GREATER_THAN_EQUAL));
		}

		if (fechamax.length() > 0) {
			//Date fechaMaxima = new Date();
			//SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
			//try {
			//	fechaMaxima = ft.parse(fechamax);
			//}
			//catch(ParseException e) 
			//{
			//	System.out.println("Imposible transformar fecha usando formato " + ft);
			//}
			searchSpecifications.add(new SearchCriteria("fechaIngresoRv",fechamax, SearchOperation.LESS_THAN_EQUAL));
		}

		if (fechaestmin.length() > 0) {
			searchSpecifications.add(new SearchCriteria("fechaEstadoRv",fechaestmin, SearchOperation.GREATER_THAN_EQUAL));
		}

		if (fechaestmax.length() > 0) {
			searchSpecifications.add(new SearchCriteria("fechaEstadoRv",fechaestmax, SearchOperation.LESS_THAN_EQUAL));
		}

		if (idestado > 0) 
		{
			searchSpecifications.add(new SearchCriteria("estadoId",idestado, SearchOperation.EQUAL));
		}
		
		if (idcargo > 0) 
		{
			searchSpecifications.add(new SearchCriteria("cargoId",idcargo, SearchOperation.EQUAL));
		}

		if (idcanal > 0) 
		{
			searchSpecifications.add(new SearchCriteria("canalId",idcanal, SearchOperation.EQUAL));
		}

		if (idvalidado > 0) 
		{
			searchSpecifications.add(new SearchCriteria("validadoId",idvalidado, SearchOperation.EQUAL));
		}

		return entrevistadoVistaRepositorio.findAll(searchSpecifications);
		//return (List<Entrevistado>) entrevistadoRepositorio.findAll();
	}

	@Override
	public List<EntrevistadoVista> buscarEntrevistadosPagina(String empresa, 
			Integer idreclutador, String fechamin, String fechamax, String fechaestmin, 
			String fechaestmax, Integer idestado, Integer idcargo, Integer idcanal, 
			Integer idvalidado, Integer pagina, Integer cantidad) {
		SearchSpecifications<EntrevistadoVista> searchSpecifications = new SearchSpecifications<>();
		
		if (empresa.length() > 0) 
		{
			searchSpecifications.add(new SearchCriteria("empresa",empresa, SearchOperation.MATCH));
		}
		
		if (idreclutador > 0) 
		{
			searchSpecifications.add(new SearchCriteria("reclutadorId",idreclutador, SearchOperation.EQUAL));
		}
		
		if (fechamin.length() > 0) {
			//LocalDateTime fechaMinima = LocalDateTime.now();
			//Date fechaMinima = new Date();
			//Date fechaHoy = new Date();
			//DateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
			/*try {
				fechaMinima = ft.parse(fechamin);				
			}
			catch(ParseException e) 
			{
				System.out.println("Imposible transformar fecha usando formato " + ft);
			}*/
			searchSpecifications.add(new SearchCriteria("fechaIngresoRv",fechamin, SearchOperation.GREATER_THAN_EQUAL));
		}

		if (fechamax.length() > 0) {
			//Date fechaMaxima = new Date();
			//SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
			/*try {
				fechaMaxima = ft.parse(fechamax);
			}
			catch(ParseException e) 
			{
				System.out.println("Imposible transformar fecha usando formato " + ft);
			}*/
			searchSpecifications.add(new SearchCriteria("fechaIngresoRv",fechamax, SearchOperation.LESS_THAN_EQUAL));
		}

		if (fechaestmin.length() > 0) {
			searchSpecifications.add(new SearchCriteria("fechaEstadoRv",fechaestmin, SearchOperation.GREATER_THAN_EQUAL));
		}

		if (fechaestmax.length() > 0) {
			searchSpecifications.add(new SearchCriteria("fechaEstadoRv",fechaestmax, SearchOperation.LESS_THAN_EQUAL));
		}

		if (idestado > 0) 
		{
			searchSpecifications.add(new SearchCriteria("estadoId",idestado, SearchOperation.EQUAL));
		}
		
		if (idcargo > 0) 
		{
			searchSpecifications.add(new SearchCriteria("cargoId",idcargo, SearchOperation.EQUAL));
		}

		if (idcanal > 0) 
		{
			searchSpecifications.add(new SearchCriteria("canalId",idcanal, SearchOperation.EQUAL));
		}

		if (idvalidado > 0) 
		{
			searchSpecifications.add(new SearchCriteria("validadoId",idvalidado, SearchOperation.EQUAL));
		}

		//return entrevistadoRepositorio.findAll(searchSpecifications);
		//return (List<Entrevistado>) entrevistadoRepositorio.findAll();
		System.out.println("Pagina: " + pagina + ", cantidad: " + cantidad);
		Pageable pageable = PageRequest.of(pagina, cantidad);
		Page<EntrevistadoVista> responsePage = entrevistadoVistaRepositorio.findAll(searchSpecifications, pageable);
		return responsePage.getContent();
	}

	@Override
	public EntrevistadoVista obtenerEntrevistadosVistaPorId(Integer identrevista) {
		Optional<EntrevistadoVista> entr = entrevistadoVistaRepositorio.findById(identrevista);
		return entr.get();
	}

	@Override
	public List<EntrevistadoVista> obtenerEntrevistadosVistaFiltroDias(String fechaingreso) {
		return (List<EntrevistadoVista>) entrevistadoVistaRepositorio.findIngresoFiltrado(fechaingreso);
	}

	@Override
	public List<EntrevistadoVista> buscarEntrevistadosVistaFiltroDias(String empresa, Integer idreclutador,
			String fechamin, String fechamax, String fechaestmin, String fechaestmax, Integer idestado, Integer idcargo,
			Integer idcanal, Integer idvalidado, String fechaingreso) {

		SearchSpecifications<EntrevistadoVista> searchSpecifications = new SearchSpecifications<>();
		
		if (empresa.length() > 0) 
		{
			searchSpecifications.add(new SearchCriteria("empresa",empresa, SearchOperation.MATCH));
		}
		
		if (idreclutador > 0) 
		{
			searchSpecifications.add(new SearchCriteria("reclutadorId", idreclutador, SearchOperation.EQUAL));
		}
		
		if (fechamin.length() > 0) {
			searchSpecifications.add(new SearchCriteria("fechaIngresoRv",fechamin, SearchOperation.GREATER_THAN_EQUAL));
		}

		if (fechamax.length() > 0) {
			searchSpecifications.add(new SearchCriteria("fechaIngresoRv",fechamax, SearchOperation.LESS_THAN_EQUAL));
		}

		if (fechaestmin.length() > 0) {
			searchSpecifications.add(new SearchCriteria("fechaEstadoRv",fechaestmin, SearchOperation.GREATER_THAN_EQUAL));
		}

		if (fechaestmax.length() > 0) {
			searchSpecifications.add(new SearchCriteria("fechaEstadoRv",fechaestmax, SearchOperation.LESS_THAN_EQUAL));
		}

		if (idestado > 0) 
		{
			searchSpecifications.add(new SearchCriteria("estadoId", idestado, SearchOperation.EQUAL));
		}
		
		if (idcargo > 0) 
		{
			searchSpecifications.add(new SearchCriteria("cargoId", idcargo, SearchOperation.EQUAL));
		}

		if (idcanal > 0) 
		{
			searchSpecifications.add(new SearchCriteria("canalId", idcanal, SearchOperation.EQUAL));
		}

		if (idvalidado > 0) 
		{
			searchSpecifications.add(new SearchCriteria("validadoId", idvalidado, SearchOperation.EQUAL));
		}
		
		if (fechaingreso.length() > 0) {
			searchSpecifications.add(new SearchCriteria("fechaIngresoRv",fechaingreso, SearchOperation.GREATER_THAN_EQUAL));
		}

		return entrevistadoVistaRepositorio.findAll(searchSpecifications);		
	}

}
