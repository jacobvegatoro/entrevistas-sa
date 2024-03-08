package cl.puertoesperanza.entrevistassa.servicio;

import java.util.List;

import cl.puertoesperanza.entrevistassa.modelo.EntrevistadoVista;

public interface EntrevistadoVistaService {
	
	EntrevistadoVista obtenerEntrevistadosVistaPorId(Integer identrevista);
	
	List<EntrevistadoVista> obtenerEntrevistadosVista();
	
	List<EntrevistadoVista> obtenerEntrevistadosVistaUsuario(String usuario);

	List<EntrevistadoVista> obtenerEntrevistadosVistaFiltroDias(String fechaingreso);
	
	/*List<EntrevistadoVista> buscarEntrevistadosVista(String empresa, Integer idreclutador, 
			String fechamin, String fechamax, String fechaestmin, String fechaestmax, 
			Integer idestado, Integer idcargo, Integer idcanal, Integer idvalidado);*/

	List<EntrevistadoVista> buscarEntrevistadosVistaFiltroDias(String empresa, 
			Integer idreclutador, String fechamin, String fechamax, String fechaestmin, 
			String fechaestmax, Integer idestado, Integer idcargo, Integer idcanal, 
			Integer idvalidado, String fechaingreso);

	long getPageCount(long registrosTotales, long registrosPorPagina);
	
	List<EntrevistadoVista> getPage(Integer pagina, Integer cantidad);

	List<EntrevistadoVista> getPageUsuario(Integer pagina, Integer cantidad, String usuario);
	
	List<EntrevistadoVista> buscarEntrevistados(String empresa, String username, 
			String fechamin, String fechamax, String fechaestmin, String fechaestmax, 
			Integer idestado, Integer idcargo, Integer idcanal, Integer idvalidado);

	List<EntrevistadoVista> buscarEntrevistadosUsuario(String empresa, String username, 
			String fechamin, String fechamax, String fechaestmin, String fechaestmax, 
			Integer idestado, Integer idcargo, Integer idcanal, Integer idvalidado, String usuario);

	List<EntrevistadoVista> buscarEntrevistadosPagina(String empresa, String username, 
			String fechamin, String fechamax, String fechaestmin, String fechaestmax, 
			Integer idestado, Integer idcargo, Integer idcanal, Integer idvalidado, 
			Integer pagina, Integer cantidad);

	List<EntrevistadoVista> buscarEntrevistadosUsuarioPagina(String empresa, String username, 
			String fechamin, String fechamax, String fechaestmin, String fechaestmax, 
			Integer idestado, Integer idcargo, Integer idcanal, Integer idvalidado, 
			Integer pagina, Integer cantidad, String usuario);
	
	List<EntrevistadoVista> obtenerRegistrosPagina(Integer cantidad, Integer inicio);

	List<EntrevistadoVista> obtenerRegistrosPaginaUsuario(Integer cantidad, Integer inicio, String username);

	long obtenerCantidadRegistros();

	long obtenerCantidadRegistrosUsuario(String username);

	List<EntrevistadoVista> filtrarRegistrosPagina(String nombres, String appaterno, String apmaterno, String run, 
			Integer pagina, Integer cantidad);

	List<EntrevistadoVista> filtrarRegistrosPaginaUsuario(String nombres, String appaterno, String apmaterno, String run, 
			Integer pagina, Integer cantidad, String username);

	long contarFiltrarRegistros(String nombres, String appaterno, String apmaterno, String run);

	long contarFiltrarRegistrosUsuario(String nombres, String appaterno, String apmaterno, String run, String username);

	long contarBuscarEntrevistados(String empresa, String username, 
			String fechamin, String fechamax, String fechaestmin, String fechaestmax, 
			Integer idestado, Integer idcargo, Integer idcanal, Integer idvalidado);
	
	long contarBuscarEntrevistadosUsuario(String empresa, String username, 
			String fechamin, String fechamax, String fechaestmin, String fechaestmax, 
			Integer idestado, Integer idcargo, Integer idcanal, Integer idvalidado, String usuario);

}
