package es.jcyl.formacion.backendapi.modelos;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Credenciales {

    @NotEmpty (message = "Correo es obligatorio")
    @NotNull (message = "Correo es obligatorio")
    @Email (message = "El correo no es correcto")
    private String correo;

    @NotNull (message = "Contraseña es obligatoria")
    @NotEmpty (message = "Contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener 6 caracteres")
    private String clave;

}
