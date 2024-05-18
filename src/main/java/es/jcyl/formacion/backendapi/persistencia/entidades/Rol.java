package es.jcyl.formacion.backendapi.persistencia.entidades;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
# TODO: anotar entidad
# TODO: anotar tabla
@EntityListeners(AuditingEntityListener.class)
public class Rol {

    # TODO: anotar PK
    # TODO: anotar valor generado
    # TODO: anotar columna
    private Integer id;

    # TODO: anotar columna
    private String nombre;


    # TODO: anotar relacion muchos-a-muchos
    private List<Usuario> usuarios;

    @CreatedDate
    @Column(name="F_CREACION", updatable = false)
    private LocalDateTime fechaCreacion;

    @LastModifiedDate
    @Column(name="F_MODIFICACION",insertable = false)
    private LocalDateTime fechaModificacion;
}
