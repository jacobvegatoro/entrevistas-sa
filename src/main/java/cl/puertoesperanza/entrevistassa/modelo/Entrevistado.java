package cl.puertoesperanza.entrevistassa.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor 
@AllArgsConstructor
public class Entrevistado {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idEntrevistado; 
	private Date fechaIngreso; 
	private String run;
	private String nombres;
	private String apPaterno;
    private String apMaterno;	
    private String correoElectronico;
	private String telefono;
	private String observacionRegistro;
	
	private String empresa;
	private Integer validado;
	private Date fechaEstado;
	private Integer estado;
	private Integer periodo;
	private Integer tipoServicio;
	private Date fechaContratacion;
	private String observacionComplementaria;
	
	private Integer contactado;
	private Integer presentacion;
	private String observacionContacto;

	private Integer nacionalidad;
	private Date fechaNacimiento;
	private String direccion;
	private String ciudad;
	private String numeroDireccion;
	private Integer prevision;
	private Integer salud;
	private Integer seguroCovid;
	private Integer tipoCuenta;
	private Integer bancoCuenta;
	private String numeroCuenta;
	private String calzado;
	private Integer polera;
	private Integer poleron;
	private Integer pantalon;
	
	private String nombreContacto;
	private String telefonoContacto;
	private Integer parentezcoContacto;
	private String direccionContacto;
	
	@ManyToOne
	@JoinColumn(name = "reclutador_id", nullable = false)
	private Reclutador reclutador; 

	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "cargo_id", nullable = false)
	private Cargo cargo;

	@ManyToOne
	@JoinColumn(name = "comuna_id", nullable = false)
	private Comuna comuna;

	@ManyToOne
	@JoinColumn(name = "canal_id", nullable = false)
	private Canal canal;
	
	@ManyToOne
	@JoinColumn(name = "instalacion_id", nullable = true)
	private Instalacion instalacion;

	@ManyToOne
	@JoinColumn(name = "comuna_contacto_id", nullable = true)
	private Comuna comunaContacto;
	
	@ManyToOne
	@JoinColumn(name = "entrevistador_id", nullable = true)
	private Entrevistador entrevistador;

}
