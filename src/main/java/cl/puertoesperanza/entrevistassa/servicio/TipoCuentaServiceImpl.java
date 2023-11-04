package cl.puertoesperanza.entrevistassa.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.puertoesperanza.entrevistassa.modelo.TipoCuenta;
import cl.puertoesperanza.entrevistassa.repositorio.TipoCuentaRepository;

@Service
public class TipoCuentaServiceImpl implements TipoCuentaService {

	@Autowired
	private TipoCuentaRepository tipoCuentaRepositorio;
	
	@Override
	public List<TipoCuenta> obtenerTiposCuenta() {
		return (List<TipoCuenta>) tipoCuentaRepositorio.findAll();
	}

}
