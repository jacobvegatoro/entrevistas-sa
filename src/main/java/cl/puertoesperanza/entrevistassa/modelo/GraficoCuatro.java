package cl.puertoesperanza.entrevistassa.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vw_grafico_cuatro")
@Data
@NoArgsConstructor 
@AllArgsConstructor
public class GraficoCuatro {

    @Id
    @Column(name="detalle_estado")
    private String detalleEstado;

    private Integer cantidadEntrevistas;

}
