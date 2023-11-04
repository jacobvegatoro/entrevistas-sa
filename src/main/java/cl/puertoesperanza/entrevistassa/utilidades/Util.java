package cl.puertoesperanza.entrevistassa.utilidades;

import java.util.List;
import java.util.stream.IntStream;

import cl.puertoesperanza.entrevistassa.modelo.Entrevistado;
import cl.puertoesperanza.entrevistassa.modelo.EntrevistadoEliminado;
import cl.puertoesperanza.entrevistassa.modelo.EntrevistadoVista;

import java.util.stream.Collectors;

public abstract class Util {

	public static List<Integer> getArregloPaginas
	(
		Integer paginaSolicitada, 
		Integer totalPaginas
	) 
	{
		Integer cantidadBotonesPaginador = 2;
		Integer inicioLista = (paginaSolicitada - cantidadBotonesPaginador) < 1 ? 1 : paginaSolicitada-cantidadBotonesPaginador;
		Integer finLista = (paginaSolicitada + cantidadBotonesPaginador) > totalPaginas ? totalPaginas : paginaSolicitada+cantidadBotonesPaginador; 
		List<Integer> paginas = IntStream.rangeClosed(inicioLista , finLista).boxed().collect(Collectors.toList()); 
		return paginas;
	}
	
	public static Entrevistado transformarTextos(Entrevistado entrevistado) 
	{
		if (entrevistado.getRun() != null && entrevistado.getRun().length() > 0)
			entrevistado.setRun(entrevistado.getRun().toUpperCase());
		
		if (entrevistado.getNombres() != null && entrevistado.getNombres().length() > 0)
			entrevistado.setNombres(entrevistado.getNombres().toUpperCase());
		
		if (entrevistado.getApPaterno() != null && entrevistado.getApPaterno().length() > 0)
			entrevistado.setApPaterno(entrevistado.getApPaterno().toUpperCase());		

		if (entrevistado.getApMaterno() != null && entrevistado.getApMaterno().length() > 0)
			entrevistado.setApMaterno(entrevistado.getApMaterno().toUpperCase());

		if (entrevistado.getCorreoElectronico() != null && entrevistado.getCorreoElectronico().length() > 0)
			entrevistado.setCorreoElectronico(entrevistado.getCorreoElectronico().toUpperCase());
		
		if (entrevistado.getTelefono() != null && entrevistado.getTelefono().length() > 0)
			entrevistado.setTelefono(entrevistado.getTelefono().toUpperCase());
		
		if (entrevistado.getObservacionRegistro() != null && entrevistado.getObservacionRegistro().length() > 0)
			entrevistado.setObservacionRegistro(entrevistado.getObservacionRegistro().toUpperCase());

		if (entrevistado.getEmpresa() != null && entrevistado.getEmpresa().length() > 0)
			entrevistado.setEmpresa(entrevistado.getEmpresa().toUpperCase());
		
		if (entrevistado.getObservacionComplementaria() != null && entrevistado.getObservacionComplementaria().length() > 0)
			entrevistado.setObservacionComplementaria(entrevistado.getObservacionComplementaria().toUpperCase());

		if (entrevistado.getObservacionContacto() != null && entrevistado.getObservacionContacto().length() > 0)
			entrevistado.setObservacionContacto(entrevistado.getObservacionContacto().toUpperCase());
		
		if (entrevistado.getDireccion() != null && entrevistado.getDireccion().length() > 0)
			entrevistado.setDireccion(entrevistado.getDireccion().toUpperCase());
		
		if (entrevistado.getCiudad() != null && entrevistado.getCiudad().length() > 0)
			entrevistado.setCiudad(entrevistado.getCiudad().toUpperCase());

		if (entrevistado.getNumeroDireccion() != null && entrevistado.getNumeroDireccion().length() > 0)
			entrevistado.setNumeroDireccion(entrevistado.getNumeroDireccion().toUpperCase());
		
		if (entrevistado.getNumeroCuenta() != null && entrevistado.getNumeroCuenta().length() > 0)
			entrevistado.setNumeroCuenta(entrevistado.getNumeroCuenta().toUpperCase());

		if (entrevistado.getCalzado() != null && entrevistado.getCalzado().length() > 0)
			entrevistado.setCalzado(entrevistado.getCalzado().toUpperCase());
		
		if (entrevistado.getNombreContacto() != null && entrevistado.getNombreContacto().length() > 0)
			entrevistado.setNombreContacto(entrevistado.getNombreContacto().toUpperCase());
		
		if (entrevistado.getTelefonoContacto() != null && entrevistado.getTelefonoContacto().length() > 0)
			entrevistado.setTelefonoContacto(entrevistado.getTelefonoContacto().toUpperCase());

		if (entrevistado.getDireccionContacto() != null && entrevistado.getDireccionContacto().length() > 0)
			entrevistado.setDireccionContacto(entrevistado.getDireccionContacto().toUpperCase());
		
		return entrevistado;
	}
	
