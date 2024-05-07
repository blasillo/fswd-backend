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
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "APP_USUARIOS")
@EntityListeners(AuditingEntityListener.class)
public class Usuario extends EntidadBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private Integer id;

    @Column (name="NOMBRE_APELLIDOS", length = 200)
    private String nombreCompleto;

    @Column (name="INICIALES", length = 10)
    private String iniciales;

    @Column (name="CORREO_ELECTRONICO",length = 100, unique = true, nullable = false)
    private String correo;

    @Column (name = "CONTRASENA", length = 100 , nullable = false)
    @Size(min=6,max=100)
    private String clave;

    @ManyToMany( fetch = EAGER )
    @JoinTable(
            name = "app_usuarios_roles", // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_rol")
    )
    private List<Rol> roles;

}
