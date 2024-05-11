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

    @NotNull (message = "El nombre no puede estar vacío")
    @NotEmpty (message = "El nombre no puede estar vacío")
    @Size (max = 200)
    private String  nombre;

    @Min(value = 0, message = "El estado no puede ser menor que 0")
    @Max(value=100, message = "El estado no puede ser mayor que 100")
    private Integer estado;

    @Size (max = 50)
    private String  color;

    @Email
    @NotNull (message = "El correo es obligatorio")
    @NotEmpty (message = "El correo es obligatorio")
    private String  usuarioCorreo;
}
