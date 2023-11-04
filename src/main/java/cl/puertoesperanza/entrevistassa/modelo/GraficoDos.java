package cl.puertoesperanza.entrevistassa.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vw_grafico_dos")
@Data
@NoArgsConstructor 
@AllArgsConstructor
public class GraficoDos {

    @Id
    @Column(name="str_fecha_ingreso")
    private String fechaIngreso;

    private Integer cantidadEntrevistas;

}
