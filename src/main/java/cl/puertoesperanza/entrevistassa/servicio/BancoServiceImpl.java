package cl.puertoesperanza.entrevistassa.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.puertoesperanza.entrevistassa.modelo.Banco;
import cl.puertoesperanza.entrevistassa.repositorio.BancoRepository;

@Service
public class BancoServiceImpl implements BancoService {

	@Autowired
	private BancoRepository bancoRepositorio;
	
	@Override
	public List<Banco> obtenerBancos() {
		return (List<Banco>) bancoRepositorio.findAll();
	}

}
