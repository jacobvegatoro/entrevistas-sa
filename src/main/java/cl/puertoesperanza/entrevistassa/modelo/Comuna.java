package cl.puertoesperanza.entrevistassa.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor 
@AllArgsConstructor
public class Comuna {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id; 

	private String nombreComuna;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "region_id", nullable = false)
	private Region region;


}