	public static boolean validarRut(String rut) {

	    boolean validacion = false;
	    try {
	        rut =  rut.toUpperCase();
	        rut = rut.replace(".", "");
	        rut = rut.replace("-", "");
	        int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

	        char dv = rut.charAt(rut.length() - 1);

	        int m = 0, s = 1;
	        for (; rutAux != 0; rutAux /= 10) {
	            s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
	        }
	        if (dv == (char) (s != 0 ? s + 47 : 75)) {
	            validacion = true;
	        }

	    } catch (java.lang.NumberFormatException e) {
	    } catch (Exception e) {
	    }
	    return validacion;
	}
	
	public static String formatear(String rut) {
		int cont = 0;
		String format;
		if (rut.length() == 0) {
			return "";
		} else {
			rut = rut.replace(".", "");
			rut = rut.replace("-", "");
			format = "-" + rut.substring(rut.length() - 1);
			for (int i = rut.length() - 2; i >= 0; i--) {
				format = rut.substring(i, i + 1) + format;
				cont++;
				if (cont == 3 && i != 0) {
					format = "." + format;
					cont = 0;
				}
			}
			return format;
		}
	}
	
	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        @SuppressWarnings("unused")
			double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
	public static String limpiarCadena(String cadena) {
		String cad = "";
		boolean esTelefono = false;
		
		if (cadena.startsWith("+"))
			esTelefono = true;
		
		if (isNumeric(cadena)) {
			//System.out.println("Cadena obtenida: " + cadena);
			double d = Double.parseDouble(cadena);
			//System.out.println("Numero obtenido: " + d);
			long i = (long) d;
			cad = String.valueOf(i);
		}
		else {
			cad = cadena;
		}
		
		if (esTelefono && isNumeric(cadena))
			cad = "+" + cad;
		
		return cad;
	}
	
