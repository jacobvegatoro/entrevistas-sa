package cl.puertoesperanza.entrevistassa.modelo;

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
public class Instalacion {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id; 

	private String rutInstalacion;
	
	private String nombreInstalacion;
	
	@ManyToOne
	@JoinColumn(name = "comuna_id", nullable = false)
	private Comuna comuna;
	
}
