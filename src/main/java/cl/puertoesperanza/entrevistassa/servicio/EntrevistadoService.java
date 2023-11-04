package cl.puertoesperanza.entrevistassa.servicio;

import java.util.List;

import cl.puertoesperanza.entrevistassa.modelo.Cargo;
import cl.puertoesperanza.entrevistassa.modelo.Cliente;
import cl.puertoesperanza.entrevistassa.modelo.Entrevistado;
import cl.puertoesperanza.entrevistassa.modelo.Instalacion;
import cl.puertoesperanza.entrevistassa.modelo.Reclutador;

public interface EntrevistadoService {

	List<Entrevistado> obtenerEntrevistados();
	boolean agregarEntrevistado(Entrevistado entrevistado);
	boolean editarEntrevistado(Entrevistado entrevistado);
	boolean editarEntrevistadoMasivo(List<Entrevistado> listaEntrevistado);
	boolean eliminarEntrevistado(Integer idEntrevistado);
	List<Entrevistado> obtenerEntrevistadosPorRun(String run);
	Entrevistado obtenerEntrevistadoPorId(Integer idEntrevistado);
	long getPageCount(long registrosTotales, long registrosPorPagina);
	List<Entrevistado> getPage(Integer pagina, Integer cantidad);
	/*List<Entrevistado> buscarEntrevistados(String empresa, Reclutador reclutador, 
			String fechamin, String fechamax, Integer estado, Cargo cargo, Canal canal,
			Integer validado);
	List<Entrevistado> buscarEntrevistadosPagina(String empresa, Reclutador reclutador, 
			String fechamin, String fechamax, Integer estado, Cargo cargo, Canal canal,
			Integer validado, Integer pagina, Integer cantidad);*/
	List<Entrevistado> buscarEntrevistadosFiltro(String nombres, String appaterno, 
			String apmaterno, String run);
	List<Entrevistado> buscarEntrevistadosFiltroPagina(String nombres, String appaterno, 
			String apmaterno, String run, Integer pagina, Integer cantidad);
	List<Entrevistado> obtenerEntrevistadosPorCliente(Cliente cliente);
	List<Entrevistado> obtenerEntrevistadosPorReclutador(Reclutador reclutador);
	List<Entrevistado> obtenerEntrevistadosPorInstalacion(Instalacion instalacion);
	List<Entrevistado> obtenerEntrevistadosPorCargo(Cargo cargo);
	List<Entrevistado> obtenerEntrevistadosPorNacionalidad(Integer idnacionalidad);
	List<Entrevistado> obtenerEntrevistadosPorEstado(Integer idestado);
	
}
