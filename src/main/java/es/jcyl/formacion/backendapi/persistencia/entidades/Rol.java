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
@Entity
@Table(name = "app_roles")
@EntityListeners(AuditingEntityListener.class)
public class Rol {

    @Id
    @GeneratedValue
    @Column(name="id_rol")
    private Integer id;

    @Column(name="nombre_rol", length = 60, nullable = false, unique = true)
    private String nombre;


    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private List<Usuario> usuarios;

    @CreatedDate
    @Column(name="F_CREACION", updatable = false)
    private LocalDateTime fechaCreacion;

    @LastModifiedDate
    @Column(name="F_MODIFICACION",insertable = false)
    private LocalDateTime fechaModificacion;
}
