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

    // TODO
    // TODO
    // TODO
    @NotNull(message="El nombre no puede ser nulo")
    @NotEmpty(message="El nombre es obligatorio")
    @Size(min=3, max=200, message="El tamaño del nombre debe estar entre 3 y 200" )
    private String  nombre;

    // TODO
    // TODO
    @Min(value = 0, message="El mínimo del estado debe ser 0")
    @Max(value = 100, message="El máximo del estado debe ser 100")
    private Integer estado;

    // TODO - compatible con BBDD
    @Size(min=1, max =50, message ="El tamaño del color como máximo es 50 caracteres")
    private String  color;

    // TODO
    // TODO
    // TODO
    @NotNull(message="el correo no debe ser nulo")
    @NotEmpty(message="el correo no debe ser vacío")
    @Email(message="Debe indicar un correo electrónico correcto")
    private String  usuarioCorreo;
}
