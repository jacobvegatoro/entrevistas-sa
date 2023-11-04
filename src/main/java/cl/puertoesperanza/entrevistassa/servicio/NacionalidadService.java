package cl.puertoesperanza.entrevistassa.servicio;

import java.util.List;

import cl.puertoesperanza.entrevistassa.modelo.Nacionalidad;

public interface NacionalidadService {

	List<Nacionalidad> obtenerNacionalidades();
	Nacionalidad obtenerNacionalidadPorId(Integer idnacionalidad);
	boolean guardarNacionalidad(Nacionalidad nacionalidad);
	boolean eliminarNacionalidad(Integer idnacionalidad);
	
}
