package cl.puertoesperanza.entrevistassa.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor 
@AllArgsConstructor
public class EntrevistadoEliminado {

    @Id
    @Column(name="id_entrevistado")
    private Integer idEntrevistado;
    
    //Grupo 1 (11 campos)
    private String fechaIngreso;
    private String fechaIngresoRv;
    private Integer reclutadorId;
    private String nombreReclutador;
    private String run;
    private Integer clienteId;
    private String nombreCliente;
    private String nombres;
    private String apPaterno;
    private String apMaterno;

    //Grupo 2 (9 campos)
    private String correoElectronico;
    private String telefono;
    private String observacionRegistro;
    private String empresa;
    private Integer validadoId;
    private String estadoValidado;
    private String fechaEstado;
    private String fechaEstadoRv;
    private Integer estadoId;
    
    //Grupo 3 (10 campos)
    private String estado;
    private Integer instalacionId;
    private String nombreInstalacion;
    private Integer cargoId;
    private String nombreCargo;
    private Integer regionId;
    private String nombreRegion;
    private Integer comunaId;
    private String nombreComuna;
    private Integer periodoId;
    
    //Grupo 4 (6 campos)
    private String periodo;
    private Integer servicioId;
    private String servicio;
    private String fechaContratacion;
    private String fechaContratacionRv;
    private String observacionComplementaria;
    
    //Grupo 5 (7 campos)
    private Integer canalId;
    private String nombreCanal;
    private Integer contactadoId;
    private String contactado;
    private Integer presentacionId;
    private String presentacion;
    private String observacionContacto;
    
    //Grupo 6 (4 campos)
    private Integer nacionalidadId;
    private String detalleNacionalidad;
    private String fechaNacimiento;
    private String fechaNacimientoRv;
    
    //Grupo 7 (7 campos)
    private Integer edad;
    private String direccion;
    private String ciudad;
    private String numeroDireccion;
    private Integer previsionId;
    private String prevision;
    private Integer saludId;
    
    //Grupo 8 (6 campos)
    private String salud;
    private Integer segurocovidId;
    private String seguroCovid;
    private Integer tipocuentaId;
    private String tipoCuenta;
    private Integer bancoId;
    
    //Grupo 9 (8 campos)
    private String banco;
    private String numeroCuenta;
    private String calzado;
    private Integer poleraId;
    private String polera;
    private Integer poleronId;
    private String poleron;
    private Integer pantalonId;
    
    //Grupo 10 (6 campos)
    private String pantalon;
    private String nombreContacto;
    private String telefonoContacto;
    private String direccionContacto;
    private Integer parentescocontactoId;
    private String parentesco;
    
    //Grupo 11 (4 campos)
    private Integer comunaContactoId;
    private String comunaContacto;
    private Integer regionContactoId;
    private String nombreRegionContacto;
    
    //Grupo 12 (2 campos)
    private Integer entrevistadorId;
	private String nombreEntrevistador;
	
}
