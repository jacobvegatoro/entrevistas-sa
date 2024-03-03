package cl.puertoesperanza.entrevistassa.utilidades;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cl.puertoesperanza.entrevistassa.modelo.EntrevistadoVista;

public class EntrevistadoExcelExporter {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<EntrevistadoVista> lentrevistadosvista;

    public EntrevistadoExcelExporter(List<EntrevistadoVista> listaEntrevistadosVista) {
        this.lentrevistadosvista = listaEntrevistadosVista;
        workbook = new XSSFWorkbook();
    }
    
    private void writeHeaderLine() {
        sheet = workbook.createSheet("Postulantes");
         
        Row row = sheet.createRow(0);
         
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
         
        createCell(row, 0, "Postulante ID", style);
        createCell(row, 1, "Fecha ingreso", style);
        createCell(row, 2, "Usuario", style);
        createCell(row, 3, "Run", style);
        createCell(row, 4, "Cliente", style);
        createCell(row, 5, "Nombres", style);
        createCell(row, 6, "Apellido Paterno", style);
        createCell(row, 7, "Apellido Materno", style);
        createCell(row, 8, "Correo electrónico", style);
        createCell(row, 9, "Teléfono", style);
        createCell(row, 10, "Observación registro", style);
        
        createCell(row, 11, "Empresa", style);
        createCell(row, 12, "Validado", style);
        createCell(row, 13, "Fecha estado", style);
        createCell(row, 14, "Estado", style);
        createCell(row, 15, "Instalación", style);
        createCell(row, 16, "Cargo", style);
        createCell(row, 17, "Región", style);
        createCell(row, 18, "Comuna", style);
        createCell(row, 19, "Periodo Contrato", style);
        createCell(row, 20, "Tipo de servicio", style);
        createCell(row, 21, "Fecha contratación", style);
        createCell(row, 22, "Observaciones complementarias", style);
        
        createCell(row, 23, "Canal", style);
        createCell(row, 24, "Contactado", style);
        createCell(row, 25, "Se presenta", style);
        createCell(row, 26, "Observación contacto", style);

        createCell(row, 27, "Nacionalidad", style);
        createCell(row, 28, "Fecha de nacimiento", style);
        createCell(row, 29, "Edad", style);
        createCell(row, 30, "Dirección", style);
        createCell(row, 31, "Ciudad", style);
        createCell(row, 32, "Número casa", style);
        createCell(row, 33, "Previsión", style);
        createCell(row, 34, "Salud", style);
        createCell(row, 35, "Seguro COVID", style);
        createCell(row, 36, "Tipo cuenta", style);
        createCell(row, 37, "Banco", style);
        createCell(row, 38, "Número cuenta", style);
        createCell(row, 39, "Calzado", style);
        createCell(row, 40, "Polera", style);
        createCell(row, 41, "Polerón", style);
        createCell(row, 42, "Pantalón", style);

        createCell(row, 43, "Nombre contacto", style);
        createCell(row, 44, "Teléfono contacto", style);
        createCell(row, 45, "Parentesco contacto", style);
        createCell(row, 46, "Dirección contacto", style);
        createCell(row, 47, "Región contacto", style);
        createCell(row, 48, "Comuna contacto", style);
        
        createCell(row, 49, "Entrevistador", style);

    }

