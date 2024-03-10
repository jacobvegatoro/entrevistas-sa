package cl.puertoesperanza.entrevistassa.utilidades;

//import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.dhatim.fastexcel.Workbook;
import org.dhatim.fastexcel.Worksheet;

import cl.puertoesperanza.entrevistassa.modelo.EntrevistadoVista;

public class FastExcelSimpleWrite {

	private List<EntrevistadoVista> lentrevistadosvista;
	
	public FastExcelSimpleWrite(List<EntrevistadoVista> listaEntrevistadosVista) {
		this.lentrevistadosvista = listaEntrevistadosVista;
	}
	
    public void writeExcel(HttpServletResponse response) throws IOException{

        //var words = List.of("sky", "blue", "work", "falcon");

    	ServletOutputStream outputStream = response.getOutputStream();
    	
        int row = 0;
        int col = 0;

        //DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        //String currentDateTime = dateFormatter.format(new Date());

        //File currDir = new File(".");
        //String path = currDir.getAbsolutePath();
        //String fileLocation = path.substring(0, path.length() - 1) + "src/main/resources/static/xlsx/postulantes_" + this.username + "_" + currentDateTime + ".xlsx";
        //System.out.println("Ubicacion: " + fileLocation);

        //var f = new File("/Users/macbookair11/Documents/words.xlsx");
        //var f = new File(fileLocation);

        //try (var fos = new FileOutputStream(f)) {

        //var wb = new Workbook(fos, "Application", "1.0");
        var wb = new Workbook(outputStream, "Application", "1.0");
        Worksheet ws = wb.newWorksheet("Postulantes");
        
        ws.value(row, 0, "Postulante ID");
        ws.value(row, 1, "Fecha ingreso");
        ws.value(row, 2, "Usuario");
        ws.value(row, 3, "Run");
        ws.value(row, 4, "Cliente");
        ws.value(row, 5, "Nombres");
        ws.value(row, 6, "Apellido Paterno");
        ws.value(row, 7, "Apellido Materno");
        ws.value(row, 8, "Correo electrónico");
        ws.value(row, 9, "Teléfono");
        ws.value(row, 10, "Observación registro");
        
        ws.value(row, 11, "Empresa");
        ws.value(row, 12, "Validado");
        ws.value(row, 13, "Fecha estado");
        ws.value(row, 14, "Estado");
        ws.value(row, 15, "Instalación");
        ws.value(row, 16, "Cargo");
        ws.value(row, 17, "Región");
        ws.value(row, 18, "Comuna");
        ws.value(row, 19, "Periodo Contrato");
        ws.value(row, 20, "Tipo de servicio");
        ws.value(row, 21, "Fecha contratación");
        ws.value(row, 22, "Observaciones complementarias");
        
        ws.value(row, 23, "Canal");
        ws.value(row, 24, "Contactado");
        ws.value(row, 25, "Se presenta");
        ws.value(row, 26, "Observación contacto");

        ws.value(row, 27, "Nacionalidad");
        ws.value(row, 28, "Fecha de nacimiento");
        ws.value(row, 29, "Edad");
        ws.value(row, 30, "Dirección");
        ws.value(row, 31, "Ciudad");
        ws.value(row, 32, "Número casa");
        ws.value(row, 33, "Previsión");
        ws.value(row, 34, "Salud");
        ws.value(row, 35, "Seguro COVID");
        ws.value(row, 36, "Tipo cuenta");
        ws.value(row, 37, "Banco");
        ws.value(row, 38, "Número cuenta");
        ws.value(row, 39, "Calzado");
        ws.value(row, 40, "Polera");
        ws.value(row, 41, "Polerón");
        ws.value(row, 42, "Pantalón");

        ws.value(row, 43, "Nombre contacto");
        ws.value(row, 44, "Teléfono contacto");
        ws.value(row, 45, "Parentesco contacto");
        ws.value(row, 46, "Dirección contacto");
        ws.value(row, 47, "Región contacto");
        ws.value(row, 48, "Comuna contacto");
        
        ws.value(row, 49, "Entrevistador");            

        row++;
        
        /*for (var word : words) {

            ws.value(row, col, word);
            row++;
        }*/
        
        for (EntrevistadoVista evista : this.lentrevistadosvista) {
            col = 0;
            //ws.value(row, col++, "Hola");
            //System.out.println("Fecha ingreso: " + evista.getFechaIngreso());
            
            ws.value(row, col++, evista.getIdEntrevistado());
            ws.value(row, col++, evista.getFechaIngreso());
            ws.value(row, col++, evista.getUsername());
            ws.value(row, col++, evista.getRun());
            ws.value(row, col++, evista.getNombreCliente());
            ws.value(row, col++, evista.getNombres());
            ws.value(row, col++, evista.getApPaterno());
            ws.value(row, col++, evista.getApMaterno());
            ws.value(row, col++, evista.getCorreoElectronico());
            ws.value(row, col++, evista.getTelefono());
            ws.value(row, col++, evista.getObservacionRegistro());

            ws.value(row, col++, evista.getEmpresa());
            ws.value(row, col++, evista.getEstadoValidado());
            ws.value(row, col++, evista.getFechaEstado());
            ws.value(row, col++, evista.getEstado());
            ws.value(row, col++, evista.getNombreInstalacion());
            ws.value(row, col++, evista.getNombreCargo());
            ws.value(row, col++, evista.getNombreRegion());
            ws.value(row, col++, evista.getNombreComuna());
            ws.value(row, col++, evista.getPeriodo());
            ws.value(row, col++, evista.getServicio());
            ws.value(row, col++, evista.getFechaContratacion());
            ws.value(row, col++, evista.getObservacionComplementaria());

            ws.value(row, col++, evista.getNombreCanal());
            ws.value(row, col++, evista.getContactado());
            ws.value(row, col++, evista.getPresentacion());
            ws.value(row, col++, evista.getObservacionContacto());

            ws.value(row, col++, evista.getDetalleNacionalidad());
            ws.value(row, col++, evista.getFechaNacimiento());
            ws.value(row, col++, evista.getEdad());
            ws.value(row, col++, evista.getDireccion());
            ws.value(row, col++, evista.getCiudad());
            ws.value(row, col++, evista.getNumeroDireccion());
            ws.value(row, col++, evista.getPrevision());
            ws.value(row, col++, evista.getSalud());
            ws.value(row, col++, evista.getSeguroCovid());
            ws.value(row, col++, evista.getTipoCuenta());
            ws.value(row, col++, evista.getBanco());
            ws.value(row, col++, evista.getNumeroCuenta());
            ws.value(row, col++, evista.getCalzado());
            ws.value(row, col++, evista.getPolera());
            ws.value(row, col++, evista.getPoleron());
            ws.value(row, col++, evista.getPantalon());
    		
            ws.value(row, col++, evista.getNombreContacto());
            ws.value(row, col++, evista.getTelefonoContacto());
            ws.value(row, col++, evista.getParentesco());
            ws.value(row, col++, evista.getDireccionContacto());
            ws.value(row, col++, evista.getNombreRegionContacto());
            ws.value(row, col++, evista.getComunaContacto());
            
            ws.value(row, col++, evista.getNombreEntrevistador());
            row++;
        }            

        //wb.finish();
        wb.close();  
        //}
        
        outputStream.close();
    }
    
}
