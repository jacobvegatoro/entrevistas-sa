package cl.puertoesperanza.entrevistassa.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vw_grafico_tres")
@Data
@NoArgsConstructor 
@AllArgsConstructor
public class GraficoTres {

    @Id
    @Column(name="nombre_reclutador")
    private String nombreReclutador;

    private Integer cantidadEntrevistas;

}