    /*private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }*/

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof String) {
            cell.setCellValue((String) value);
        } else if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        }else {
            cell.setCellValue((Boolean) value);
        }
        cell.setCellStyle(style);
    }
    
    private void createCellInteger(Row row, int columnCount, Integer value, Cell cell) {
        //sheet.autoSizeColumn(columnCount);
        cell = row.createCell(columnCount);
        cell.setCellValue(value);
    }
    
    private void createCellIntegerV2(Row row, int columnCount, Integer value, CellStyle style, Cell cell) {
        //sheet.autoSizeColumn(columnCount);
        cell = row.createCell(columnCount);
        cell.setCellValue(value);
        cell.setCellStyle(style);
    }

    private void createCellString(Row row, int columnCount, String value, Cell cell) {
        //sheet.autoSizeColumn(columnCount);
        cell = row.createCell(columnCount);
        cell.setCellValue(value);
        //cell.setCellStyle(style);
    }

    private void createCellStringV2(Row row, int columnCount, String value, CellStyle style, Cell cell) {
        //sheet.autoSizeColumn(columnCount);
        cell = row.createCell(columnCount);
        cell.setCellValue(value);
        cell.setCellStyle(style);
    }

    private void createCellDate(Row row, int columnCount, String value, Cell cell) {
        cell = row.createCell(columnCount);
        
        if (value.trim().length() > 0) {
            Date fecha = new Date();
            try {
                fecha=new SimpleDateFormat("dd-MM-yyyy").parse(value);        	
            }
            catch(ParseException e) 
            {
            	System.out.println("Error al convertir la fecha");
            }
            cell.setCellValue(fecha);        	
        }
        else {
        	cell.setCellValue(value);
        }
        
        //cell.setCellStyle(style);
    }

    private void createCellDateV2(Row row, int columnCount, String value, CellStyle style, Cell cell) {
        cell = row.createCell(columnCount);
        
        if (value.trim().length() > 0) {
            Date fecha = new Date();
            try {
                fecha=new SimpleDateFormat("dd-MM-yyyy").parse(value);        	
            }
            catch(ParseException e) 
            {
            	System.out.println("Error al convertir la fecha");
            }
            cell.setCellValue(fecha);        	
        }
        else {
        	cell.setCellValue(value);
        }
        
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;
 
        CellStyle styleRegular = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        styleRegular.setFont(font);
        
        CreationHelper createHelper = workbook.getCreationHelper();
        CellStyle styleDate = workbook.createCellStyle();
        styleDate.setFont(font);
        styleDate.setDataFormat(createHelper.createDataFormat().getFormat("dd-mm-yyyy"));

        CellStyle style = workbook.createCellStyle();
        Row row;
        Cell celda;
        int columnCount;
        for (EntrevistadoVista evista : lentrevistadosvista) {
            columnCount = 0;
            row = sheet.createRow(rowCount++);
            style = styleRegular;

            //createCellIntegerV2(row, columnCount++, evista.getIdEntrevistado(), style, celda);
            celda = row.createCell(columnCount++);
            celda.setCellStyle(styleRegular);
            celda.setCellValue(evista.getIdEntrevistado());
            
            style = styleDate;
            //celda.setCellStyle(styleDate);
            createCellDateV2(row, columnCount++, evista.getFechaIngreso(), style, celda);
            style = styleRegular;
            //celda.setCellStyle(styleRegular);
            createCellStringV2(row, columnCount++, evista.getUsername(), style, celda);
            createCellStringV2(row, columnCount++, evista.getRun(), style, celda);
            createCellStringV2(row, columnCount++, evista.getNombreCliente(), style, celda);
            createCellStringV2(row, columnCount++, evista.getNombres(), style, celda);
            createCellStringV2(row, columnCount++, evista.getApPaterno(), style, celda);
            createCellStringV2(row, columnCount++, evista.getApMaterno(), style, celda);
            createCellStringV2(row, columnCount++, evista.getCorreoElectronico(), style, celda);
            createCellStringV2(row, columnCount++, evista.getTelefono(), style, celda);
            createCellStringV2(row, columnCount++, evista.getObservacionRegistro(), style, celda);

            createCellStringV2(row, columnCount++, evista.getEmpresa(), style, celda);
            createCellStringV2(row, columnCount++, evista.getEstadoValidado(), style, celda);
            style = styleDate;
            createCellDateV2(row, columnCount++, evista.getFechaEstado(), styleDate, celda);
            style = styleRegular;
            createCellStringV2(row, columnCount++, evista.getEstado(), style, celda);
            createCellStringV2(row, columnCount++, evista.getNombreInstalacion(), style, celda);
            createCellStringV2(row, columnCount++, evista.getNombreCargo(), style, celda);
            createCellStringV2(row, columnCount++, evista.getNombreRegion(), style, celda);
            createCellStringV2(row, columnCount++, evista.getNombreComuna(), style, celda);
            createCellStringV2(row, columnCount++, evista.getPeriodo(), style, celda);
            createCellStringV2(row, columnCount++, evista.getServicio(), style, celda);
            style = styleDate;
            createCellDateV2(row, columnCount++, evista.getFechaContratacion(), styleDate, celda);
            style = styleRegular;
            createCellStringV2(row, columnCount++, evista.getObservacionComplementaria(), style, celda);

            createCellStringV2(row, columnCount++, evista.getNombreCanal(), style, celda);
            createCellStringV2(row, columnCount++, evista.getContactado(), style, celda);
            createCellStringV2(row, columnCount++, evista.getPresentacion(), style, celda);
            createCellStringV2(row, columnCount++, evista.getObservacionContacto(), style, celda);

            createCellStringV2(row, columnCount++, evista.getDetalleNacionalidad(), style, celda);
            style = styleDate;
            createCellDateV2(row, columnCount++, evista.getFechaNacimiento(), styleDate, celda);
            style = styleRegular;
            createCellIntegerV2(row, columnCount++, evista.getEdad(), style, celda);
            createCellStringV2(row, columnCount++, evista.getDireccion(), style, celda);
            createCellStringV2(row, columnCount++, evista.getCiudad(), style, celda);
            createCellStringV2(row, columnCount++, evista.getNumeroDireccion(), style, celda);
            createCellStringV2(row, columnCount++, evista.getPrevision(), style, celda);
            createCellStringV2(row, columnCount++, evista.getSalud(), style, celda);
            createCellStringV2(row, columnCount++, evista.getSeguroCovid(), style, celda);
            createCellStringV2(row, columnCount++, evista.getTipoCuenta(), style, celda);
            createCellStringV2(row, columnCount++, evista.getBanco(), style, celda);
            createCellStringV2(row, columnCount++, evista.getNumeroCuenta(), style, celda);
            createCellStringV2(row, columnCount++, evista.getCalzado(), style, celda);
            createCellStringV2(row, columnCount++, evista.getPolera(), style, celda);
            createCellStringV2(row, columnCount++, evista.getPoleron(), style, celda);
            createCellStringV2(row, columnCount++, evista.getPantalon(), style, celda);
    		
            createCellStringV2(row, columnCount++, evista.getNombreContacto(), style, celda);
            createCellStringV2(row, columnCount++, evista.getTelefonoContacto(), style, celda);
            createCellStringV2(row, columnCount++, evista.getParentesco(), style, celda);
            createCellStringV2(row, columnCount++, evista.getDireccionContacto(), style, celda);
            createCellStringV2(row, columnCount++, evista.getNombreRegionContacto(), style, celda);
            createCellStringV2(row, columnCount++, evista.getComunaContacto(), style, celda);
            
            createCellStringV2(row, columnCount++, evista.getNombreEntrevistador(), style, celda);
        }
    }
    
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
         
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
         
        outputStream.close();
    }
}
