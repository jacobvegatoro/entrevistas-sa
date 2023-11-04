package cl.puertoesperanza.entrevistassa.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vw_grafico_uno")
@Data
@NoArgsConstructor 
@AllArgsConstructor
public class GraficoUno {

    @Id
    @Column(name="cliente_id")
    private Integer idCliente;
    
    private String nombreCliente;

    private Integer cantidadEntrevistas;
	
}
