package es.jcyl.formacion.backendapi.persistencia.entidades;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.FetchType.EAGER;

@Getter
@Setter
@Builder
//@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
// TODO: anotar entidad
// TODO: anotar tabla
@EntityListeners(AuditingEntityListener.class)
public class Usuario {

    // TODO: anotar PK
    // TODO: anotar valor generado
    // TODO: anotar columna
    private Integer id;

    // TODO: anotar columna
    private String nombreCompleto;

    // TODO: anotar columna
    private String iniciales;

    // TODO: anotar columna (deber se única)
    private String correo;

    // TODO: anotar columna
    private String clave;

    // TODO: anotar relación muchos-a-muchos
    private List<Rol> roles;

    @CreatedDate
    @Column(name="F_CREACION", updatable = false)
    private LocalDateTime fechaCreacion;

    @LastModifiedDate
    @Column(name="F_MODIFICACION",insertable = false)
    private LocalDateTime fechaModificacion;
}
