package es.jcyl.formacion.backendapi.persistencia.entidades;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
# TODO: anotar entidad
# TODO: anotar tabla
@EntityListeners(AuditingEntityListener.class)
public class Tarea {

    # TODO: anotar PK
    # TODO: anotar valor generado
    # TODO: anotar columna
    private Integer id;

    # TODO: anotar columna
    private String nombre;

    # TODO: anotar columna
    private Integer estado;

    # TODO: anotar columna
    private String color;

    # TODO: anotar relacion
    private Usuario usuario;


    @CreatedDate
    @Column(name="F_CREACION", updatable = false)
    private LocalDateTime fechaCreacion;

    @LastModifiedDate
    @Column(name="F_MODIFICACION",insertable = false)
    private LocalDateTime fechaModificacion;

}
