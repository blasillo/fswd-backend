package es.jcyl.formacion.backendapi.modelos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TareaModelo {

    private Integer id;
    private String  nombre;
    private Integer estado;
    private String  color;
    private String  usuarioCorreo;
}