	public static EntrevistadoEliminado transformarEntrevistadoVista(EntrevistadoVista entr) {
		
		EntrevistadoEliminado elim = new EntrevistadoEliminado();

		//Grupo 1 (11 campos)
		elim.setIdEntrevistado(entr.getIdEntrevistado());
		elim.setFechaIngreso(entr.getFechaIngreso());
		elim.setFechaIngresoRv(entr.getFechaIngresoRv());
		elim.setReclutadorId(entr.getReclutadorId());
		elim.setNombreReclutador(entr.getNombreReclutador());
		elim.setRun(entr.getRun());
		elim.setClienteId(entr.getClienteId());
		elim.setNombreCliente(entr.getNombreCliente());
		elim.setNombres(entr.getNombres());
		elim.setApPaterno(entr.getApPaterno());
		elim.setApMaterno(entr.getApMaterno());
		
		//Grupo 2 (9 campos)
		elim.setCorreoElectronico(entr.getCorreoElectronico());
		elim.setTelefono(entr.getTelefono());
		elim.setObservacionRegistro(entr.getObservacionRegistro());
		elim.setEmpresa(entr.getEmpresa());
		elim.setValidadoId(entr.getValidadoId());
		elim.setEstadoValidado(entr.getEstadoValidado());
		elim.setFechaEstado(entr.getFechaEstado());
		elim.setFechaEstadoRv(entr.getFechaEstadoRv());
		elim.setEstadoId(entr.getEstadoId());

		//Grupo 3 (10 campos)
		elim.setEstado(entr.getEstado());
		elim.setInstalacionId(entr.getInstalacionId());
		elim.setNombreInstalacion(entr.getNombreInstalacion());
		elim.setCargoId(entr.getCargoId());
		elim.setNombreCargo(entr.getNombreCargo());
		elim.setRegionId(entr.getRegionId());
		elim.setNombreRegion(entr.getNombreRegion());
		elim.setComunaId(entr.getComunaId());
		elim.setNombreComuna(entr.getNombreComuna());
		elim.setPeriodoId(entr.getPeriodoId());

		//Grupo 4 (6 campos)
		elim.setPeriodo(entr.getPeriodo());
		elim.setServicioId(entr.getServicioId());
		elim.setServicio(entr.getServicio());
		elim.setFechaContratacion(entr.getFechaContratacion());
		elim.setFechaContratacionRv(entr.getFechaContratacionRv());
		elim.setObservacionComplementaria(entr.getObservacionComplementaria());

		//Grupo 5 (7 campos)
		elim.setCanalId(entr.getCanalId());
		elim.setNombreCanal(entr.getNombreCanal());
		elim.setContactadoId(entr.getContactadoId());
		elim.setContactado(entr.getContactado());
		elim.setPresentacionId(entr.getPresentacionId());
		elim.setPresentacion(entr.getPresentacion());
		elim.setObservacionContacto(entr.getObservacionContacto());

		//Grupo 6 (4 campos)
		elim.setNacionalidadId(entr.getNacionalidadId());
		elim.setDetalleNacionalidad(entr.getDetalleNacionalidad());
		elim.setFechaNacimiento(entr.getFechaNacimiento());
		elim.setFechaNacimientoRv(entr.getFechaNacimientoRv());

		//Grupo 7 (7 campos)
		elim.setEdad(entr.getEdad());
		elim.setDireccion(entr.getDireccion());
		elim.setCiudad(entr.getCiudad());
		elim.setNumeroDireccion(entr.getNumeroDireccion());
		elim.setPrevisionId(entr.getPrevisionId());
		elim.setPrevision(entr.getPrevision());
		elim.setSaludId(entr.getSaludId());

		//Grupo 8 (6 campos)
		elim.setSalud(entr.getSalud());
		elim.setSegurocovidId(entr.getSegurocovidId());
		elim.setSeguroCovid(entr.getSeguroCovid());
		elim.setTipocuentaId(entr.getTipocuentaId());
		elim.setTipoCuenta(entr.getTipoCuenta());
		elim.setBancoId(entr.getBancoId());

		//Grupo 9 (8 campos)
		elim.setBanco(entr.getBanco());
		elim.setNumeroCuenta(entr.getNumeroCuenta());
		elim.setCalzado(entr.getCalzado());
		elim.setPoleraId(entr.getPoleraId());
		elim.setPolera(entr.getPolera());
		elim.setPoleronId(entr.getPoleronId());
		elim.setPoleron(entr.getPoleron());
		elim.setPantalonId(entr.getPantalonId());

		//Grupo 10 (6 campos)
		elim.setPantalon(entr.getPantalon());
		elim.setNombreContacto(entr.getNombreContacto());
		elim.setTelefonoContacto(entr.getTelefonoContacto());
		elim.setDireccionContacto(entr.getDireccionContacto());
		elim.setParentescocontactoId(entr.getParentescocontactoId());
		elim.setParentesco(entr.getParentesco());

		//Grupo 11 (4 campos)
		elim.setComunaContactoId(entr.getComunaContactoId());
		elim.setComunaContacto(entr.getComunaContacto());
		elim.setRegionContactoId(entr.getRegionContactoId());
		elim.setNombreRegionContacto(entr.getNombreRegionContacto());

		//Grupo 12 (2 campos)
		elim.setEntrevistadorId(entr.getEntrevistadorId());
		elim.setNombreEntrevistador(entr.getNombreEntrevistador());

		return elim;
		
	}
}
