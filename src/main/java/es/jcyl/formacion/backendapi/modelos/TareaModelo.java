package es.jcyl.formacion.backendapi.modelos;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TareaModelo {

    private Integer id;

    @NotNull (message = "100")
    @NotEmpty (message = "100")
    @Size (max = 200)
    private String  nombre;

    @Min(0)
    @Max(100)
    private Integer estado;

    @Size (max = 50)
    private String  color;

    @Email
    @NotNull
    @NotEmpty
    private String  usuarioCorreo;
}
