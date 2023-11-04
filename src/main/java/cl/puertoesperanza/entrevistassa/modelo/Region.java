package cl.puertoesperanza.entrevistassa.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor 
@AllArgsConstructor
public class Region {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id; 

	private String codigoRegion;

	private String nombreRegion;

	@JsonManagedReference
	@OneToMany(mappedBy="region", cascade = CascadeType.ALL)
	List<Comuna> comunasLista;
	
}
